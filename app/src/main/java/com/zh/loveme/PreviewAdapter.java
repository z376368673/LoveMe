package com.zh.loveme;

import android.Manifest;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Description: 类说明，描述
 * @Author: zh浩
 * @CreateDate: PreviewAdapter 15:39
 * @Version: 1.0
 */
public class PreviewAdapter extends BaseMultiAdapter {


    public PreviewAdapter(Context context) {
        super(context);
        addItemView(0, R.layout.item_text);
    }

    @Override
    public void addData(@NonNull Object data) {
        super.addData(data);
        notifyItemInserted(getData().size()-1);//注意这里

    }

    @Override
    public int resultItemType(Object obj) {
        return 0;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view, int viewType) {
        BaseViewHolder holder = new TextViewHolder(view);
        return holder;
    }

    class TextViewHolder extends BaseViewHolder<String> {
        private TextView tv_text;

        public TextViewHolder(View itemView) {
            super(itemView);
            tv_text = itemView.findViewById(R.id.tv_text);
        }

        @Override
        public void setData(String data, int position) {
            tv_text.setText(data);
        }
    }


}
