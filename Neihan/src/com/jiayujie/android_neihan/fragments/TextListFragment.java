package com.jiayujie.android_neihan.fragments;

import java.util.LinkedList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jiayujie.android_neihan.R;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TextListFragment extends Fragment implements OnClickListener, OnScrollListener, OnRefreshListener2<ListView> {

	private View quickTools;
	private TextView textNotifiy;

	public TextListFragment() {
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		View view = inflater.inflate(R.layout.fragment_textlist, container,false);
		
		//获取标题控件  添加点击。
		
		View titleView=view.findViewById(R.id.textlist_title);
		titleView.setOnClickListener(this);
		
		
		
		//TODO  1、获取listView并且设置数据
				
				PullToRefreshListView refreshListView=(PullToRefreshListView)view.findViewById(R.id.textlist_listview);
				
				//设置上拉与下拉的事件监听
				refreshListView.setOnRefreshListener(this);
				
				refreshListView.setMode(Mode.BOTH);//设置模式 双向加载
				 ListView listView=refreshListView.getRefreshableView();
				
//				ListView listView=(ListView)view.findViewById(R.id.textlist_listview);
				
				List<String>strings=new LinkedList<String>();
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				strings.add("java"+System.currentTimeMillis());
				
				
	 	        header = inflater.inflate(R.layout.textlist_header_tools, listView,false);
				listView.addHeaderView(header);
				
				View quickPublish=header.findViewById(R.id.quick_tools_publish);
				quickPublish.setOnClickListener(this);
				
				View quickReview=header.findViewById(R.id.quick_tools_review);
				quickReview.setOnClickListener(this);
				
				ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,strings);
				
				listView.setAdapter(adapter);
				
				listView.setOnScrollListener(this);
				
		
		//TODO  2、获取 快速的工具调（发布和审核），用于列表滚动的显示和隐藏
				
				quickTools = view.findViewById(R.id.textlist_quick_tools);//默认隐藏
				quickTools.setVisibility(View.GONE);
				
				//设置悬浮的工具条  两个命令的事件
				quickPublish=quickTools.findViewById(R.id.quick_tools_publish);
				quickPublish.setOnClickListener(this);
				
				quickReview=quickTools.findViewById(R.id.quick_tools_review);
				quickReview.setOnClickListener(this);
				
		//TODO 3、获取  新条目通知控件，每次有新数据要显示,过一段时间隐藏
				
				textNotifiy = (TextView) view.findViewById(R.id.textlist_new_notify);//新消息的提醒
				textNotifiy.setVisibility(View.GONE);
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO 自动生成的方法存根
		super.onResume();
	}
	
	@Override
	public void onPause() {
		// TODO 自动生成的方法存根
		super.onPause();
	}
	
	@Override
	public void onDestroyView() {
		// TODO 自动生成的方法存根
		super.onDestroyView();
	}
	
	
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			int what=msg.what;
			if (what==1) {
				//TODO  what==1  代表有新消息提醒
				textNotifiy.setVisibility(View.INVISIBLE);
			}
		};
		
	};
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		int id=v.getId();
		switch (id) {
		case R.id.textlist_title:
			textNotifiy.setVisibility(View.VISIBLE);
//			handler.obtainMessage(1);
//			handler.sendMessageDelayed(handler.obtainMessage(1), 3000);
			handler.sendEmptyMessageDelayed(1, 3000);
			break;
		}
	}
	
	/**
	 * 列表滚动，显示工具条
	 */
	private int lastIndex=0;
	private View header;
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO 自动生成的方法存根
		int offset=lastIndex-firstVisibleItem;
		if (offset<0||firstVisibleItem==1||firstVisibleItem==0) {
			//证明现在移动是向上移动
			if (quickTools!=null) {
				quickTools.setVisibility(View.INVISIBLE);
			}
		}else if (offset>0) {
			if (quickTools!=null) {
				quickTools.setVisibility(View.VISIBLE);
			}
		}
		lastIndex=firstVisibleItem;
	}
	
	
	/**
	 * 列表的上拉加载和下拉刷新事件
	 * 上拉加载旧数据，下拉刷新加载新的数据
	 */
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO 自动生成的方法存根
		
	}
	
	
}
