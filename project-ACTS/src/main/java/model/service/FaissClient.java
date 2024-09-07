package model.service;

import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.charset.StandardCharsets;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class FaissClient {

	private static final String FAISS_INDEX_URL = "http://localhost:5000/index_posts";
	private static final String FAISS_SEARCH_URL = "http://localhost:5000/find_similar";

	public static void indexPosts(JSONArray allPosts) throws Exception {

		URL url = new URL(FAISS_INDEX_URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setDoOutput(true);

		try (OutputStream os = conn.getOutputStream()) {
			byte[] input = allPosts.toString().getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}

		conn.getInputStream().close();
	}

	public static int[] findSimilar(JSONObject post) throws Exception {

		URL url = new URL(FAISS_SEARCH_URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setDoOutput(true);

		try (OutputStream os = conn.getOutputStream()) {
			byte[] input = post.toString().getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}

		Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8);
		String response = scanner.useDelimiter("\\A").next();
		scanner.close();

		JSONObject responseJson = new JSONObject(response);
		JSONArray indicesJson = responseJson.getJSONArray("indices");
		
		int[] indices = new int[indicesJson.length()];
		for (int i = 0; i < indicesJson.length(); i++) {
			indices[i] = indicesJson.getInt(i);
		}

		return indices;
	}

}
