package com.jiayujie.test;


import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.jiayujie.android_neihan.R;
import com.jiayujie.android_neihan.Client.ClientAPI;
import com.jiayujie.android_neihan.bean.Comment;
import com.jiayujie.android_neihan.bean.CommentList;
import com.jiayujie.android_neihan.bean.EntityList;

/**
 * 测试网络连接获取数据
 * @author jiayujie
 */
public class MainactivityActivity extends Activity implements Listener<String>,OnClickListener {
	
	/**
	 * 定义一个常量
	 * ID为1代表文本，为2代表图片
	 */
	public static final int CATEGORY_TEXT=1;
	public static final int CATEGORY_IMAGE=2;
	
	//声明队列
	private  RequestQueue queue;
	
	private Button button;
	private int itemCount;
	private long lastTime;
	private long groupId;
	private int offset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivity);
		
		queue = Volley.newRequestQueue(this);
		
		itemCount = 30;
		ClientAPI.getList(0,queue,itemCount, CATEGORY_TEXT,this);
		button=(Button)this.findViewById(R.id.button1);
		button.setOnClickListener(this);
		
		groupId = 3079732238l;
		offset = 0;
		ClientAPI.getComment(queue,groupId, offset,this);
		queue.start();
		
	}
	/**
	 * 列表网络获取回调部分
	 * @param arg0
	 */
	public void onResponse(String arg0) {
		// TODO 自动生成的方法存根
		
		/**
		 * 获取评论列表
		 */
		try {
			JSONObject json=new JSONObject(arg0);
			arg0=json.toString(4);
			
		/*	Iterator<String > key=json.keys();
			while(key.hasNext()){
				String value=key.next();
				Log.i("", "======"+value);
			}*/
			
//			Log.i("", "======评论"+arg0);
		
			CommentList commentList=new CommentList();
			/**
			 * 评论列表包含两组数据，一个是热门评论，另一个是新鲜评论
			 * 热门评论和新鲜评论可能是空的。
			 */
			commentList.parseJSON(json);
			long gid=commentList.getGroupId();
			
			//表示评论列表是否还有更多数据加载
			boolean hasmore=commentList.getHasMore();
			
			int totalNumber=commentList.getTotalNumber();
			
			//热门评论
			List<Comment> topComments = commentList.getTopComments();
			//新鲜评论
			List<Comment> recentComments = commentList.getRecentComments();
			//TODO  直接把commentList提交给listView的Adapter ，这样可以进行是否还有内容的判断
			
			if (recentComments!=null) {
				for(Comment comment:recentComments){
					Log.i("", "======"+comment.getText());
				}
			}
			offset+=20;//分页标识，要求服务器每次返回20条评论。通过hasMore判断是否还需要分页。
			Log.i("", "======gid+hasmore:  "+gid+","+hasmore+","+totalNumber);
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		/**
		 * 获取段子内容
		 */
		try {
			
			JSONObject json = new JSONObject(arg0);
			arg0=json.toString(4);

			//获取根节点下的data对象
			JSONObject obj=json.getJSONObject("data");
			
			//解析段子列表完整数据
			EntityList entityList=new EntityList();
			//这个方法是解析json的方法，其中包含的支持图片、文本、广告的解析
			entityList.parseJSON(obj);//相当于获取数据内容
			//如果isHasMore返回true，代表还可以更新一次数据
			if (entityList.isHasMore()) {
				lastTime = entityList.getMinTime();//获取更新时间标志
				Log.i("lastTime", "======lastTime"+lastTime);
			}else{
				//没有数据可以更新了
				String tip=entityList.getTip();
				Log.i("Tip", "======Tip"+tip);
			}
			//获取段子内容列表
			entityList.getEntities();//交由Adapter操作,至此所有网络请求数据的解析操作全部进行完毕。
			//
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
//		ClientAPI.getList(lastTime,queue,itemCount, CATEGORY_IMAGE,this);
		ClientAPI.getComment(queue,groupId, offset,this);
	}
}
