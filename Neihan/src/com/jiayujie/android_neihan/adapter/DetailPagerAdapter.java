package com.jiayujie.android_neihan.adapter;

import java.util.List;

import com.jiayujie.android_neihan.bean.DataStore;
import com.jiayujie.android_neihan.bean.TextEntity;
import com.jiayujie.android_neihan.fragments.DetailContentFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DetailPagerAdapter extends FragmentPagerAdapter {

	private List<TextEntity> entities;//传入list

	public DetailPagerAdapter(FragmentManager fm, List<TextEntity> entities) {
		super(fm);
		this.entities=entities;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO 自动生成的方法存根
		DetailContentFragment fragment=new DetailContentFragment();
		
		Bundle arguments=new Bundle();
		
		TextEntity entity= entities.get(arg0);
		arguments.putSerializable("entity", entity);
		fragment.setArguments(arguments);
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return entities.size();
	}

}
