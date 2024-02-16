package com.jit.funchemistry.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseActivity;

import butterknife.BindView;

/**
 * @author created by XiaNingIf
 * @data 2021/4/3
 */
public class InformationActivity extends BaseActivity {
    @BindView(R.id.ivNavigateBefore7)
    ImageView mBack;

    @Override
    protected void initEvent() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_information;
    }
}
