package com.zh.loveme.config

import com.zh.loveme.MainActivity

/**
 * @Description: 类说明，描述
 * @Author: zh浩
 * @CreateDate: Configs$ 15:21$
 * @Version: 1.0
 */
 object  Configs{
     var listData:ArrayList<String> = ArrayList()
    fun setContentText(text: String) {
        listData.clear()
        val split = text.split(" ")
        listData.addAll(split)
    }
}