package com.hzp.zhbj.base;

import com.hzp.zhbj.R;
import com.hzp.zhbj.activity.home.HomeActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 首页，新闻中心，智慧服务等界面的父类
 *
 *2016-11-2  上午10:34:53
 */
public class BasePager {

	public Activity activity;
	public View view;
	public TextView mTitle;
	public FrameLayout mContent;
	public ImageButton mMenu;
	public SlidingMenu slidingMenu;
	public ImageButton mImage;
	
	public BasePager(Activity activity){
		this.activity = activity;
		//获取侧拉菜单
		slidingMenu = ((HomeActivity)activity).getSlidingMenu();
		//在new出来的界面的时候，就加载界面
		view = initView();
	}
	
	/**
	 * 加载布局
	 *@return
	 * 2016-11-2 上午10:30:32
	 */
	public View initView(){
		//因为每个子类都是标题+显示内容的操作，相同操作收取到父类
		view = View.inflate(activity, R.layout.basepager, null);
		
		//初始化控件，方便填充显示子类的数据
		mTitle = (TextView) view.findViewById(R.id.titlebar_tv_title);
		mContent = (FrameLayout) view.findViewById(R.id.basepager_fl_content);
		mMenu = (ImageButton) view.findViewById(R.id.titlebar_btn_menu);
		mImage = (ImageButton) view.findViewById(R.id.titlebar_btn_image);
		
		//设置侧拉菜单按钮的点击事件，实现侧拉菜单的打开关闭操作
		mMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});
		
		return view;
	}
	
	/**
	 * 显示数据
	 *
	 * 2016-11-2 上午10:33:21
	 */
	public void initData(){
		
	}
	
}
