package com.jiayujie.android_neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageEntity {
	private int type;
	private long groupId;
	private String content;
	private int commentCount;
	private UrlList largeList;
	private UrlList middleList;

	public void parseJSON(JSONObject item) throws JSONException{
		type = item.getInt("type");
		JSONObject group=item.getJSONObject("group");
		commentCount = group.getInt("comment_count");
		
		JSONObject largeImage=group.getJSONObject("large_image");
		JSONObject middleImage=group.getJSONObject("middle_image");
		groupId = group.getLong("group_id");
		content = group.getString("content");
		
		largeList = new UrlList();
		largeList.parseJSON(largeImage);
		
		middleList = new UrlList();
		middleList.parseJSON(middleImage);
	}
}
