package com.jiayujie.android_neihan.bean;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UrlList {

	//生成get方法
	public List<String> getLargeImageUrls() {
		return largeImageUrls;
	}

	public String getUri() {
		return uri;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private List<String> largeImageUrls;
	private String uri;
	private int width;
	private int height;

	public void parseJSON(JSONObject jsonObject) throws JSONException{
		largeImageUrls = parseImageList(jsonObject);
		uri = jsonObject.getString("uri");
		width = jsonObject.getInt("width");
		height = jsonObject.getInt("height");
	}

	//json数据存在格式相似的部分，采用同一个方法解析，来减少代码量，提升代码效率
	private List<String> parseImageList(JSONObject largeImage)
			throws JSONException {
		List<String> ImageUrls=new LinkedList<String>();
		
		JSONArray urls= largeImage.getJSONArray("url_list");
		int ulen=urls.length();
		for (int j = 0; j < ulen; j++) {
			JSONObject uobject=urls.getJSONObject(j);
			String url=uobject.getString("url");
			ImageUrls.add(url);
		}
		return ImageUrls;
	}
}
