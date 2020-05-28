package com.zh.loveme

import android.Manifest
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment
import com.yalantis.contextmenu.lib.MenuObject
import com.yalantis.contextmenu.lib.MenuParams
import com.zh.loveme.activity.PreviewActivity
import com.zh.loveme.activity.fragment.WirteTextFragment
import com.zh.loveme.base.LibBaseActivity
import com.zh.loveme.config.Configs
import com.zh.loveme.utils.ToastUtil

/**
 * @Description:    文字界面
 *
 * @Author:         zh浩
 * @CreateDate:     2020/5/27 15:18
 * @Version:        1.0
 */
class MainActivity : LibBaseActivity() {
    private lateinit var writeTextFragment: WirteTextFragment
    override fun initData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initEvent() {

    }

    override fun initView() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        writeTextFragment = WirteTextFragment.newInstance()
        beginTransaction.add(R.id.frameLayout, writeTextFragment, "write")
        beginTransaction.commit()
        initToolbar()
        initMenuFragment()
        initPermissions()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun initToolbar() {
        var toolbar: Toolbar = findViewById(R.id.toolbar)
        var tvToolbarTitle: TextView = findViewById(R.id.tvToolbarTitle)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        toolbar.apply {
            setNavigationIcon(R.mipmap.ic_back)
            setNavigationOnClickListener { onBackPressed() }
        }

        tvToolbarTitle.text = "Samantha"
    }

    private lateinit var contextMenuDialogFragment: ContextMenuDialogFragment
    private fun initMenuFragment() {
        val menuParams = MenuParams(
            actionBarSize = resources.getDimension(R.dimen.dp_56).toInt(),
            menuObjects = getMenuObjects(),
            isClosableOutside = false
        )
        contextMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams).apply {
            menuItemClickListener = { view, position ->
                if (position == 1) {
                    val beginTransaction = supportFragmentManager.beginTransaction()
                    val findFragmentByTag = supportFragmentManager.findFragmentByTag("write")
                    if (findFragmentByTag?.isAdded!!) {
                        beginTransaction.show(findFragmentByTag)
                    } else {
                        beginTransaction.add(R.id.frameLayout, writeTextFragment, "write")
                    }
                    beginTransaction.commit()
                } else if (position == 2) {
                    Configs.setContentText(writeTextFragment.getText())
                    PreviewActivity.jumpActivity(mContext)
                } else if (position == 3) {
                    val beginTransaction = supportFragmentManager.beginTransaction()
                    beginTransaction.add(R.id.frameLayout, WirteTextFragment.newInstance(), "write")
                    beginTransaction.commit()
                } else {

                }
//                ToastUtil.show(mActivity, "Clicked on position: $position")
            }
//            menuItemLongClickListener = { view, position ->
//                ToastUtil.show(mActivity,"Long clicked on position: $position")
//            }
        }
    }

    private fun getMenuObjects() = mutableListOf<MenuObject>().apply {
        val close = MenuObject().apply { setResourceValue(R.mipmap.icn_close) }
        val writeText = MenuObject("文字录入").apply { setResourceValue(R.mipmap.ic_write) }
        val preview = MenuObject("预览效果").apply { setResourceValue(R.mipmap.ic_preview) }
        val config = MenuObject("效果配置").apply { setResourceValue(R.mipmap.ic_config) }

        add(close)
        add(writeText)
        add(preview)
        add(config)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (it.itemId) {
                R.id.context_menu -> {
                    showContextMenuDialogFragment()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (::contextMenuDialogFragment.isInitialized && contextMenuDialogFragment.isAdded) {
            contextMenuDialogFragment.dismiss()
        } else {
            finish()
        }
    }

    private fun showContextMenuDialogFragment() {
        if (supportFragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
            contextMenuDialogFragment.show(supportFragmentManager, ContextMenuDialogFragment.TAG)
        }
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
            } else {
                //只要有一个权限被拒绝，就会执行
                Toast.makeText(mContext, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        var listData = ArrayList<String>()


        fun jumpActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            ContextCompat.startActivity(context, intent, null)
        }
    }
}
