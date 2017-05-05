package com.beenvip.shedu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.beenvip.shedu.R;

/**
 * Created by ZH on 2017/3/31.
 * 497239511@qq.com
 */

public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {
    private View foot;
    private int totalItemCount;
    private int lastVisibleItem;
    private boolean isLoding;
    public MyLoadingListener listener;

    public LoadMoreListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        foot=inflater.inflate(R.layout.foot,null);
        foot.findViewById(R.id.footlayout).setVisibility(VISIBLE);
        this.addFooterView(foot);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount==lastVisibleItem && scrollState==SCROLL_STATE_IDLE){
                if (!isLoding){
                    isLoding=true;
                    //foot.findViewById(R.id.footlayout).setVisibility(VISIBLE);
                    if (listener!=null){
                        listener.onLoad();
                    }
                }
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem=firstVisibleItem+visibleItemCount;
        this.totalItemCount=totalItemCount;

    }
    public void loadComplate(){
        isLoding=false;
        //foot.findViewById(R.id.footlayout).setVisibility(GONE);
    }
    public void noMore(){
        foot.findViewById(R.id.footlayout).setVisibility(GONE);
        foot.findViewById(R.id.nomorelayout).setVisibility(VISIBLE);
    }


    public void setInterface(MyLoadingListener listener){
        this.listener=listener;
    }


    public interface MyLoadingListener{
         void onLoad();
    }
}
