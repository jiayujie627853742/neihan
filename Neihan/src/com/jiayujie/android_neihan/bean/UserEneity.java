package com.jiayujie.android_neihan.bean;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class UserEneity  implements Serializable{
	
	
	private static final long serialVersionUID = 8213728212255500430L;

	
	private String avatarUrl;//头像网址
	private long userID;//用户ID
	private String  name;//用户昵称
	private boolean userVerified;//用户是否验证
	
	
	
	public String getAvatarUrl() {
		return avatarUrl;
	}



	public long getUserID() {
		return userID;
	}



	public String getName() {
		return name;
	}



	public boolean isUserVerified() {
		return userVerified;
	}



	public void parseJSON(JSONObject item) throws JSONException{
		if (item!=null) {
			this.avatarUrl=item.getString("avatar_url");
			this.userID=item.getLong("user_id");
			this.name=item.getString("name");
			this.userVerified=item.getBoolean("user_verified");
		}
	}
	
}
/*  "user": {
    "avatar_url": "http://p2.pstatp.com/thumb/953/2658476120",
    "user_id": 1791732391,
    "name": "随遇而安",
    "user_verified": false
	}*/
