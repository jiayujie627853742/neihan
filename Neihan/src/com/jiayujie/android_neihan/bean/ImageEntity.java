package com.jiayujie.android_neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageEntity extends TextEntity {

	private UrlList largeList;
	private UrlList middleList;

	public UrlList getLargeList() {
		return largeList;
	}

	public UrlList getMiddleList() {
		return middleList;
	}

	public void parseJSON(JSONObject item) throws JSONException{
		super.parseJSON(item);
		
		JSONObject group=item.getJSONObject("group");
		
		JSONObject largeImage=group.optJSONObject("large_image");
		JSONObject middleImage=group.optJSONObject("middle_image");
		
		largeList = new UrlList();
		if (largeImage!=null) {
			largeList.parseJSON(largeImage);
		}
		
		middleList = new UrlList();
		if (middleImage!=null	) {
			middleList.parseJSON(middleImage);
		}
	}
}
