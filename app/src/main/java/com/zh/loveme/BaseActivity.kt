package com.zh.loveme

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @Description:    Activity基类
 *
 * @Author:         zh浩
 * @CreateDate:     2020/5/27 15:18
 * @Version:        1.0
 */
open class BaseActivity : AppCompatActivity() {
    lateinit var mContext: Context;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

}
