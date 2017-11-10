package xyz.kkt.padc_assignment.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 11/8/2017.
 */

public class MovieTabAdapter extends FragmentStatePagerAdapter {

    private List<String> mTapTitile;
    private List<Fragment> mFragment;

    public MovieTabAdapter(FragmentManager fm) {
        super(fm);
        mTapTitile = new ArrayList<>();
        mFragment = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTapTitile.get(position);
    }

    public void addTap(Fragment fragment, String title) {
        mFragment.add(fragment);
        mTapTitile.add(title);
    }
}
