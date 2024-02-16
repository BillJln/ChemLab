package com.jit.funchemistry.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jit.funchemistry.ui.fragment.LearningStationFragment;
import com.jit.funchemistry.ui.fragment.ThinkMapFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaNingIf
 * @date 2021/3/30
 */
public class LearningAdapter extends FragmentStateAdapter {
    List<Fragment> fragments = new ArrayList<>();
    public LearningAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public LearningAdapter(FragmentManager supportFragmentManager, Lifecycle lifecycle, List<Fragment> fragments) {
        super(supportFragmentManager, lifecycle);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
