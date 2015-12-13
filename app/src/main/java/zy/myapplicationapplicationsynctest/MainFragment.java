package zy.myapplicationapplicationsynctest;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {

    private ListView lv_news;
    private MainNewsItemAdapter mAdapter;
    private Latest latest;
    private Before before;
    private Kanner kanner;
    private String date;
    private boolean isLoading = false;
    private Handler handler = new Handler();


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_news_layout, container, false);
        lv_news = (ListView) view.findViewById(R.id.lv_news);
        View header = inflater.inflate(R.layout.kanner, lv_news, false);
        kanner = (Kanner) header.findViewById(R.id.kanner);
        kanner.setOnItemClickListener(new Kanner.OnItemClickListener() {
            @Override
            public void click(View v, Latest.TopStoriesEntity entity) {

            }
        });
        lv_news.addHeaderView(header);
        mAdapter = new MainNewsItemAdapter(mActivity);
        lv_news.setAdapter(mAdapter);
        lv_news.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (lv_news != null && lv_news.getChildCount() > 0) {
                    boolean enable = (firstVisibleItem == 0) && (view.getChildAt(firstVisibleItem).getTop() == 0);
                    ((MainActivity) mActivity).setSwipeRefreshEnable(enable);
                    if (firstVisibleItem + visibleItemCount == totalItemCount && !isLoading) {
                        loadMore(Constant.BEFORE + date);
                    }
                }
            }
        });


        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        loadFirst();
    }

    private void loadMore(final String url) {
        isLoading = true;
        HttpUtils.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                before = gson.fromJson(responseString, Before.class);
                date = before.getDate();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        List<StoriesEntity> storiesEntities = before.getStories();
                        StoriesEntity topic = new StoriesEntity();
                        topic.setType(Constant.TOPIC);
                        topic.setTitle(convertDate(date));
                        storiesEntities.add(0, topic);
                        mAdapter.addList(storiesEntities);
                        isLoading = false;
                    }
                });
            }
        });
    }

    private void loadFirst() {
        isLoading = true;
        HttpUtils.get(Constant.LATESTNEWS, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                latest = gson.fromJson(responseString, Latest.class);
                kanner.setTopStoriesEntities(latest.getTop_stories());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        List<StoriesEntity> storiesEntities = latest.getStories();
                        StoriesEntity topic = new StoriesEntity();
                        topic.setType(Constant.TOPIC);
                        topic.setTitle("今日热闻");
                        storiesEntities.add(0, topic);
                        lv_news.setAdapter(new NewsItemAdapter(mActivity, storiesEntities));
                    }
                });
            }
        });
    }

    private String convertDate(String date) {
        String result = date.substring(0, 4);
        result += "年";
        result += date.substring(4, 6);
        result += "月";
        result += date.substring(6, 8);
        result += "日";
        return result;
    }


}
