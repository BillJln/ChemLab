package com.jit.funchemistry.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import com.jit.funchemistry.R
import com.jit.funchemistry.base.BaseActivity
import com.jit.funchemistry.extension.dp2px
import com.jit.funchemistry.extension.setDrawable
import com.jit.funchemistry.utils.GlobalUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.etPassWord
import kotlinx.android.synthetic.main.activity_register.etPhoneNumberOrEmail
import kotlinx.android.synthetic.main.layout_title_bar.*
import kotlinx.android.synthetic.main.layout_title_register_bar.*

/**
 * @author created by XiaNingIf
 * @data 2021/3/23
 */
class RegisterActivity : BaseActivity() {
    override fun initPresenter() {}

    override fun initView() {
        setStatusBarBackground(R.color.purple_register)
        initTitleBar()
    }

    private fun initTitleBar() {
        ivNavigateBefore2!!.setOnClickListener{finish()}
        tvTitle2!!.isSelected = true
        titleRegisterBar!!.layoutParams.height = resources.getDimensionPixelSize(R.dimen.actionBarSizeSecondary)
        titleRegisterBar!!.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        val padding = dp2px(9f)
        ivNavigateBefore2!!.setPadding(padding, padding, padding, padding)
        ivNavigateBefore2!!.setImageResource(R.drawable.ic_close_white_24dp)
        tvRightText2!!.visibility = View.VISIBLE
        tvRightText2!!.text = GlobalUtil.getString(R.string.string_concern)
        tvRightText2!!.setTextColor(ContextCompat.getColor(this@RegisterActivity, R.color.white))
        tvRightText2!!.textSize = 12f
        etPhoneNumberOrEmail!!.setDrawable(ContextCompat.getDrawable(this, R.drawable.ic_person_white_18dp), 18f, 18f, 0)
        etPassWord!!.setDrawable(ContextCompat.getDrawable(this, R.drawable.ic_password_white_lock_18dp), 18f, 18f, 0)
        etEmail!!.setDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_email_18dp), 18f, 18f, 0)
        etPhoneNumber!!.setDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_phone_android_18dp), 18f, 18f, 0)
        concernPassWord!!.setDrawable(ContextCompat.getDrawable(this, R.drawable.ic_password_white_lock_18dp), 18f, 18f, 0)
        divider2!!.visibility = View.GONE
    }
    override fun getLayoutResId(): Int {
        return R.layout.activity_register
    }

}