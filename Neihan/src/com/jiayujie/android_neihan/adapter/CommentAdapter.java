package com.jiayujie.android_neihan.adapter;

import java.sql.Date;
import java.util.List;

import com.jiayujie.android_neihan.R;
import com.jiayujie.android_neihan.bean.Comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {
	
	private List<Comment> comments;
	private LayoutInflater inflater;
	
	public CommentAdapter(Context context,List<Comment> comments) {
		// TODO 自动生成的构造函数存根
		this.comments=comments;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return comments.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return comments.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return comments.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View  ret=convertView;
		if (convertView==null) {
			ret=inflater.inflate(R.layout.item_comment, parent,false);
		}
		if (ret!=null) {
		     ViewHolder holder=	(ViewHolder) ret.getTag();
		     if (holder==null) {
				holder=new ViewHolder();
				holder.profileImage=(ImageView)ret.findViewById(R.id.comment_profile_image);
				holder.chbDigg=(CheckBox)ret.findViewById(R.id.comment_digg_count);
				holder.txtContent=(TextView)ret.findViewById(R.id.comment_content);
				holder.txtCreateTime=(TextView)ret.findViewById(R.id.comment_create_time);
				holder.txtNick=(TextView)ret.findViewById(R.id.comment_profile_nick);
				ret.setTag(holder);
			}
		     Comment comment = comments.get(position);
		     holder.txtNick.setText( comment.getUserName());
		     
		     Date  date=new Date(comment.getCreateTime());
		     holder.txtCreateTime.setText(date.toString());
		     holder.txtContent.setText(comment.getText());
		     
		     int diggCount = comment.getDiggCount();
		     holder.chbDigg.setText(String.valueOf(diggCount));
		     int userDigg = comment.getUserDigg();
		     holder.chbDigg.setEnabled((userDigg==0));
		}
		
		return ret;
	}
	static class ViewHolder{
		public ImageView profileImage;
		public TextView txtNick;
		public TextView txtCreateTime;
		public TextView txtContent;
		public CheckBox chbDigg;
	}
}
