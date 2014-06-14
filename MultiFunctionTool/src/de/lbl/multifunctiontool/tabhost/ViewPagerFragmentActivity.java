package de.lbl.multifunctiontool.tabhost;
import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import de.lbl.multifunctiontool.R;

public class ViewPagerFragmentActivity extends FragmentActivity
{

    private PagerAdapter mPagerAdapter;
	List<Fragment> fragments;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.viewpager_layout);
        //initialsie the pager
        this.initialisePaging();
    }

    private void initialisePaging() {

        fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, Tab1.class.getName()));
        fragments.add(Fragment.instantiate(this, Tab2.class.getName()));
        fragments.add(Fragment.instantiate(this, Tab3.class.getName()));
        this.mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
        //
        ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);
    }
}
