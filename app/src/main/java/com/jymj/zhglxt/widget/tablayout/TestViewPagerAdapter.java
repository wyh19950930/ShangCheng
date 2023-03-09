package com.jymj.zhglxt.widget.tablayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by jiaojing on 2017/12/12.
 */

public class TestViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> testFragmentList;
    public TestViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> testFragmentList) {
        super(fm);
        this.testFragmentList = testFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return testFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return testFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
