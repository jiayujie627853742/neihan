package com.jiayujie.android_neihan.bean;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class EntityList {
	private long maxTime;
	private String tip;
	private long minTime;
	private boolean hasMore;
	private int i=0;
	
	private List<TextEntity> entities;

	public void parseJSON(JSONObject json) throws JSONException{
		if (json!=null) {
			hasMore = json.getBoolean("has_more");//是否可以加载更多
			tip = json.getString("tip");//提示信息
			if (hasMore==true) {
				minTime = json.getLong("min_time");
				
			}
			maxTime = json.optLong("max_time");
			
			//从data对象中获取名称为data的json数组
			JSONArray array=json.getJSONArray("data");
			entities=new LinkedList<TextEntity>();
			
			//得到数组的长度
			int len=array.length();
			if (len>0) {
				//遍历数组中的每一条图片段子的信息
				for (int i = 0; i < len; i++) {
					JSONObject item=array.getJSONObject(i);
					int type=item.getInt("type");//获取类型  1.段子  5广告
					if (type==5) {
						//处理广告
						ADEntity entity=new ADEntity();
						entity.parseJSON(item);
					
						Log.i("hehe", "======guanggao"+entity.getDownload_url());
					}else if (type==1) {
						JSONObject group =  item.getJSONObject("group");
						int cid=group.getInt("category_id");
						TextEntity entity=null;
						if (cid==1) {
							//TODO 解析文本段子
							entity=new TextEntity();
						}else if (cid==2) {
							//TODO 解析图片段子
							entity=new ImageEntity();
						}
						entity.parseJSON(item);
						
						entities.add(entity);
						
						long groupID = entity.getGroupID();
						Log.i("hehe", "======"+(i++)+","+entity.getGroupID());
					}
				}
//				for (int i = 0; i < entities.size(); i++) {
//					TextEntity textEntity=entities.get(i);
//					Log.i("hehe", "======"+i+","+textEntity.getGroupID());
//				}
			}
		}
	}

	public long getMaxTime() {
		return maxTime;
	}

	public String getTip() {
		return tip;
	}

	public long getMinTime() {
		return minTime;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public List<TextEntity> getEntities() {
		return entities;
	}
	
	
}
