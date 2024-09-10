package model.service;

import org.json.JSONArray;

import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

import model.Post;
import model.UserAction.MLP;
import model.UserAction.UserActionRecord;
import model.UserAction.UserActionProcessed;
import model.UserAction.UserActionPrediction;
import model.dao.UserActionDAO;

public class UserActionManager {

	private static UserActionManager userActionManager = new UserActionManager();
	private UserActionDAO userActionDao;
	private static final long CACHE_DURATION_MS = 3 * 24 * 60 * 60 * 1000; // 3일
	private Map<String, CacheEntry> cache = new HashMap<>();

	private UserActionManager() {
		userActionDao = new UserActionDAO();
	}

	public static UserActionManager getInstance() {
		return userActionManager;
	}

	public int create(UserActionRecord record) throws Exception {
		return userActionDao.create(record);
	}

	// 구현 필요
	public int delete(UserActionRecord record) throws Exception {
		return userActionDao.delete(record);
	}

	private static class CacheEntry {
		List<Post> posts;
		long timestamp;

		CacheEntry(List<Post> posts, long timestamp) {
			this.posts = posts;
			this.timestamp = timestamp;
		}
	}

	public List<Post> findRecommendPersonalizedPostsByUserId(String userId) throws Exception {

		long currentTime = System.currentTimeMillis();

		if (cache.containsKey(userId) && (currentTime - cache.get(userId).timestamp) < CACHE_DURATION_MS) {
			System.out.println("Returning cached posts for user: " + userId);
			return cache.get(userId).posts;
		}

		System.out.println("사용자 " + userId + "의 사용자 행동 전처리 데이터를 수집합니다.");

		List<UserActionProcessed> UserActionProcessed = findPreprocessUserActionsByUserId(userId);

		if (UserActionProcessed.isEmpty()) {
			System.out.println("사용자 " + userId + "의 데이터가 없습니다.");
			return null;
		}

		System.out.println("사용자 " + userId + "의 MLP 모델 학습을 시작합니다.");

		List<DataSet> trainingData = convertToDataSet(UserActionProcessed);
		MLP mlp = new MLP();
		mlp.trainModel(trainingData);

		System.out.println("사용자 " + userId + "의 선호 게시글 예측을 시작합니다.");

		List<UserActionPrediction> predictions = new ArrayList<>();

		for (UserActionProcessed data : UserActionProcessed) {
			double[] features = new double[] { data.getLiked(), data.getViewDuration(), data.getViewCount() };
			double score = mlp.predict(features);

			predictions.add(new UserActionPrediction(data, score));
		}

		UserActionProcessed topPost = predictions.stream()
				.sorted(Comparator.comparingDouble(UserActionPrediction::getScore).reversed()).findFirst()
				.map(UserActionPrediction::getAction).orElse(null);

		System.out.println("모든 게시글 인덱싱을 시작합니다.");

		PostManager postManager = PostManager.getInstance();

		JSONArray posts = postManager.convertAllPostToJsonArray();
		FaissClient.indexPosts(posts);

		System.out.println("인덱싱된 모든 게시글 중 사용자의 선호 게시글을 검색합니다.");

		Post preferredPost = postManager.findPostByPostId(topPost.getPostId());
		System.out.println("사용자가 가장 선호하는 게시글의 제목은: " + preferredPost.getTitle());

		int[] arr = FaissClient.findSimilar(postManager.convertPostToJsonArray(preferredPost));

		List<Post> similarPosts = new ArrayList<>();

		for (int i = 1; i < arr.length; i++) {
			Post post = postManager.findPostByPostId(arr[i]);
			similarPosts.add(post);

			System.out.println(arr[i]);
		}

		cache.put(userId, new CacheEntry(similarPosts, currentTime));
		System.out.println("Updating cache for user: " + userId);

		return similarPosts;

	}

	public List<UserActionProcessed> findPreprocessUserActionsByUserId(String userId) {
		List<UserActionProcessed> allActions = userActionDao.preprocessUserActions();

		if (allActions == null) {
			System.out.println("allActions is null");
		}
		return allActions.stream().filter(action -> action.getUserId().equals(userId)).collect(Collectors.toList());
	}

	public List<DataSet> convertToDataSet(List<UserActionProcessed> processeds) {

		List<DataSet> trainingData = new ArrayList<>();

		for (UserActionProcessed processed : processeds) {

			double[] features = new double[] { processed.getLiked(), processed.getViewDuration(),
					processed.getViewCount() };
			double label = processed.getLiked();

			DataSet dataSet = new DataSet(Nd4j.create(features), Nd4j.create(new double[] { label }));
			trainingData.add(dataSet);
		}

		return trainingData;
	}

}
