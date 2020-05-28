package com.zh.loveme.activity.fragment

import android.os.Bundle
import android.view.View
import com.zh.loveme.R
import com.zh.loveme.activity.PreviewActivity
import com.zh.loveme.base.LibBaseFragment
import kotlinx.android.synthetic.main.activity_wirte_text.*
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @Description: 类说明，描述
 * @Author: zh浩
 * @CreateDate: WirteTextFragment$ 13:39$
 * @Version: 1.0
 */
class WirteTextFragment : LibBaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.activity_wirte_text
    }

    override fun initView(view: View) {
        ed_text.setText(getText())
    }
    override fun initData() {}
    override fun initEvent() {
      
    }

    public fun getText(): String{
        var content = ed_text.text.toString()
        if (content.isNullOrEmpty()){
            content = getLoadText()
        }
        return content
    }
    private fun getLoadText(): String {
        val instream = mActivity.assets.open("test.txt")
        var content = ""
        if (instream != null) {
            val inputreader = InputStreamReader(
                instream
            )
            val buffreader = BufferedReader(inputreader)
            var line = ""
            // 分行读取
            while (buffreader.readLine()?.let {itt-> line = itt }!=null) {
                content += "$line \n"
            }
            instream.close()
        }
        return content
    }

    companion object {
        fun newInstance(): WirteTextFragment {
            val args = Bundle()
            val fragment = WirteTextFragment()
            fragment.arguments = args
            return fragment
        }
    }
}