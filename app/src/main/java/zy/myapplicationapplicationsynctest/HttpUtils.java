package zy.myapplicationapplicationsynctest;

/**
 * Created by ZhichaoYang on 12/5/15.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.SyncStateContract;

import com.loopj.android.http.*;

import java.io.Console;
import java.io.File;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;

public class HttpUtils {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, ResponseHandlerInterface responseHandler) {
        client.get(Constant.BASEURL + url, responseHandler);
    }
    public static void getImage(String url, ResponseHandlerInterface responseHandler){
        client.get(url, responseHandler);
    }
    public static boolean isNetworkConnected(Context context) {
        if(context != null){
            ConnectivityManager mConnectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo != null){
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}
