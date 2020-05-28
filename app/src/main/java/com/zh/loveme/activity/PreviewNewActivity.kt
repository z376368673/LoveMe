package com.zh.loveme.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zh.loveme.BaseActivity
import com.zh.loveme.R
import com.zh.loveme.config.Configs
import kotlinx.android.synthetic.main.activity_preview_new.*


/**
 * @Description:    预览界面
 *
 * @Author:         zh浩
 * @CreateDate:     2020/5/27 15:18
 * @Version:        1.0
 */
class PreviewNewActivity : BaseActivity() {
    lateinit var textList: ArrayList<String>
    var index = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_new)

        Configs.listData.let {
            textList = it
        }
        if (textList.isNullOrEmpty()) {
            Toast.makeText(mContext, "文字解析出错", Toast.LENGTH_SHORT)
            return
        }
        initView()
        initPermissions()
    }

    private fun initPermissions() {
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.INTERNET
        ).subscribe {
            if (it) {
                //申请的权限全部允许
                Toast.makeText(mContext, "允许了权限!", Toast.LENGTH_SHORT).show()
                initData()
            } else {
                //只要有一个权限被拒绝，就会执行
                Toast.makeText(mContext, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initView() {
        val loadAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha)
        ctv_view.setText(Configs.textData.toString(), loadAnimation, 500)
    }

    private fun initData() {
        addData(index, textList)
    }

    private fun addData(index: Int, list: List<String>) {
        if (index < textList.size) {

            this.index++
            handler.sendEmptyMessageDelayed(1, 2000)
        } else {
            Toast.makeText(mContext, "谢谢观看", Toast.LENGTH_SHORT)
        }
    }

    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                addData(index, textList)
            }
        }
    }


    companion object {
        fun jumpActivity(context: Context, text: String) {
            val intent = Intent(context, PreviewNewActivity::class.java)
            intent.putExtra("data", text)
            ContextCompat.startActivity(context, intent, null)
        }

        fun jumpActivity(context: Context) {
            val intent = Intent(context, PreviewNewActivity::class.java)
            ContextCompat.startActivity(context, intent, null)
        }
    }
}
