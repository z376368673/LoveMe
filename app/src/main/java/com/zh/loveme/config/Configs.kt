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
     var textData:StringBuffer = StringBuffer("")
     fun setContentText(text: String) {
        textData.append(text)
        listData.clear()
        val split = text.split(" ")
        listData.addAll(split)
    }
}