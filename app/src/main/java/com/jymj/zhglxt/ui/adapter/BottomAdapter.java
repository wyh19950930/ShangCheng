package com.jymj.zhglxt.ui.adapter;


import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @package com.jymj.huitouzi.adapter
 * @fileName BottomAdapter
 * @date 2019/1/1814:17
 * @name qzw
 */
public class BottomAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    public BottomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }



}
