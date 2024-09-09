package model.service;

import org.json.JSONArray;

import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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

	private UserManager userManager = UserManager.getInstance();
	private PostManager postManager = PostManager.getInstance();

	private static final int SCHEDULE_INTERVAL = 60; // 배치 처리 주기 (초)
	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	private UserActionManager() {
		userActionDao = new UserActionDAO();
		scheduler.scheduleAtFixedRate(() -> {
			try {
				getTopPostForAllUsers();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, SCHEDULE_INTERVAL, SCHEDULE_INTERVAL, TimeUnit.SECONDS);
	}

	public static UserActionManager getInstance() {
		return userActionManager;
	}

	public int create(UserActionRecord record) throws Exception {
		return userActionDao.create(record);
	}

	// 구현 필요
	public int delete(UserActionRecord record) throws Exception {
		return userActionDao.create(record);
	}

	public List<Post> getTopPostForAllUsers() throws Exception {

		System.out.println("배치 학습을 시작합니다.");

		Map<String, UserActionProcessed> userTopPosts = new HashMap<>();

		for (String userId : userManager.findAllUserId()) {

			System.out.println("사용자 " + userId + "의 데이터로 학습을 시작합니다.");

			List<UserActionProcessed> userActions = findPreprocessUserActionsByUserId(userId);

			if (userActions.isEmpty()) {
				System.out.println("사용자 " + userId + "의 데이터가 없습니다. 다음 사용자로 넘어갑니다.");
				continue;
			}

			List<DataSet> trainingData = convertToDataSet(userActions);

			MLP mlp = new MLP();
			mlp.trainModel(trainingData);

			System.out.println("사용자 " + userId + "의 데이터로 학습이 완료되었습니다.");
			System.out.println("사용자 " + userId + "의 선호 게시글 예측을 시작합니다.");

			List<UserActionPrediction> predictions = new ArrayList<>();

			for (UserActionProcessed action : userActions) {
				double[] features = new double[] { action.getLiked(), action.getViewDuration(), action.getViewCount() };
				double score = mlp.predict(features);

				predictions.add(new UserActionPrediction(action, score));
			}

			UserActionProcessed topPost = predictions.stream()
					.sorted(Comparator.comparingDouble(UserActionPrediction::getScore).reversed()).findFirst()
					.map(UserActionPrediction::getAction).orElse(null);

			System.out.println("사용자 " + userId + "의 선호 게시글 예측을 종료합니다.");

			userTopPosts.put(userId, topPost);
		}

		System.out.println("배치 학습 및 선호 게시글 예측 데이터 생성이 완료되었습니다.");

		int topPostId = -1;

		for (Map.Entry<String, UserActionProcessed> entry : userTopPosts.entrySet()) {
			String userId = entry.getKey();
			UserActionProcessed topPost = entry.getValue();

			if (topPost != null) {
				System.out.println("사용자 ID: " + userId);
				System.out.println("선호 게시글 ID: " + topPost.getPostId());
				System.out.println("-------------------------------");

				topPostId = topPost.getPostId();
			} else {
				System.out.println("사용자 ID: " + userId + "는 선호 게시글이 없습니다.");
			}
		}

		System.out.println("모든 게시글 인덱싱을 시작합니다.");

		JSONArray posts = postManager.convertAllPostToJsonArray();
		FaissClient.indexPosts(posts);
		
		System.out.println("모든 게시글 인덱싱을 완료하였습니다.");

		System.out.println("인덱싱된 모든 게시글 중 사용자의 선호 게시글을 검색합니다.");
		
		Post topPost = postManager.findPostByPostId(topPostId);
		int[] arr = FaissClient.findSimilar(postManager.convertPostToJsonArray(topPost));
		
		List<Post> similarPosts = new ArrayList<>();
		
		for(int i = 1; i < arr.length; i++) {
			Post post = postManager.findPostByPostId(topPostId);
			similarPosts.add(post);
		}
		
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
