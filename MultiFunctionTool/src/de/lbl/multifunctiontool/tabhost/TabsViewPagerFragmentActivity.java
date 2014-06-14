package de.lbl.multifunctiontool.tabhost;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import de.lbl.multifunctiontool.R;

public class TabsViewPagerFragmentActivity extends FragmentActivity implements TabHost.OnTabChangeListener,ViewPager.OnPageChangeListener
{

	private TabHost						mTabHost;
	private ViewPager						mViewPager;
	private HashMap<String, TabInfo>	mapTabInfo	= new HashMap<String, TabsViewPagerFragmentActivity.TabInfo>();
	private PagerAdapter					mPagerAdapter;
	public Context							mContext;

	private GestureDetector				gd;

	private class TabInfo
	{
		private String		tag;
		private Class<?>	clss;
		private Bundle		args;
		private Fragment	fragment;


		TabInfo(String tag, Class<?> clazz, Bundle args)
		{
			this.tag = tag;
			this.clss = clazz;
			this.args = args;
		}

	}

	/**
	 * A simple factory that returns dummy views to the Tabhost
	 * 
	 */
	class TabFactory implements TabContentFactory
	{

		private final Context	mContext;


		/**
		 * @param context
		 */
		public TabFactory(Context context)
		{
			mContext = context;
		}


		public View createTabContent(String tag)
		{
			View v = new View(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}

	}


	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mContext = this;
		gd = new GestureDetector(this);

		// Inflate the layout
		setContentView(R.layout.tabviewpager);
		// Initialise the TabHost
		this.initialiseTabHost(savedInstanceState);
		if (savedInstanceState != null)
		{
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); // set
																									// the
																									// tab
																									// as
																									// per
																									// the
																									// saved
																									// state
		}
		// Intialise ViewPager
		this.intialiseViewPager();
	}


	protected void onSaveInstanceState(Bundle outState)
	{
		outState.putString("tab", mTabHost.getCurrentTabTag()); // save the tab
																					// selected
		super.onSaveInstanceState(outState);
	}


	/**
	 * Initialise ViewPager
	 */
	private void intialiseViewPager()
	{

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, Tab1.class.getName()));
		fragments.add(Fragment.instantiate(this, Tab2.class.getName()));
		fragments.add(Fragment.instantiate(this, Tab3.class.getName()));
		this.mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
		//
		this.mViewPager = (ViewPager) super.findViewById(R.id.viewpager);
		this.mViewPager.setAdapter(this.mPagerAdapter);
		this.mViewPager.setOnPageChangeListener(this);
	}


	/**
	 * Initialise the Tab Host
	 */
	private void initialiseTabHost(Bundle args)
	{
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		TabInfo tabInfo = null;
		TabsViewPagerFragmentActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab1").setIndicator("Tab 1"), (tabInfo = new TabInfo("Tab1", Tab1.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		TabsViewPagerFragmentActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab2").setIndicator("Tab 2"), (tabInfo = new TabInfo("Tab2", Tab2.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		TabsViewPagerFragmentActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab3").setIndicator("Tab 3"), (tabInfo = new TabInfo("Tab3", Tab3.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		// Default to first tab
		// this.onTabChanged("Tab1");
		//
		mTabHost.setOnTabChangedListener(this);
	}


	/**
	 * Add Tab content to the Tabhost
	 * 
	 * @param activity
	 * @param tabHost
	 * @param tabSpec
	 * @param clss
	 * @param args
	 */
	private static void AddTab(TabsViewPagerFragmentActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo)
	{
		// Attach a Tab view factory to the spec
		tabSpec.setContent(activity.new TabFactory(activity));
		tabHost.addTab(tabSpec);
	}


	@Override
	public void onTabChanged(String tag)
	{
		// TabInfo newTab = this.mapTabInfo.get(tag);
		int pos = this.mTabHost.getCurrentTab();
		this.mViewPager.setCurrentItem(pos);
	}


	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void onPageSelected(int position)
	{
		// TODO Auto-generated method stub
		this.mTabHost.setCurrentTab(position);
	}


	@Override
	public void onPageScrollStateChanged(int state)
	{
		// TODO Auto-generated method stub

	}
}
