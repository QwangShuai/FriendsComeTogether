package com.nostra13.universalimageloader.use;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;

import cn.trinea.android.common.R;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;


/**
 * 图片异步加载工具类
 *
 * @author slg
 *         用法：imageLoader.displayImage(url, imageview);
 *         加载路径设置
 *         String imageUri = "http://site.com/image.png"; // from Web
 *         String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
 *         String imageUri = "content://media/external/audio/albumart/13"; // from content provider
 *         String imageUri = "assets://image.png"; // from assets
 *         String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
 *         <p>
 *         <p>
 *         <p>
 *         监听事件：
 *         imageLoader.displayImage(imageUrl, imageView, options, new ImageLoadingListener() {
 * @Override public void onLoadingStarted() {
 * //开始加载的时候执行
 * }
 * @Override public void onLoadingFailed(FailReason failReason) {
 * //加载失败的时候执行
 * }
 * @Override public void onLoadingComplete(Bitmap loadedImage) {
 * //加载成功的时候执行
 * }
 * @Override public void onLoadingCancelled() {
 * //加载取消的时候执行
 * },new ImageLoadingProgressListener() {
 * @Override public void onProgressUpdate(String imageUri, View view, int current,int total) {
 * //在这里更新 ProgressBar的进度信息
 * }
 * });
 * <p>
 * 简单的监听回调事件：
 * ImageSize mImageSize = new ImageSize(100, 100);  //图片大小 ，可以不指定
 * <p>
 * ImageLoader.getInstance().loadImage(imageUrl, mImageSize, new SimpleImageLoadingListener(){
 * @Override public void onLoadingComplete(String imageUri, View view,
 * Bitmap loadedImage) {
 * super.onLoadingComplete(imageUri, view, loadedImage);
 * mImageView.setImageBitmap(loadedImage);
 * }
 * <p>
 * });
 * 监听下载进度：
 * imageLoader.displayImage(imageUrl, mImageView, options, new SimpleImageLoadingListener(), new ImageLoadingProgressListener() {
 * @Override public void onProgressUpdate(String imageUri, View view, int current,
 * int total) {
 * <p>
 * }
 * });
 */
public class ImageLoaderUtil {
    /**
     * 实例化
     * 应该在application中实例化
     *
     * @param cachePath 缓存路径
     */
    public static void init(Context context, String cachePath) {
        File cacheDir = new File(cachePath);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) //你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)    //内存缓存大小
                .diskCacheSize(50 * 1024 * 1024)   //硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheFileCount(500) //缓存的文件数量
                .diskCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs()
                .build();//开始构建

        ImageLoader.getInstance().init(config);//全局初始化此配置

    }

    /**
     * 实例化
     * 应该在application中实例化
     */
    public static void init(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) //你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)    //内存缓存大小
                .diskCacheSize(50 * 1024 * 1024)   //硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheFileCount(500) //缓存的文件数量
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs()
                .build();//开始构建

        ImageLoader.getInstance().init(config);//全局初始化此配置

    }

    /**
     * 以下配置中的：
     * 1）.imageScaleType(ImageScaleType imageScaleType)  是设置 图片的缩放方式
     * 缩放类型mageScaleType:
     * <p>
     * EXACTLY :图像将完全按比例缩小的目标大小
     * EXACTLY_STRETCHED:图片会缩放到目标大小完全
     * IN_SAMPLE_INT:图像将被二次采样的整数倍
     * IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
     * NONE:图片不会调整
     * 2）.displayer(BitmapDisplayer displayer)   是设置 图片的显示方式
     * <p>
     * 显示方式displayer：
     * RoundedBitmapDisplayer（int roundPixels）设置圆角图片
     * FakeBitmapDisplayer（）这个类什么都没做
     * FadeInBitmapDisplayer（int durationMillis）设置图片渐显的时间
     * 　　　　　　　 	  SimpleBitmapDisplayer()正常显示一张图片
     */
    private static DisplayImageOptions options;

    public static DisplayImageOptions getPoints() {
        if (options == null) {
            synchronized (ImageLoaderUtil.class) {
                if (options == null) {
                    options = new DisplayImageOptions.Builder()
                            .showImageOnLoading(R.drawable.ic_stub) //设置图片在下载期间显示的图片
                            .showImageForEmptyUri(R.drawable.ic_stub)//设置图片Uri为空或是错误的时候显示的图片
                            .showImageOnFail(R.drawable.ic_stub)  //设置图片加载/解码过程中错误时候显示的图片
                            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                            .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片显示方式
                            .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                            //.decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置
                            //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                            //.preProcessor(BitmapProcessor preProcessor)  	//设置图片加入缓存前，对bitmap进行设置
                            //	.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                            .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                            //	.displayer(new RoundedBitmapDisplayer(10))//是否设置为圆角，弧度为多少
                            .build();//构建完成
                }
            }
        }

        return options;
    }

    private static DisplayImageOptions threeOptions;

    public static DisplayImageOptions getPoints(int load, int empty, int fail) {
        if (threeOptions == null) {
            synchronized (ImageLoaderUtil.class) {
                if (threeOptions == null) {
                    threeOptions = new DisplayImageOptions.Builder()
                            .showImageOnLoading(load) //设置图片在下载期间显示的图片
                            .showImageForEmptyUri(empty)//设置图片Uri为空或是错误的时候显示的图片
                            .showImageOnFail(fail)  //设置图片加载/解码过程中错误时候显示的图片
                            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                            .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片显示方式
                            .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                            .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                            .build();//构建完成
                }
            }
        }

        return threeOptions;
    }

    private static DisplayImageOptions noBgoptions;

    public static DisplayImageOptions getNoBgPoints() {
        if (noBgoptions == null) {
            synchronized (ImageLoaderUtil.class) {
                if (noBgoptions == null) {
                    noBgoptions = new DisplayImageOptions.Builder()
                            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                            .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片显示方式
                            .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                            .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                            .build();//构建完成
                }

            }
        }

        return noBgoptions;
    }

    private static DisplayImageOptions roundOptions;

    public static DisplayImageOptions getRoundPoints() {
        if (roundOptions == null) {
            synchronized (ImageLoaderUtil.class) {
                if (roundOptions == null) {
                    roundOptions = new DisplayImageOptions.Builder()
                            .showImageOnLoading(R.drawable.ic_stub) //设置图片在下载期间显示的图片
                            .showImageForEmptyUri(R.drawable.ic_stub)//设置图片Uri为空或是错误的时候显示的图片
                            .showImageOnFail(R.drawable.ic_stub)  //设置图片加载/解码过程中错误时候显示的图片
                            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                            .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片显示方式
                            .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                            .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                            .displayer(new RoundedBitmapDisplayer(10))//是否设置为圆角，弧度为多少
                            .build();//构建完成
                }
            }
        }

        return options;
    }

    /**
     * 清除内存缓存
     */
    public static void clearMemoryCache() {
        ImageLoader.getInstance().clearMemoryCache();
    }

    /**
     * 清除硬盘缓存
     */
    public static void clearDiscCache() {
        ImageLoader.getInstance().clearDiskCache();
    }

    /**
     * 不存在则创建路径
     *
     * @param path
     */
    private static void createPath(String path) {
        File file = new File(path);
        if (!file.exists()) file.mkdirs();
    }

}
