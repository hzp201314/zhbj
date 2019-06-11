package com.hzp.zhbj.base;

import com.hzp.zhbj.activity.home.HomeActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;

/**
 * 菜单页页面的父类
 * 因为父类不知道子类加载什么样的界面，显示什么样的数据，所以创建抽象方法，子类根据自己的特性进行实现
 *2016-11-4  上午10:16:55
 */
public abstract class BaseMenupager {
	public Activity activity;
	public View rootView;
	public SlidingMenu slidingMenu;
	public BaseMenupager(Activity activity){
		this.activity = activity;
		slidingMenu = ((HomeActivity)activity).getSlidingMenu();
		rootView = initView();
	}
	
	/**
	 * 加载布局
	 *@return
	 * 2016-11-4 上午10:15:43
	 */
	public abstract View initView();
	
	/**
	 * 显示数据
	 *
	 * 2016-11-4 上午10:16:02
	 */
	public abstract void initData();
}
