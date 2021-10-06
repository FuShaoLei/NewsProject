package com.fushaolei.project_android.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.fushaolei.project_android.module.common.CommonFragment;

import java.util.List;

public class NormalPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> title;
    private List<Integer> number;
    private int type;

    public NormalPagerAdapter(@NonNull FragmentManager fm, List<String> title, List<Integer> number, int type) {
        super(fm);
        this.title = title;
        this.number = number;
        this.type = type;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return CommonFragment.getInstance(number.get(position), type);
    }

    @Override
    public int getCount() {
        return title == null ? 0 : title.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
