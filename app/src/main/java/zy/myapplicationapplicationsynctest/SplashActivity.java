package zy.myapplicationapplicationsynctest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class SplashActivity extends Activity {

    private ImageView iv_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.root_layout);
        iv_start = (ImageView) findViewById(R.id.iv_start);
        initImage();


    }

    private void initImage(){
        File dir = getFilesDir();
        final File imgFile = new File(dir, "start.jpg");
        if(imgFile.exists()){
            iv_start.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
        } else {
            iv_start.setImageResource(R.drawable.start); //当未能找到图片地址时显示图标
        }

        final ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(HttpUtils.isNetworkConnected(SplashActivity.this)) {
                    HttpUtils.get(Constant.START, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            try {
                                JSONObject jsonObject = new JSONObject(new String(responseBody));
                                String url = jsonObject.getString("img");
                                HttpUtils.getImage(url, new BinaryHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                                        saveImage(imgFile, binaryData);
                                        startActivity();
                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                                        startActivity();
                                    }
                                });


                        }

                        catch(
                        JSONException e
                        )

                        {
                            e.printStackTrace();
                        }
                    }

                    @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            startActivity();
                        }
                    });
                }else {
                    Toast.makeText(SplashActivity.this, "There is no network connected", Toast.LENGTH_LONG).show();
                    startActivity();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv_start.startAnimation(scaleAnim);
    }
    private void startActivity(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
    public void saveImage(File file, byte[] bytes){
        try {
            if(file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
