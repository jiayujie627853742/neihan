package com.jiayujie.android_neihan.Client;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jiayujie.test.MainactivityActivity;

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
		 * @see  MainactivityActivity#CATEGORY_TEXT
		 * @see  MainactivityActivity#CATEGORY_IMAGE
		 */
		public  static void getList(RequestQueue queue,int itemCount,int catefarytype,Response.Listener<String> listener){
			String CATEGORY_LIST_API="http://ic.snssdk.com/2/essay/zone/category/data/";
			//根据类型获取不同的数据
			String categoryParam="category_id="+catefarytype;
			String countParam="count="+itemCount;
			String deviceParam="KFTT";
			String openUDID="b90ca6a3a19a78d6";
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
	//		RequestQueue queue=Volley.newRequestQueue(this);
			queue.add(new StringRequest(Request.Method.GET, url, listener, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError arg0) {
					// TODO 自动生成的方法存根
				}
			}));
			queue.start();
		}
	
}
