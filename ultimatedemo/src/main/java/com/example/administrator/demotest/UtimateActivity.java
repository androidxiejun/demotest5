package com.example.administrator.demotest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.itemTouchHelper.SimpleItemTouchHelperCallback;
import com.marshalchen.ultimaterecyclerview.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class UtimateActivity extends AppCompatActivity {
    private UltimateRecyclerView recyclerView;
    private View headerView;
    private List<String> data = new ArrayList<>();
    private StringAdapter adapter;
    private Handler handler;
    private Context context;
    private CustomGridView mListView;
    private MyListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utimate);
        context = this;
        handler = new Handler();
        initView();
        initData();
        mListView = (CustomGridView) findViewById(R.id.list_view);
        listAdapter = new MyListAdapter();
        mListView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        headerView = LayoutInflater.from(this).inflate(R.layout.head_view, null);
//        imageView= (ImageView) headerView.findViewById(R.id.img);
        adapter = new StringAdapter(data);
        recyclerView.setAdapter(adapter);
//        StickyRecyclerHeadersDecoration stickyRecyclerHeadersDecoration = new StickyRecyclerHeadersDecoration(adapter);
//        recyclerView.addItemDecoration(stickyRecyclerHeadersDecoration);
         recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
         recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        //控制Item的滑动以及删除
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter){
            //这个方法还有别的方法可以重载  可以控制如滑动删除等功能


            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;//控制拖动的方向   这里设置了智能上下拖动交换位置
                final int swipeFlags = ItemTouchHelper.RIGHT ;//控制滑动删除的方向  这里设置了只能右滑删除
//                final int swipeFlags = ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT;//左右滑删除

                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return false;
//                return super.isItemViewSwipeEnabled();//这里控制开启或关闭item是否可以滑动删除的功能
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return super.isLongPressDragEnabled();//控制长按拖动功能
            }
        };
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView.mRecyclerView);
//        设置头部一定要在setAdapter后面，因为这个操作会调用adapter的方法来显示头部，如果adapter为null，则出错
        recyclerView.setParallaxHeader(headerView);
        recyclerView.enableDefaultSwipeRefresh(true);//开启下拉刷新
        recyclerView.reenableLoadmore();
        recyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {

            }
        });
        recyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            data.add("ITEM" + i);
        }
    }

    private void initView() {
        recyclerView = (UltimateRecyclerView) findViewById(R.id.recycler_view);
    }

    public class StringViewHolder extends UltimateRecyclerviewViewHolder {
        public TextView textView;

        public StringViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                textView = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }

    class StringAdapter extends UltimateViewAdapter<StringViewHolder> {
        private List<String> stringList;

        public StringAdapter(List<String> stringList) {
            this.stringList = stringList;
        }

        @Override
        public StringViewHolder newFooterHolder(View view) {
            return null;
        }

        @Override
        public StringViewHolder newHeaderHolder(View view) {
            return null;
        }

        @Override
        public StringViewHolder onCreateViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.string_item, null);
            return new StringViewHolder(view, true);
        }

        @Override
        public int getAdapterItemCount() {
            return stringList == null ? 0 : stringList.size();
            //这里返回的是你的item的个数  不包括头部和加载view
        }

        @Override
        public long generateHeaderId(int position) {

            return -1;
            //为每一项item生成头部的View，如果返回－1，则不生成，假如多个连续的item返回同一个id，
            //则只会生成一个头部View
            //这里提取position的第一个数作为id
            //1 10 11 12 14等返回的id是一样的  为1
        }

        @Override
        public void onBindViewHolder(StringViewHolder holder, int position) {
            //一定要加这个判断  因为UltimateRecyclerView本身有加了头部和尾部  这个方法返回的是包括头部和尾部在内的
            if (position < getItemCount() && (customHeaderView != null ? position <= stringList.size() : position < stringList.size()) && (customHeaderView != null ? position > 0 : true)) {
//                position  -= customHeaderView==null?0:1;
//                holder.textView.setText(stringList.get(position));
            }
            holder.textView.setText(stringList.get(position));
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.string_item, null);
            return new StringViewHolder(view, true);
            //初始化item的头部布局  这里为了方便 就直接用StringViewHolder,实际使用可以使用不同于item的布局
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (customHeaderView != null) {
                position -= 1;
            }

//            ((StringViewHolder)holder).textView.setText("header  "+(position+"").charAt(0));
//            ((StringViewHolder)holder).textView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            //绑定item头部view的数据，这里提取每个view的position的第一个数来作为头部显示数据
            //即10 11 12  13  14这些  返回的是1
            //20 21 22等是2
        }

        @Override
        public void onItemMove(int fromPosition, int toPosition) {
            swapPositions(data, fromPosition, toPosition);
            //如果开启的拖动移动位置的功能
            //要重写这个方法  因为如果不重写  交换的只是view的位置，数据的位置没有交换 一拖动，就会变成原来的样子
            super.onItemMove(fromPosition, toPosition);
        }

        @Override
        public void onItemDismiss(int position) {
            data.remove(position);//控制删除的
            super.onItemDismiss(position);
        }
    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(context);
            textView.setText("DEMO");
            textView.setTextSize(40);
            return textView;
        }
    }
}
