package com.jiayujie.android_neihan.fragments;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import pl.droidsonroids.gif.GifImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.jiayujie.android_neihan.R;
import com.jiayujie.android_neihan.Client.ClientAPI;
import com.jiayujie.android_neihan.adapter.CommentAdapter;
import com.jiayujie.android_neihan.bean.Comment;
import com.jiayujie.android_neihan.bean.CommentList;
import com.jiayujie.android_neihan.bean.TextEntity;
import com.jiayujie.android_neihan.bean.UserEneity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailContentFragment extends Fragment implements OnTouchListener, Listener<String> {

	private TextEntity entity;
	private ScrollView scrollView;
	private TextView txtHotCommentCount;
	private TextView txtRecentCommentCount;
	private RequestQueue queue;
	
	private int offset;
	
	
	public DetailContentFragment() {
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		if (entity==null) {
			Bundle arguments = getArguments();
			Serializable serializable = arguments.getSerializable("entity");
			if (serializable instanceof TextEntity) {
				entity=(TextEntity)serializable;
			}
		}
		queue=Volley.newRequestQueue(getActivity());
	}
	
	private CommentAdapter hotAdapter;
	private CommentAdapter recentAdapter;
	private List<Comment>  hotComments;
	
	private List<Comment> recentComments;
	
	private LinearLayout scrollContent;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		View  ret=inflater.inflate(R.layout.fragment_detail_content, container,false);
		
		scrollView = (ScrollView) ret.findViewById(R.id.detail_scroll);
		scrollView.setOnTouchListener(this);
		
		scrollContent=(LinearLayout)ret.findViewById(R.id.scroll_content);
		
		
//		if (entity!=null) {
//			UserEneity user = entity.getUser();
//			if (user!=null) {
//				String avatarUrl = user.getAvatarUrl();
//				String nick = user.getName();
//				TextView txtNick = (TextView) ret.findViewById(R.id.comment_profile_nick);
//			}
//		}
		
		txtHotCommentCount = (TextView)ret.findViewById(R.id.txt_hot_comments_count);
		hotCommentListView = (ListView)ret.findViewById(R.id.hot_comments_list);
		hotComments=new LinkedList<>();
		hotAdapter=new CommentAdapter(getActivity(),hotComments);
		hotCommentListView.setAdapter(hotAdapter);
		
		txtRecentCommentCount = (TextView)ret.findViewById(R.id.txt_recent_comments_count);
		recentCommentListView = (ListView)ret.findViewById(R.id.recent_comments_list);
		recentComments=new LinkedList<>();
		recentAdapter=new CommentAdapter(getActivity(),hotComments);
		recentCommentListView.setAdapter(recentAdapter);
		
		setEssayContent(ret);
		
		groupID = entity.getGroupID();
		ClientAPI.getComment(queue, groupID, 0, this);
		
		return ret;
	}
	
	/**
	 * 设置段子的主体内容（详情）
	 */
	private void setEssayContent(View ret) {
		
		TextView btnGifPlay = (TextView) ret.findViewById(R.id.btngifplay);
		ImageButton btnShare = (ImageButton) ret.findViewById(R.id.item_share);
		CheckBox chbBuryCount = (CheckBox) ret.findViewById(R.id.item_bury_count);
		CheckBox chbDiggCount = (CheckBox) ret
				.findViewById(R.id.item_digg_count);
		GifImageView gifImageView = (GifImageView) ret
				.findViewById(R.id.gifView);
		ImageView imgprofileImage = (ImageView) ret
				.findViewById(R.id.item_profile_image);
		ProgressBar pbDownloadProgress = (ProgressBar) ret
				.findViewById(R.id.item_image_download_progress);
		TextView txtCommentCount = (TextView) ret
				.findViewById(R.id.item_comment_count);
		TextView txtContent = (TextView) ret.findViewById(R.id.item_content);
		TextView txtprofileNick = (TextView) ret
				.findViewById(R.id.item_profile_nick);
		
		
		
		UserEneity  user=entity.getUser();
		String nick="";
		if (user!=null) {
				nick=user.getName();
		}
		txtprofileNick.setText(nick);
		
		String content=entity.getContent();
		txtContent.setText(content);
		
		chbDiggCount.setText(String.valueOf(entity.getDiggCount()));
		int userDigg = entity.getUserDigg();//当前用户是否赞过
		chbDiggCount.setEnabled(userDigg!=1);//等于1表示赞过，则禁用点击；不等于1表示没有赞过
		
		chbBuryCount.setText(String.valueOf(entity.getBuryCount()));
		int userBury=entity.getUserbury();
		chbBuryCount.setEnabled(userBury!=1);//等于1表示踩过，则禁用点击；不等于1表示没有踩过
		
		txtCommentCount.setText(String.valueOf(entity.getCommentCount()));//设置评论个数
		
		/**
		 * 设置图片内容的数据
		 */
		//TODO  加载各种图片数据
	}
	/**
	 * 处理ScrollView 触摸事件，用于在ScrollView滚动到最低端的时候自动加载数据
	 */
	private boolean hasMove=false;
	private long groupID;
	private ListView hotCommentListView;
	private ListView recentCommentListView;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自动生成的方法存根
		boolean bret=false;
		
		int action=event.getAction();
		//摁下事件
		if (action==MotionEvent.ACTION_DOWN) {
			bret=true;
			hasMove=false;
		}
		else if (action==MotionEvent.ACTION_MOVE) {
			hasMove=true;
		}
		//抬起事件
		else if (action==MotionEvent.ACTION_UP) {
			if (hasMove) {
				int sy = scrollView.getScrollY();
				int mh = scrollView.getMeasuredHeight();
				int ch = scrollContent.getHeight();//内容区高度
				if (sy+mh>=ch) {
					//TODO  进行评论分页加载
					ClientAPI.getComment(queue, groupID, offset, this);
				}
			}
		}
		
		return bret;
	}
	/**
	 * 
	 */
	@Override
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
			if (topComments!=null) {
				hotComments.addAll(topComments);
				hotAdapter.notifyDataSetChanged();
			}
			//新鲜评论
			List<Comment> rtComments = commentList.getRecentComments();
			//TODO  直接把commentList提交给listView的Adapter ，这样可以进行是否还有内容的判断
			if (rtComments!=null) {
				recentComments.addAll(rtComments);
				recentAdapter.notifyDataSetChanged();
			}
			
			if (recentComments!=null) {
				for(Comment comment:recentComments){
				}
			}
			offset+=20;//分页标识，要求服务器每次返回20条评论。通过hasMore判断是否还需要分页。

			//TODO  扩充listView的内容
			int  childCount=hotCommentListView.getChildCount();
			if (childCount>0) {
				int totalHeight=0;
				for(int i=0;i<childCount;i++){
					View  view=hotCommentListView.getChildAt(i);
					totalHeight+=view.getHeight();
				}
				LayoutParams layoutParams = hotCommentListView.getLayoutParams();
				layoutParams.height=totalHeight;
				hotCommentListView.setLayoutParams(layoutParams);
			}
			
			
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
