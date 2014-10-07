package com.jiayujie.android_neihan;

import java.util.List;

import com.jiayujie.android_neihan.adapter.DetailPagerAdapter;
import com.jiayujie.android_neihan.bean.DataStore;
import com.jiayujie.android_neihan.bean.TextEntity;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

/**
 *  shi wo de cuo 
 * @author jiayujie
 *
 */
public class EssayDetailActivity extends FragmentActivity {

	private ViewPager pager;
	
	private DetailPagerAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_essay_detail);
		
		pager=(ViewPager)this.findViewById(R.id.detail_pager_content);
		
		Intent intent = getIntent();
		Bundle extras=intent.getExtras();
	
		int category=1;
		int currentEssayPosition=0;
		
		if (extras!=null) {
			category=extras.getInt("category",1);
			currentEssayPosition=extras.getInt("currentEssayPosition", 0);
			Log.i("currentEssayPosition", "======currentEssayPosition : "+currentEssayPosition);
		}
		
		DataStore dataStore = DataStore.getInstance();
		List<TextEntity> entities=null;
		if (category==1) {
			entities=dataStore.getTextEntityes();
		}else if (category==2) {
			entities=dataStore.getImageEntities();
		}
		Log.i("entities", "======entities : "+entities.get(currentEssayPosition).toString());
		
		adapter=new DetailPagerAdapter(getSupportFragmentManager(), entities);
		
		pager.setAdapter(adapter);
		
		if (currentEssayPosition>0) {
			pager.setCurrentItem(currentEssayPosition);
		}
	}
}
