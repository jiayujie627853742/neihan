package com.jiayujie.android_neihan.adapter;

import java.util.List;
import java.util.zip.Inflater;

import pl.droidsonroids.gif.GifImageView;

import com.android.volley.toolbox.Volley;
import com.jiayujie.android_neihan.R;
import com.jiayujie.android_neihan.bean.TextEntity;
import com.jiayujie.android_neihan.bean.UserEneity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class EssayAdapter extends BaseAdapter{

	private Context  context;
	
	private List<TextEntity> entities;
	
	private LayoutInflater inflater;
	
	private int categoryType;
	
	public EssayAdapter(Context context,List<TextEntity> entities,LayoutInflater inflater,int categoryType){
		this.context=context;
		this.entities=entities;
		this.inflater=inflater;
		this.categoryType=categoryType;
	}
	
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return entities.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return entities.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View ret=convertView;
		if (convertView==null) {
			ret=inflater.inflate(R.layout.item_essay, parent, false);
		}
		ViewHolder holder=(ViewHolder) ret.getTag();
		if (holder==null) {
			holder=new ViewHolder();
			holder.btnGifPlay=(TextView)ret.findViewById(R.id.btngifplay);
			holder.btnShare=(ImageButton)ret.findViewById(R.id.item_share);
			holder.chbBuryCount=(CheckBox)ret.findViewById(R.id.item_bury_count);
			holder.chbDiggCount=(CheckBox)ret.findViewById(R.id.item_digg_count);
			holder.gifImageView=(GifImageView)ret.findViewById(R.id.gifView);
			holder.imgprofileImage=(ImageView)ret.findViewById(R.id.item_profile_image);
			holder.pbDownloadProgress=(ProgressBar)ret.findViewById(R.id.item_image_download_progress);
			holder.txtCommentCount=(TextView)ret.findViewById(R.id.item_comment_count);
			holder.txtContent=(TextView)ret.findViewById(R.id.item_content);
			holder.txtprofileNick=(TextView)ret.findViewById(R.id.item_profile_nick);
			ret.setTag(holder);
		}
		
		
		/**
		 * 设置图片段子相应的控件消失
		 */
		holder.gifImageView.setVisibility(View.GONE);
		holder.btnGifPlay.setVisibility(View.GONE);
		//================================
		/**
		 * 先设置文本内容的数据
		 */
		TextEntity entity=entities.get(position);
		
		UserEneity  user=entity.getUser();
		String nick="";
		if (user!=null) {
				nick=user.getName();
		}
		holder.txtprofileNick.setText(nick);
		
		String content=entity.getContent();
		holder.txtContent.setText(content);
		
		holder.chbDiggCount.setText(String.valueOf(entity.getDiggCount()));
		int userDigg = entity.getUserDigg();//当前用户是否赞过
		holder.chbDiggCount.setEnabled(userDigg!=1);//等于1表示赞过，则禁用点击；不等于1表示没有赞过
		
		holder.chbBuryCount.setText(String.valueOf(entity.getBuryCount()));
		int userBury=entity.getUserbury();
		holder.chbBuryCount.setEnabled(userBury!=1);//等于1表示踩过，则禁用点击；不等于1表示没有踩过
		
		holder.txtCommentCount.setText(String.valueOf(entity.getCommentCount()));//设置评论个数
		
		/**
		 * 设置图片内容的数据
		 */
		//TODO  加载各种图片数据
		
		return ret;
	}
	static class ViewHolder{
		public ImageView imgprofileImage;//头像
		public TextView txtprofileNick;//昵称
		public  TextView txtContent;//文本内容
		public ProgressBar pbDownloadProgress;//下载进度
		public GifImageView gifImageView;//图片显示
		public TextView btnGifPlay;//图片显示部分按钮，开启GIF播放
		public CheckBox chbDiggCount;//赞，包含个数，如果已经赞过，则禁用
		public CheckBox chbBuryCount;//踩，包含个数，如果已经踩过，则禁用
		public TextView txtCommentCount;//评论个数，点击可以查看评论
		public ImageButton btnShare;//分享操作
		
	}
	
}
