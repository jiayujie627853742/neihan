package com.jiayujie.android_neihan.Client;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jiayujie.test.TestactivityActivity;

/**
 * 所有和服务器接口对接的方法全部在这个类里面
 * @author jiayujie
 *
 */
public class ClientAPI {

	public ClientAPI() {
		// TODO 自动生成的构造函数存根
	}

	/**
		 * 私有方法来获取内涵段子中的内容
		 * @param queue  任务请求队列
		 * @param itemCount   要获取的数据个数，即指定服务器一次传回的数据的条目数
		 * @param catefarytype   要获取的参数的类型
		 * @param  listener 用于获取段子列表的回调接口，任何调用getList方法时，此参数用更新段子列表
		 * @param minTime  用作分页加载数据，或者是下拉刷新时用，代表的是上一次服务器返回的信息
		 * @see  TestactivityActivity#CATEGORY_TEXT
		 * @see  TestactivityActivity#CATEGORY_IMAGE
		 */
		public  static void getList(long minTime,RequestQueue queue,int itemCount,int catefarytype,Response.Listener<String> listener){
			String CATEGORY_LIST_API="http://ic.snssdk.com/2/essay/zone/category/data/";//根据类型获取不同的数据
			String categoryParam="category_id="+catefarytype;
			String countParam="count="+itemCount;
			String deviceParam="KFTT";
			String openUDID="b90ca6a3a19a78d6";
			
		//	long minTime;//分页显示字段，用作分页加载时反馈给服务器的参数。
			
			//拼接成的url地址
			String url=CATEGORY_LIST_API
					+"?"
					+categoryParam
					+"&"
					+countParam
					+"&"
					+deviceParam
					+"&"
					+openUDID
					+"&level=6&iid=2337593504&device_id=2757969807&ac=wifi&channel=wandoujia&aid=7&app_name=joke_essay&version_code=302&device_platform=android&os_api=15&os_version=4.0.3";

			if (minTime>0) {
				url=url+"&mintime"+minTime;
			}
			
			//		RequestQueue queue=Volley.newRequestQueue(this);
			queue.add(new StringRequest(Request.Method.GET, url, listener, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError arg0) {
					// TODO 自动生成的方法存根
				}
			}));
		}
		/**
		 * 获取评论内容
		 * @param queue  请求队列
		 * @param groupId  段子ID
		 * @param offset	 翻页
		 * @param listener  用于获取段子列表的回调接口，任何调用getList方法时，此参数用更新评论列表
		 */
	public static void getComment(RequestQueue queue,long groupId, int offset,Response.Listener<String> listener) {
		String COMMENT_API="http://isub.snssdk.com/2/data/get_essay_comments/";
		String groupIdParams="group_id="+groupId;
		String offsetParams="&offset="+offset;
		String url=COMMENT_API
				+"?"
				+groupIdParams
				+"&"
				+offsetParams
				+"&count=20&iid=2337593504&device_id=2757969807&ac=wifi&channel=wandoujia&aid=7&app_name=joke_essay&version_code=302&device_platform=android&device_type=KFTT&os_api=15&os_version=4.0.3&openudid=b90ca6a3a19a78d6";
		queue.add(new StringRequest(Request.Method.GET, url, listener, new Response.ErrorListener() {
	
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO 自动生成的方法存根
				
			}
		}));
	}
	
}
