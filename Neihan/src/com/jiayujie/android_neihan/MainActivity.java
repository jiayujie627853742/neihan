package com.jiayujie.android_neihan;

import java.util.LinkedList;
import java.util.List;

import com.jiayujie.android_neihan.fragments.HuodongFragment;
import com.jiayujie.android_neihan.fragments.ImageListFragment;
import com.jiayujie.android_neihan.fragments.MineFragment;
import com.jiayujie.android_neihan.fragments.ReviewFragment;
import com.jiayujie.android_neihan.fragments.TextListFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

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
		switchFragment(0);
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
		switchFragment(checkedIndex);
		
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

	public void switchFragment(int checkedIndex) {
		Fragment fragment=fragments.get(checkedIndex);
		FragmentManager manager=getSupportFragmentManager();
		FragmentTransaction transaction=manager.beginTransaction();
		transaction.replace(R.id.main_fragment_container, fragment);
		transaction.commit();
	}
}
