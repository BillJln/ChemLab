package com.jit.funchemistry.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseActivity;

import butterknife.BindView;

/**
 * @author XiaNingIf
 * @date 2021/3/24
 */
public class SoftRelationActivity extends BaseActivity {
    @BindView(R.id.ivNavigateBefore3)
    ImageView mIvNavigateBefore3;

    @Override
    protected void initEvent(){
        mIvNavigateBefore3.setOnClickListener(new View.OnClickListener() {
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
        return R.layout.activity_soft_relation;
    }
}
