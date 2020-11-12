package com.src.Utils;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.src.Module.Category.view.CateFragment;
import com.src.Module.Favourite.view.FavouriteFragment;
import com.src.Module.Home.view.ExploreFragment;
import com.src.Module.User.view.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerNavigationAdapter extends FragmentStatePagerAdapter {

//    Code cũ để dự phòng
//private final List<Fragment> mFragmentList = new ArrayList<>();
//
//    public ViewPagerNavigationAdapter(FragmentManager manager) {
//        super(manager);
//    }
//    @Override
//    public Fragment getItem(int position) {
//        return mFragmentList.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return mFragmentList.size();
//    }
//
//    public void addFragment(Fragment fragment) {
//        mFragmentList.add(fragment);
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
//    }
//
    public ViewPagerNavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case (0):
                return new ExploreFragment();
            case (1):
                return new CateFragment();
            case (2):
                return new FavouriteFragment();
            case (3):
                return new ProfileFragment();
            default:
                return new ExploreFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
