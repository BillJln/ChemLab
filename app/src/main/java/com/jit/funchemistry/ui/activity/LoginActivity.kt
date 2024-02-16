package com.jit.funchemistry.ui.activity

import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import butterknife.BindView
import com.jit.funchemistry.R
import com.jit.funchemistry.base.BaseActivity
import com.jit.funchemistry.base.BaseApplication
import com.jit.funchemistry.base.BaseApplication.isLogin
import com.jit.funchemistry.extension.dp2px
import com.jit.funchemistry.extension.setDrawable
import com.jit.funchemistry.utils.DensityUtil
import com.jit.funchemistry.utils.GlobalUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_title_bar.*
import java.security.acl.Group

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
class LoginActivity : BaseActivity() {
//    @BindView(R.id.ivLogoText)
//    var ivLogeText: ImageView? = null
//
//    @BindView(R.id.tvLoginTips)
//    var tvLoginTips: TextView? = null
//
//    @BindView(R.id.etPhoneNumberOrEmail)
//    var etPhoneNumberOrEmail: EditText? = null
//
//    @BindView(R.id.etPassWord)
//    var etPassWord: EditText? = null
//
//    @BindView(R.id.tvUserLogin)
//    var tvUserLogin: TextView? = null
//
//    @BindView(R.id.tvUserRegister)
//    var tvUserRegister: TextView? = null
//
//    @BindView(R.id.ivWechat)
//    var ivWechat: ImageView? = null
//
//    @BindView(R.id.ivSina)
//    var ivSina: ImageView? = null
//
//    @BindView(R.id.ivQQ)
//    var ivQQ: ImageView? = null
//
//    @BindView(R.id.group)
//    var group: Group? = null
//
//    @BindView(R.id.tvDescription)
//    var tvDescription: TextView? = null
//
//    @BindView(R.id.tvUserAgreement)
//    var tvUserAgreement: TextView? = null
//
//    @BindView(R.id.ivNavigateBefore)
//    var ivNavigateBefore: ImageView? = null
//
//    @BindView(R.id.tvTitle)
//    var tvTitle: TextView? = null
//
//    @BindView(R.id.titleBar)
//    var titleBar: RelativeLayout? = null
//
//    @BindView(R.id.tvRightText)
//    var tvRightText: TextView? = null
//
//    @BindView(R.id.divider)
//    var divider: View? = null
    override fun initPresenter() {}
    override fun initView() {
        setStatusBarBackground(R.color.blue_login)
        initTitleBar()
    }

    override fun initEvent() {
        //var keyword = etPhoneNumberOrEmail.text

        tvUserRegister?.setOnClickListener(View.OnClickListener {_ ->
            val intent = Intent(this,RegisterActivity::class.java)
            this.startActivity(intent)
        })
        tvUserLogin?.setOnClickListener(View.OnClickListener{_->
            isLogin = true
            val intent = Intent(this,MainActivity::class.java)
            this.startActivity(intent)
        })
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    private fun initTitleBar() {
        ivNavigateBefore!!.setOnClickListener { finish() }
        tvTitle!!.isSelected = true
        titleBar!!.layoutParams.height = resources.getDimensionPixelSize(R.dimen.actionBarSizeSecondary)
        titleBar!!.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        val padding = dp2px(9f)
        ivNavigateBefore!!.setPadding(padding, padding, padding, padding)
        ivNavigateBefore!!.setImageResource(R.drawable.ic_close_white_24dp)
        tvRightText!!.visibility = View.VISIBLE
        tvRightText!!.text = GlobalUtil.getString(R.string.forgot_password)
        tvRightText!!.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.white))
        tvRightText!!.textSize = 12f
        etPhoneNumberOrEmail?.setDrawable(ContextCompat.getDrawable(this, R.drawable.ic_person_white_18dp), 18f, 18f, 0)
        etPassWord?.setDrawable(ContextCompat.getDrawable(this, R.drawable.ic_password_white_lock_18dp), 18f, 18f, 0)
        divider!!.visibility = View.GONE
    }
}