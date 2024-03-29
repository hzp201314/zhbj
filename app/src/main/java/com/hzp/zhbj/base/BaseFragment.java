package com.hzp.zhbj.base;

import com.hzp.zhbj.activity.home.HomeActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 首页和菜单页的fragment的父类
 * 
 * 2016-11-1 下午4:37:00
 */
public abstract class BaseFragment extends Fragment {
	public Activity activity;
	public View view;
	public SlidingMenu slidingMenu;
	// 初始化数据
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//getActivity : 获取管理fragment的activity
		activity = getActivity();
		slidingMenu = ((HomeActivity)activity).getSlidingMenu();
		super.onCreate(savedInstanceState);
	}

	// 加载fragment布局的方法
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//因为fragment加载布局的操作是否调用onCreateView方法实现的，不是通过initView方法实现的
		view = initView();
		return view;
	}

	// 加载显示数据的方法，类似activity的oncrate方法
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initData();
		super.onActivityCreated(savedInstanceState);
	}
	
	
	//因为父类不知道子类要加载什么布局，显示什么数据，所以，创建抽象方法，子类实现抽象方法，根据自己的特性进行相应的实现
	/**
	 * 加载布局的方法，由子类进行实现
	 *@return
	 * 2016-11-1 下午4:39:34
	 */
	public abstract View initView();
	/**
	 * 显示数据的方法，由子类进行实现
	 *
	 * 2016-11-1 下午4:39:58
	 */
	public abstract void initData();
	
	
	
	
	
	
	
	
	
}
