package com.jiayujie.test;


import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.jiayujie.android_neihan.R;
import com.jiayujie.android_neihan.Client.ClientAPI;
import com.jiayujie.android_neihan.bean.ImageEntity;
import com.jiayujie.android_neihan.bean.UrlList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

//测试网络连接获取数据
public class MainactivityActivity extends Activity implements Listener<String> {
	
	//定义一个常量
	//ID为1代表文本，为2代表图片
	public static final int CATEGORY_TEXT=1;
	public static final int CATEGORY_IMAGE=2;
	
	//声明队列
	private  RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivity);
		
		queue = Volley.newRequestQueue(this);
		
		int itemCount=1;
		ClientAPI.getList(queue,itemCount, CATEGORY_IMAGE,this);
	}
	@Override
	public void onResponse(String arg0) {
		// TODO 自动生成的方法存根
		// Log.i("TestActivity", "======List"+arg0);
		try {
			JSONObject json = new JSONObject(arg0);
			arg0=json.toString(4);
//			 Log.i("TestActivity", "======"+arg0);
			System.out.println(arg0);
			//获取根节点下的data对象
			JSONObject obj=json.getJSONObject("data");
			
			//从data对象中获取名称为data的json数组
			JSONArray array=obj.getJSONArray("data");
			
			//得到数组的长度
			int len=array.length();
			if (len>0) {
				//遍历数组中的每一条图片段子的信息
				for (int i = 0; i < len; i++) {
					JSONObject item=array.getJSONObject(i);
					ImageEntity imageEntity=new ImageEntity();
					imageEntity.parseJSON(item);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
