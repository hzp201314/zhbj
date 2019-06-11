package com.hzp.zhbj.fragment;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.hzp.zhbj.R;
import com.hzp.zhbj.base.BaseFragment;
import com.hzp.zhbj.base.BasePager;
import com.hzp.zhbj.pager.GovPager;
import com.hzp.zhbj.pager.HomePager;
import com.hzp.zhbj.pager.NewsCenterPager;
import com.hzp.zhbj.pager.SettingPager;
import com.hzp.zhbj.pager.SmartServicePager;
import com.hzp.zhbj.ui.NoScrollViewpager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 首页的fragment 因为HomeFragment和MenuFragment都需要加载布局，显示数据，相同操作，抽取到父类
 * 
 * 2016-11-1 下午4:35:44
 */
public class HomeFragment extends BaseFragment {

	private NoScrollViewpager mViewPager;
	private ArrayList<BasePager> list;
	private Myadapter myadapter;
	private RadioGroup mButtons;

	@Override
	public View initView() {
		/*
		 * TextView textView = new TextView(activity);
		 * textView.setText("我是首页的fragment");
		 */
		view = View.inflate(activity, R.layout.homefragment, null);
		return view;
	}

	@Override
	public void initData() {
		// 将首页，新闻中心等界面，添加到viewpager中的进行展示操作
		mViewPager = (NoScrollViewpager) view
				.findViewById(R.id.homefragment_vp_viewpager);
		mButtons = (RadioGroup) view.findViewById(R.id.homefragment_rg_buttons);

		// 1.创建首页，新闻中心界面，保存到list集合中，方便viewpager的adapter使用
		list = new ArrayList<BasePager>();
		list.clear();// 保存每次保存的都是最新的界面
		list.add(new HomePager(activity));
		list.add(new NewsCenterPager(activity));
		list.add(new SmartServicePager(activity));
		list.add(new GovPager(activity));
		list.add(new SettingPager(activity));
		// 2.设置viewpager的adapter显示数据
		if (myadapter == null) {
			myadapter = new Myadapter();
			mViewPager.setAdapter(myadapter);
		} else {
			myadapter.notifyDataSetChanged();
		}
		// 3.监听viewpager界面切换监听，当切换到相应的界面的时候，加载相应界面的数据,刚进入界面的时候，没有进行界面的切换，是不会调用界面切换监听的，需要手动设置第一个界面的加载数据
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// 切换到哪个界面，加载哪个界面的数据
				// 获取切换到的界面
				BasePager pager = list.get(position);
				pager.initData();
				
				//6.判断当切换到首页和设置页面的时候，不能进行侧拉菜单侧拉，当切换到其他三个界面的时候可以进行侧拉菜单侧拉
				if (position == 0 || position == list.size()-1) {
					//不能进行侧拉菜单侧拉
					slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				}else{
					//可以进行侧拉菜单侧拉
					slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		// 4.加载第一个界面的数据
		list.get(0).initData();

		// 5.点击相应的RadioButton实现界面切换
		//设置默认选中首页的Radiobutton
		mButtons.check(R.id.homefragment_rbtn_shou);//设置选中哪个Radiobutton
		// 监听Radiogroup中的Radiobutton选中操作
		mButtons.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			// 当Radiobutton选中的时候调用的方法
			// checkedId : 选中的Radiobutton的id
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// 根据选中的Radiobutton的id设置viewpager切换到那个界面
				switch (checkedId) {
				case R.id.homefragment_rbtn_shou:
					//首页
					//设置当前显示的界面是首页的界面
					//mViewPager.setCurrentItem(0);//设置当前显示的界面，item：界面的索引
					mViewPager.setCurrentItem(0, false);//设置当前显示的界面，同时设置切换界面的时候是否执行界面切换的动画效果，true:执行，false:不执行
					break;
				case R.id.homefragment_rbtn_newscenter:
					//新闻中心
					mViewPager.setCurrentItem(1, false);
					break;
				case R.id.homefragment_rbtn_smartservice:
					//智慧服务
					mViewPager.setCurrentItem(2, false);
					break;
				case R.id.homefragment_rbtn_gov:
					//政务
					mViewPager.setCurrentItem(3, false);
					break;
				case R.id.homefragment_rbtn_setting:
					//设置
					mViewPager.setCurrentItem(4, false);
					break;
				}
			}
		});
	}

	/** ViewPager的adapter **/
	private class Myadapter extends PagerAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pager = list.get(position);
			// viewpager只能存放view对象
			View rootview = pager.view;
			container.addView(rootview);
			return rootview;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			container.removeView((View) object);
		}
	}
	
	
	/**
	 * 获取NewsCenterPager界面对象的
	 *@return
	 * 2016-11-4 上午9:52:28
	 */
	public NewsCenterPager getNewsCenterpager(){
		return (NewsCenterPager) list.get(1);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
