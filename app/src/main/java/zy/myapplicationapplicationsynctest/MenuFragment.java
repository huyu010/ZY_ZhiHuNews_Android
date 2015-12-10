package zy.myapplicationapplicationsynctest;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements OnClickListener {

    private ListView lv_item;
    private TextView tv_download, tv_main;
    private List<NewListItem> items;
    private Handler handler = new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.downsidemenu, container, false);
        tv_download = (TextView) view.findViewById(R.id.tv_download);
        tv_download.setOnClickListener(this);
        tv_main = (TextView) view.findViewById(R.id.tv_main);
        tv_main.setOnClickListener(this);
        lv_item = (ListView) view.findViewById(R.id.lv_item);
        getItems();


        return view;

    }

    private void getItems() {
        items = new ArrayList<NewListItem>();
        HttpUtils.get(Constant.THEMES, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray itemArray = response.getJSONArray("others");
                    for (int i = 0; i < itemArray.length(); i++) {
                        NewListItem newListItem = new NewListItem();
                        JSONObject itemObject = itemArray.getJSONObject(i);
                        newListItem.setTitle(itemObject.getString("name"));
                        newListItem.setId(itemObject.getString("id"));
                        items.add(newListItem);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            lv_item.setAdapter(new NewsTypeAdapter());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public class NewsTypeAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.menu_item, parent, false);
            }
            TextView tv_item = (TextView) convertView.findViewById(R.id.tv_item);
            tv_item.setText(items.get(position).getTitle());


            return convertView;
        }
    }


}
