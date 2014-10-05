package com.jiayujie.android_neihan.bean;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//评论接口返回的data :{} 数据部分实体定义
//包含了top_comments和 recent_comments 两个数组
/**
 * @author jiayujie
 *json格式如下<br/>
 *<pre>
 *{
 *		"data":{
 *				"top_comments" : []
 *				"recent_comments" : []
 *			}
 *}
 *<pre/>
 */
public class CommentList {
	
	private List<Comment> topComments;
	
	private List<Comment> recentComments;
	
	private long groupId;
	private int totalNumber;
	private boolean hasMore;
	
	public List<Comment> getTopComments() {
		return topComments;
	}

	public List<Comment> getRecentComments() {
		return recentComments;
	}
	
	public long getGroupId() {
		return groupId;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public boolean getHasMore() {
		return hasMore;
	}

	public void parseJSON(JSONObject json) throws JSONException{
		if (json!=null) {
			
			groupId=json.getLong("group_id");
			totalNumber=json.getInt("total_number");
			hasMore=json.getBoolean("has_more");
			
			JSONObject data=json.getJSONObject("data");
			JSONArray topArray =   data.optJSONArray("top_comments");
			JSONArray recentArray =   data.optJSONArray("recent_comments");
			if (topArray!=null) {
				topComments=new LinkedList<Comment>();
				int len=topArray.length();
				if (len>0) {
					for (int i = 0; i < len; i++) {
						JSONObject obj = topArray.getJSONObject(i);
						Comment comment=new Comment();
						comment.parseJSON(obj);
						topComments.add(comment);
					}
				}
			}
			if (recentArray!=null) {
				recentComments=new LinkedList<Comment>();
				int len=recentArray.length();
				if (len>0) {
					for (int i = 0; i < len; i++) {
						JSONObject obj = recentArray.getJSONObject(i);
						Comment comment=new Comment();
						comment.parseJSON(obj);
						recentComments.add(comment);
					}
				}
			}
		}
	}
}
