package com.jiayujie.android_neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

/**
 * @author jiayujie
 * 文本段子截取
 */
/*{
    "message": "success",
    "data": {
        "has_more": true,
        "min_time": 1411887357,
        "tip": "50条新内容",
        "data": [
            {
                "online_time": 1411878957,
                "display_time": 1411878957,
                "group": {
                    "create_time": 1411718218.0,
                    "favorite_count": 1209,
                    "user_bury": 0,
                    "user_favorite": 0,
                    "bury_count": 1516,
                    "share_url": "http://toutiao.com/group/3560859160/?iid=2337593504&app=joke_essay",
                    "label": 1,
                    "content": "甲:昨天碰到抢劫的，被打了两顿。乙:为啥啊？甲:他问我有钱吗我说没有，他从我身上搜出一包软中华然后就被打了一顿。等他走了，不一会儿又回来打了我一顿，因为他发现里面塞的是白红梅，劫匪走时还留下一句‘没钱还装B’",
                    "comment_count": 177,
                    "status": 3,
                    "has_comments": 0,
                    "go_detail_count": 4370,
                    "status_desc": "已发表到热门列表",
                    "user": {
                        "avatar_url": "http://p1.pstatp.com/thumb/1367/2213311454",
                        "user_id": 3080520868,
                        "name": "请叫我梓安哥",
                        "user_verified": false
                    },
                    "user_digg": 0,
                    "group_id": 3560859160,
                    "level": 4,
                    "repin_count": 1209,
                    "digg_count": 18424,
                    "has_hot_comments": 1,
                    "user_repin": 0,
                    "category_id": 1
                },
                "comments": [],
                "type": 1
            },
            {
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
            },
            {
                "online_time": 1411872657,
                "display_time": 1411872657,
                "group": {
                    "create_time": 1411365213,
                    "favorite_count": 1641,
                    "user_bury": 0,
                    "user_favorite": 0,
                    "bury_count": 2143,
                    "share_url": "http://toutiao.com/group/3551461874/?iid=2337593504&app=joke_essay",
                    "label": 1,
                    "content": "今天看到七岁的儿子在画画，画了一只小鸡，我问他:儿子，你这画的是什么啊？儿子说:我画的是老鹰捉小鸡。我问他:那鹰去哪了？儿子回:那英去参加中国好声音了。现在的孩子…",
                    "comment_count": 111,
                    "status": 3,
                    "has_comments": 0,
                    "go_detail_count": 944,
                    "status_desc": "已发表到热门列表",
                    "user": {
                        "avatar_url": "http://p2.pstatp.com/thumb/953/2658476120",
                        "user_id": 1791732391,
                        "name": "随遇而安",
                        "user_verified": false
                    },
                    "user_digg": 0,
                    "group_id": 3551461874,
                    "level": 4,
                    "repin_count": 1641,
                    "digg_count": 20001,
                    "has_hot_comments": 1,
                    "user_repin": 0,
                    "category_id": 1
                },
                "comments": [],
                "type": 1
            }
        ],
        "max_time": 1411872657
    }
}*/
public class TextEntity {
	private int type;
	private long createTime;
	private int userbury;//代表当前用户是否踩了，0代表没有。
	private int userfavorite;//代表当前用户是否赞了，0代表没有。
	private long buryCount;
	private long favoriteCount;
	private String shareUrl;	//第三方分享提交的网址参数
	private int lable;//TODO
	private String content;
	private int commentCount;
	private int status;//TODO
	private int  hasComments;//当前用户是否评论
	private int goDetailCount;//TODO
	private String statusDesc;//状态的描述信息,可选值。
	private int userDigg;//TODO
	private long groupID;//段子的ID，访问详情和评论时，用这个作为接口参数。
	private int level;//TODO
	private int repinCount;//TODO
	private int diggCount;//digg的个数
	private int hasHotComments;//是否含有热门评论
	private int userRepin;//代表用户是否回复。
	private int categoryID;//内容分类类型。1为文本，2为图片
	
	//TODO     空缺字段 ===>>      需要去分析  comments 这个json数组中的内容是什么。
	
	private  long onlineTime;//上线时间
	private long displayTime;//显示时间
	
	private UserEneity user;
	
	public void parseJSON(JSONObject json) throws JSONException{
		if (json!=null) {
			this.type=json.getInt("type");
			this.onlineTime=json.getLong("online_time");
			this.displayTime=json.getLong("display_time");
			
			JSONObject group=json.getJSONObject("group");
			this.createTime=group.getLong("create_time");
			this.favoriteCount=group.getInt("favorite_count");
			this.userbury=group.getInt("user_bury");
			this.userfavorite=group.getInt("user_favorite");
			this.buryCount=group.getLong("bury_count");
			this.shareUrl=group.getString("share_url");
			this.lable=group.optInt("label",0);
			this.content=group.getString("content");
			this.commentCount=group.getInt("comment_count");
			this.status=group.getInt("status");
			this.hasComments=group.getInt("has_comments");
			this.goDetailCount=group.getInt("go_detail_count");
			this.statusDesc=group.getString("status_desc");
			
			JSONObject uobj=  group.getJSONObject("user");
			user=new UserEneity();
			user.parseJSON(uobj);
			
			this.userDigg=group.getInt("user_digg");
			this.groupID=group.getLong("group_id");
			this.level=group.getInt("level");
			this.repinCount=group.getInt("repin_count");
			this.diggCount=group.getInt("digg_count");
			this.hasHotComments=group.optInt("has_hot_comments",0);
			this.userRepin=group.getInt("user_repin");
			this.categoryID=group.getInt("category_id");
		}
	}

	public int getType() {
		return type;
	}

	public long getCreateTime() {
		return createTime;
	}

	public int getUserbury() {
		return userbury;
	}

	public int getUserfavorite() {
		return userfavorite;
	}

	public long getBuryCount() {
		return buryCount;
	}

	public long getFavoriteCount() {
		return favoriteCount;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public int getLable() {
		return lable;
	}

	public String getContent() {
		return content;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public int getStatus() {
		return status;
	}

	public int getHasComments() {
		return hasComments;
	}

	public int getGoDetailCount() {
		return goDetailCount;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public int getUserDigg() {
		return userDigg;
	}

	public long getGroupID() {
		return groupID;
	}

	public int getLevel() {
		return level;
	}

	public int getRepinCount() {
		return repinCount;
	}

	public int getDiggCount() {
		return diggCount;
	}

	public int getHasHotComments() {
		return hasHotComments;
	}

	public int getUserRepin() {
		return userRepin;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public long getOnlineTime() {
		return onlineTime;
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public UserEneity getUser() {
		return user;
	}
}
