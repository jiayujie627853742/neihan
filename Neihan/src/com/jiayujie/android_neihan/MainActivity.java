package com.jiayujie.android_neihan;

import java.security.acl.Group;
import java.util.LinkedList;
import java.util.List;

import com.jiayujie.android_neihan.fragments.HuodongFragment;
import com.jiayujie.android_neihan.fragments.ImageListFragment;
import com.jiayujie.android_neihan.fragments.MineFragment;
import com.jiayujie.android_neihan.fragments.ReviewFragment;
import com.jiayujie.android_neihan.fragments.TextListFragment;

import android.R.integer;
import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.os.Build;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{

	private RadioGroup group;
	private List<Fragment> fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		group = (RadioGroup)this.findViewById(R.id.main_tab_bar);
		group.setOnCheckedChangeListener(this);
		
		fragments =new LinkedList<Fragment>();
		fragments.add(new TextListFragment());
		fragments.add(new ImageListFragment());
		fragments.add(new ReviewFragment());
		fragments.add(new HuodongFragment());
		fragments.add(new MineFragment());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO 自动生成的方法存根
		int childCount=group.getChildCount();
		int checkedIndex=0;
		RadioButton btn=null;
		for (int i = 0; i <childCount; i++) {
			btn=(RadioButton)group.getChildAt(i);
			if (btn.isChecked()) {
				checkedIndex=i;
				break;
			}
		}
		Fragment fragment=fragments.get(checkedIndex);
		
		FragmentManager manager=getSupportFragmentManager();
		FragmentTransaction transaction=manager.beginTransaction();
		transaction.replace(R.id.main_fragment_container, fragment);
		transaction.commit();
		
		/*switch (checkedIndex) {
		case 0:
			
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
		transaction.commit();*/
	}
}
