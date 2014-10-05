package com.jiayujie.android_neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ADEntity {
	
private int type;
private long display_time;
private long online_time;
private int display_image_height;
private long ad_id;
private int display_image_width;
private String source;
private String packagename;
private String title;
private String open_url;
private String download_url;
private int is_ad;
private String display_info;
private String web_url;
private int display_type;
private String button_text;
private String appleid;
private String track_url;
private String label;
private long id;
private String ipa_url;
private String display_image;

/*	  {
          "type": 5,
          "display_time": 1411878658,
          "online_time": 1411878658,
          "ad": {
              "display_image_height": 400,
              "ad_id": 3561092485,
              "display_image_width": 600,
              "source": "",
              "package": "",
              "title": "霜霜和阿伟都爱玩的游戏，还有iphone6等你拿哦！",
              "open_url": "",
              "download_url": "http://yihua.b0.upaiyun.com/neihan.apk",
              "is_ad": 1,
              "display_info": "霜霜和阿伟都爱玩的游戏，还有iphone6等你拿哦！",
              "web_url": "http://yihua.b0.upaiyun.com/neihan.apk",
              "display_type": 3,
              "button_text": "立即下载",
              "appleid": "",
              "track_url": "",
              "label": "推广",
              "type": "app",
              "id": 3561092485,
              "ipa_url": "",
              "display_image": "http://p2.pstatp.com/large/1362/1075506622"
          }
      }*/
	public void parseJSON(JSONObject json) throws JSONException{
		if (json!=null) {
			type = json.getInt("type");
			display_time = json.getLong("display_time");
			online_time = json.getLong("online_time");
			
			
			JSONObject adJsonObject=json.getJSONObject("ad");
			display_image_height = adJsonObject.getInt("display_image_height");
			ad_id = adJsonObject.getLong("ad_id");
			display_image_width = adJsonObject.getInt("display_image_width");
			source = adJsonObject.getString("source");
			packagename = adJsonObject.getString("package");
			title = adJsonObject.getString("title");
			open_url = adJsonObject.getString("open_url");
			download_url = adJsonObject.getString("download_url");
			is_ad = adJsonObject.getInt("is_ad");
			display_info = adJsonObject.getString("display_info");
			web_url = adJsonObject.getString("web_url");
			display_type = adJsonObject.getInt("display_type");
			button_text = adJsonObject.getString("button_text");
			appleid = adJsonObject.getString("appleid");
			track_url = adJsonObject.getString("track_url");
			label = adJsonObject.getString("label");
			id = adJsonObject.getLong("id");
			ipa_url = adJsonObject.getString("ipa_url");
			display_image = adJsonObject.getString("display_image");
		}
	}

	public int getType() {
		return type;
	}

	public long getDisplay_time() {
		return display_time;
	}

	public long getOnline_time() {
		return online_time;
	}

	public int getDisplay_image_height() {
		return display_image_height;
	}

	public long getAd_id() {
		return ad_id;
	}

	public int getDisplay_image_width() {
		return display_image_width;
	}

	public String getSource() {
		return source;
	}

	public String getPackagename() {
		return packagename;
	}

	public String getTitle() {
		return title;
	}

	public String getOpen_url() {
		return open_url;
	}

	public String getDownload_url() {
		return download_url;
	}

	public int getIs_ad() {
		return is_ad;
	}

	public String getDisplay_info() {
		return display_info;
	}

	public String getWeb_url() {
		return web_url;
	}

	public int getDisplay_type() {
		return display_type;
	}

	public String getButton_text() {
		return button_text;
	}

	public String getAppleid() {
		return appleid;
	}

	public String getTrack_url() {
		return track_url;
	}

	public String getLabel() {
		return label;
	}

	public long getId() {
		return id;
	}

	public String getIpa_url() {
		return ipa_url;
	}

	public String getDisplay_image() {
		return display_image;
	}
}
