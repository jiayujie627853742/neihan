package com.jiayujie.android_neihan.fragments;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jiayujie.android_neihan.EssayDetailActivity;
import com.jiayujie.android_neihan.R;
import com.jiayujie.android_neihan.Client.ClientAPI;
import com.jiayujie.android_neihan.adapter.EssayAdapter;
import com.jiayujie.android_neihan.bean.DataStore;
import com.jiayujie.android_neihan.bean.EntityList;
import com.jiayujie.android_neihan.bean.TextEntity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 1、列表界面，第一次启动，并且数据为空的时候自动加载数据
 * 2、只要列表没有数据，进入这个界面的时候，就尝试加载数据
 * 3、只要有数据就不进行数据的加载
 * 4、进入这个界面，并且有数据的情况下，尝试检查新信息的个数
 * @author jiayujie
 *
 */
public class TextListFragment extends Fragment implements OnClickListener, OnScrollListener, OnRefreshListener2<ListView>, OnItemClickListener {

	/**
	 * 定义一个常量
	 * ID为1代表文本，为2代表图片
	 */
	public static final int CATEGORY_TEXT=1;
	public static final int CATEGORY_IMAGE=2;
	
	//声明队列
	private  RequestQueue queue;
	private long lastTime=0;
	
	private int requestCategory;//请求的分类类型ID
	
	private View quickTools;
	private TextView textNotifiy;
	
	private EssayAdapter adapter;

	public TextListFragment() {
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		queue=Volley.newRequestQueue(getActivity());//生成队列
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		/**
//		 * 现场保护
//		 */
//		if (savedInstanceState!=null) {
//			lastTime = savedInstanceState.getLong("lastTime");
//			Log.i("savedInstance", "======lasttime  "+lastTime);
//		}
		//===========================================
		View view = inflater.inflate(R.layout.fragment_textlist, container,false);
		
		//获取标题控件  添加点击。
		
		View titleView=view.findViewById(R.id.textlist_title);
		titleView.setOnClickListener(this);
		
		
		//除非在fragment的onDestory()中销毁数据，
		 List<TextEntity>  entities = DataStore.getInstance().getTextEntityes();
//		if (entities==null) {
//			entities=new LinkedList<TextEntity>();
//		}
		
		adapter=new EssayAdapter(getActivity(), entities,inflater,CATEGORY_TEXT);
		
		//TODO  1、获取listView并且设置数据
				
				refreshListView = (PullToRefreshListView)view.findViewById(R.id.textlist_listview);
				
				//设置上拉与下拉的事件监听
				refreshListView.setOnRefreshListener(this);
				
				refreshListView.setMode(Mode.BOTH);//设置模式 双向加载
				 ListView listView=refreshListView.getRefreshableView();
				
//				ListView listView=(ListView)view.findViewById(R.id.textlist_listview);
				
				
	 	        header = inflater.inflate(R.layout.textlist_header_tools, listView,false);
				listView.addHeaderView(header);
				
				View quickPublish=header.findViewById(R.id.quick_tools_publish);
				quickPublish.setOnClickListener(this);
				
				View quickReview=header.findViewById(R.id.quick_tools_review);
				quickReview.setOnClickListener(this);
				
				
				listView.setOnScrollListener(this);
				listView.setOnItemClickListener(this);
				
		
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
				
				
				listView.setAdapter(adapter);
		return view;
	}
	
//	//保存数据
//	@Override
//	public void onSaveInstanceState(Bundle outState) {
//		// TODO 自动生成的方法存根
//		super.onSaveInstanceState(outState);
//		outState.putLong("lastTime", lastTime);
//	}
//=================================
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
		
		this.adapter=null;
		this.header=null;
		this.quickTools=null;
		this.textNotifiy=null;
		
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
	private PullToRefreshListView refreshListView;
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
	 * 
	 * 列表网络获取回调部分，这个方法，是在Volley联网响应返回时调用
	 * 
	 */
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO 下拉刷新
		ClientAPI.getList(lastTime, queue, 30, CATEGORY_TEXT, new Response.Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO 加载新数据
				/**
				 * 获取段子内容
				 */
				try {
					
					JSONObject json = new JSONObject(arg0);

					//获取根节点下的data对象
					JSONObject obj=json.getJSONObject("data");
					
					//解析段子列表完整数据
					EntityList entityList=new EntityList();
					//这个方法是解析json的方法，其中包含的支持图片、文本、广告的解析
					entityList.parseJSON(obj);//相当于获取数据内容
					//如果isHasMore返回true，代表还可以更新一次数据
					if (entityList.isHasMore()) {
						lastTime = entityList.getMinTime();//获取更新时间标志
					}else{
						//没有数据可以更新了
						String tip=entityList.getTip();
					}
					//获取段子内容列表
					List<TextEntity> ets =  entityList.getEntities();//交由Adapter操作,至此所有网络请求数据的解析操作全部进行完毕。
					if (ets!=null) {
						if (!ets.isEmpty()) {
//							entities.add(location, object);//把object添加到指定的location位置，原来的location以及以后的内容向后移动
							
//							entities.addAll(0, ets);//把ets中的内容按照迭代器的顺序添加
							DataStore.getInstance().addTextEntities(ets);
							
//							//代码手动添加
//							int len=ets.size();
//							for(int index=len-1;index>=0;index--){
//								entities.add(0, ets.get(index));
//							}
//							//代码手动添加
							adapter.notifyDataSetChanged();
							refreshListView.onRefreshComplete();//数据加载完成，提示消失。
						}else{
							//TODO  没有更多的数据了，需要提示一下
						}
					}else {
						//TODO  没有获取到网络数据，可能是数据解析错误、网络错误，需要提示一下。
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO 上拉加载
		
	}
	/**
	 * listView
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO 自动生成的方法存根
		position--;
		Intent  intent=new Intent(getActivity(), EssayDetailActivity.class);
		intent.putExtra("curretEssayPosition", position);
		intent.putExtra("category", requestCategory);
		startActivity(intent);
		
	}
}
