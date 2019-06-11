package com.hzp.zhbj.pager.menu;

import com.hzp.zhbj.base.BaseMenupager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 菜单页专题界面
 * 因为每个界面都要加载界面，显示数据，所以相同操作抽取到父类
 *2016-11-4  上午10:15:10
 */
public class MenuSpecialPager extends BaseMenupager {

	public MenuSpecialPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		TextView textView = new TextView(activity);
		textView.setText("菜单详情页-专题");
		textView.setTextSize(22);
		textView.setTextColor(Color.RED);
		textView.setGravity(Gravity.CENTER);
		return textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	
}
