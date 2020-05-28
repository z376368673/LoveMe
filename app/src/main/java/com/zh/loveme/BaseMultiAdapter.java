package com.zh.loveme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author： zh浩
 * @创建时间:19-10-16 下午3:19
 * @描述：  多个不同样式的 item
 * @更新时间： 19-10-16 下午3:19
 * @更新说明： 无
 * @版本号 1.0
 */
public abstract class BaseMultiAdapter extends RecyclerView.Adapter {

    public Context mContext;
    protected OnItemClickListener mOnItemClickListener;
    private List<Object> mData;
    private Map<Integer, Integer> map;

    public BaseMultiAdapter(Context context) {
        mData = new ArrayList<>();
        map = new HashMap<>();
        mContext = context;
    }

    public void addData(@NonNull Object data) {
        mData.add(data);
    }

    public List getData() {
        return mData;
    }

    public void addData(@NonNull Collection data) {
        mData.addAll(data);
    }

    public void remove(int position) {
        mData.remove(position);
    }

    public boolean remove(Object data) {
        return mData.remove(data);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 添加多item 类型 和 layout
     *
     * @param type
     * @param layoutId
     */
    public void addItemView(int type, int layoutId) {

        map.put(type, layoutId);
    }

    @Override
    public int getItemViewType(int position) {
        return resultItemType(mData.get(position));
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        Integer layoutId = map.get(viewType);
        View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        holder = getViewHolder(view, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder baseHolder = (BaseViewHolder) holder;
        baseHolder.setData(mData.get(position), position);
    }


    /**
     * 根据数据类型 返回 type
     *
     * @param obj
     *
     * @return
     */
    public abstract int resultItemType(Object obj);

    /**
     * 实现 各自的  BaseViewHolder
     *
     * @param view
     * @param viewType
     *
     * @return
     */
    protected abstract BaseViewHolder getViewHolder(View view, int viewType);


    /**
     * @param <Data>
     */
    public abstract class BaseViewHolder<Data> extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);

        }

        public abstract void setData(Data data, int position);

    }


    public <T> void setOnItemClickListener(OnItemClickListener<T> mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 点击监听
     */
    public interface OnItemClickListener<T> {
        void onItemClickListener(BaseMultiAdapter adapter, View view, int position);
    }

}
