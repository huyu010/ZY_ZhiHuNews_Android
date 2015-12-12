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
    private List<Latest> items;
    private Latest latest;
    private Kanner kanner;
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
        lv_news.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (lv_news != null && lv_news.getChildCount() > 0) {
                    boolean enable = (firstVisibleItem == 0) && (view.getChildAt(firstVisibleItem).getTop() == 0);
                    ((MainActivity) mActivity).setSwipeRefreshEnable(enable);
                }
            }
        });


        return view;
    }

    @Override
    protected void initData() {
        super.initData();
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
                        List<Latest.StoriesEntity> storiesEntities = latest.getStories();
                        Latest.StoriesEntity topic = new Latest.StoriesEntity();
                        topic.setType(Constant.TOPIC);
                        topic.setTitle("今日热闻");
                        storiesEntities.add(0, topic);
                        lv_news.setAdapter(new NewsItemAdapter(mActivity, storiesEntities));
                    }
                });
            }
        });
    }

}
