package zy.myapplicationapplicationsynctest;

import android.app.Application;
import android.content.Context;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by ZhichaoYang on 12/9/15.
 */
public class Kpplicaition extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context){
        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY-2)
                .memoryCache(new LruMemoryCache(2*1024*1024))
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .diskCache(new UnlimitedDiskCache(cacheDir)).writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }
}
