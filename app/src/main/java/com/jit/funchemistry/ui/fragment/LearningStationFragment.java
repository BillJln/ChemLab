package com.jit.funchemistry.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseFragment;

/**
 * @author XiaNingIf
 * @date 2021/3/30
 */
public class LearningStationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_learning_station,null);
        return inflate;
    }
}
