package cn.trinea.android.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageCompressUtils {

	private static final String TAG = "ImageUtils";

	public static Bitmap loadImage(Activity activity, ImageView imageView, Uri uri) {
		
		if (uri != null && !uri.toString().startsWith("content://")) {
			return null;
		}
		Bitmap bitmap = null;
		try {
			// MediaStore
			String[] pojo = { MediaStore.Images.Media.DATA };
			Cursor cursor = activity.managedQuery(uri, pojo, null, null, null);
			if (cursor != null && cursor.moveToFirst()) {
				ContentResolver cr = activity.getContentResolver();
				int colunm_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				String path = cursor.getString(colunm_index);
				

				if (path.endsWith(".jpg") || path.endsWith(".png")) {
					//bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
					bitmap = getCompredBitmap(path);
					// imageView.setImageBitmap(getBitmap(path, 100, 200));
					imageView.setImageBitmap(bitmap);
				} else {
					Toast.makeText(activity, "Please select image...",
							Toast.LENGTH_SHORT).show();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static Bitmap getCompredBitmap(String srcPath) {
		return compressDef(getResizeBitmap(srcPath));
	}

	/**
	 * get bitmap compressed to 100kb
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getCompredDefBitmap(Bitmap bitmap) {
		return getCompredBitmap(bitmap, 100);
	}

	/**
	 * get bitmap compressed
	 * 
	 * @param bitmap
	 * @param target_size
	 *            unit is kb
	 * @return
	 */
	public static Bitmap getCompredBitmap(Bitmap bitmap, int target_size) {
		return compress(bitmap, target_size);
	}

	private static Bitmap compressDef(Bitmap bitmap) {
		return compress(bitmap, 100);
	}

	/**
	 * The cycle compression specify meet the specified target size(100kb)
	 * 
	 * @param bitmap
	 * @param target_size
	 *            unit is kb
	 * @return
	 */
	private static Bitmap compress(Bitmap bitmap, int target_size) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		int quality = 100;
		int length = baos.toByteArray().length / 1024;
		
		// The cycle compression specify meet the specified size
		while (length > target_size) {
			baos.reset();
			quality -= 10;
			bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
			length = baos.toByteArray().length / 1024;
		}
		if (!bitmap.isRecycled()) {
			bitmap.recycle();
		}
		byte[] data = baos.toByteArray();
		int cp_length = data.length;
		Log.i(TAG, "compressed image length = " + cp_length / 1024 + " kb");
		return BitmapFactory.decodeByteArray(data, 0, cp_length);
	}

	/**
	 * change image size: width and height
	 * 
	 * @param options
	 * @return
	 */
	private static int getSimpleSize(BitmapFactory.Options options) {
		int w = options.outWidth;
		int h = options.outHeight;
		float hh = 800f;
		float ww = 480f;
		int scale = 1;
		if (w > h && w > ww) {
			scale = (int) (options.outWidth / ww);
		} else if (w < h && h > hh) {
			scale = (int) (options.outHeight / hh);
		}
		if (scale <= 0)
			scale = 1;
		return scale;
	}

	public static Bitmap getResizeBitmap(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// start parse image only width and height, set
		// options.inJustDecodeBounds to true
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		newOpts.inSampleSize = getSimpleSize(newOpts);
		newOpts.inJustDecodeBounds = false;
		// restart parse image all info, set options.inJustDecodeBounds as true
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return bitmap;
	}

	/**
	 * save bitmap 100%
	 * @param bitmap
	 * @return save path
	 */
	public static String saveImage(Bitmap bitmap){
		return saveImage(bitmap, 100);
	}
	
	/**
	 * save bitmap 50%
	 * @param bitmap
	 * @return save path
	 */
	public static String saveImage50(Bitmap bitmap){
		return saveImage(bitmap, 50);
	}
	
	/**
	 * save bitmap 25%
	 * @param bitmap
	 * @return save path
	 */
	public static String saveImage25(Bitmap bitmap){
		return saveImage(bitmap, 25);
	}

	/**
	 * save bitmap
	 * @param bitmap
	 * @param quality bitmap quality(1-100)
	 * @return
	 */
	public static String saveImage(Bitmap bitmap, int quality) {

		String fileName = "slim_" + System.currentTimeMillis() + ".jpg";
//		String filePath = Conf.getImagePath() + fileName;
		String filePath = "/"+ fileName;
		String path = null;
		try {
			File file = new File(filePath);
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)) {
				//Log.d(TAG, "saveImage seccess: fileName= " + filePath + ", quality = " + quality);
				out.flush();
				out.close();
				path = filePath;
			} else {
				Log.d(TAG, "saveImage fail: fileName= " + filePath);
			}
		} catch (Exception e) {
			Log.d(TAG, "saveImage Exception: " + e);
			e.printStackTrace();
		}
		return path;
	}

	public static void transImage(String fromFile, String toFile, int width,
			int height, int quality) {
		try {
			Bitmap bitmap = BitmapFactory.decodeFile(fromFile);
			int bitmapWidth = bitmap.getWidth();
			int bitmapHeight = bitmap.getHeight();
			// scaling proportion
			float scaleWidth = (float) width / bitmapWidth;
			float scaleHeight = (float) height / bitmapHeight;
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			// resizeBitmap
			Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0,
					bitmapWidth, bitmapHeight, matrix, false);
			// save file
			File myCaptureFile = new File(toFile);
			FileOutputStream out = new FileOutputStream(myCaptureFile);
			if (resizeBitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)) {
				out.flush();
				out.close();
			}
			// Release memory resources
			if (!bitmap.isRecycled()) {
				bitmap.recycle();
			}
			if (!resizeBitmap.isRecycled()) {
				resizeBitmap.recycle();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 直接载入图片
	 * 
	 * @param path
	 * @return
	 */
	public static Bitmap getBitmap(String path) {
		return BitmapFactory.decodeFile(path);
	}

	/**
	 * get resized (kb) bitmap
	 * 
	 * @param path
	 * @param size
	 *            unit is kb
	 * @return
	 */
	public static Bitmap getBitmap(String path, int inSampleSize) {
		Options op = new Options();
		op.inSampleSize = inSampleSize;
		return BitmapFactory.decodeFile(path, op);
	}

	/**
	 * 按寬高壓縮載入圖片
	 * 
	 * @param path
	 * @param width
	 * @param heigh
	 * @return
	 */
	public static Bitmap getBitmap(String path, int width, int heigh) {
		Options op = new Options();
		op.inJustDecodeBounds = true;
		Bitmap bmp = BitmapFactory.decodeFile(path, op);
		int xScale = op.outWidth / width;
		int yScale = op.outHeight / heigh;
		op.inSampleSize = xScale > yScale ? xScale : yScale;
		op.inJustDecodeBounds = false;
		bmp = BitmapFactory.decodeFile(path, op);
		return bmp;
	}

	public static Bitmap getBitmap2(String path, int width, int height) {
		BitmapFactory.Options op = new BitmapFactory.Options();
		op.inJustDecodeBounds = true;
		Bitmap bmp = BitmapFactory.decodeFile(path, op);

		// 编码后bitmap的宽高,bitmap除以屏幕宽度得到压缩比
		int widthRatio = (int) FloatMath.ceil(op.outWidth / (float) width);
		int heightRatio = (int) FloatMath.ceil(op.outHeight / (float) height);

		if (widthRatio > 1 && heightRatio > 1) {
			if (widthRatio > heightRatio) {
				// 压缩到原来的(1/widthRatios)
				op.inSampleSize = widthRatio;
			} else {
				op.inSampleSize = heightRatio;
			}
		}
		op.inJustDecodeBounds = false;
		bmp = BitmapFactory.decodeFile(path, op);
		return bmp;
	}

	/**
	 * 截取可见屏幕部分视图
	 * @param activity
	 * @return
	 */
	public static Bitmap shotScreen(Activity activity) {
		View view = activity.getWindow().getDecorView();
		Display display = activity.getWindowManager().getDefaultDisplay();
		view.layout(0, 0, display.getWidth(), display.getHeight());
		// 允许当前窗口保存缓存信息，这样getDrawingCache()方法才会返回一个Bitmap
		view.setDrawingCacheEnabled(true);
		Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache());
		return bmp;
	}
	
	/**
	 * 截取view的根层可见屏幕部分的视图
	 * @param activity
	 * @return
	 */
	public static Bitmap getRootViewBitmap(View view) {
		return shotViewBitmap(view.getRootView());
	}
	
	/**
	 * 截取可见屏幕部分的view视图
	 * @param activity
	 * @return
	 */
	public static Bitmap shotViewBitmap(View v) {
		v.clearFocus();
        v.setPressed(false);
        Bitmap bmp = null;
        try {
        	v.layout(0, 0, v.getWidth(), v.getHeight());
    		// 允许当前窗口保存缓存信息，这样getDrawingCache()方法才会返回一个Bitmap
    		v.setDrawingCacheEnabled(true);
    		v.buildDrawingCache();
    		bmp = Bitmap.createBitmap(v.getDrawingCache());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bmp;
	}
	
	/**
	 * convert view to integral bitmap and return it
	 * 获取view的完整视图图片（即使没有显示出来的部分）
	 * @param view : view or layout
	 * @return
	 */
	public static Bitmap convertBitmap(View view){
        return convertViewToBitmap(view, view.getWidth(), view.getHeight());
	}

	/**
	 * convert measured view to integral bitmap and return it
	 * 通过计算的方法宽高后，获取view的完整视图图片（即使没有显示出来的部分）
	 * @param view : view or layout
	 * @return
	 */
	public static Bitmap convertMeasureBitmap(View view){
		view.measure(MeasureSpec.makeMeasureSpec(view.getWidth(), MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec(view.getHeight(), MeasureSpec.AT_MOST));
        return convertViewToBitmap(view, view.getMeasuredWidth(), view.getMeasuredHeight());
	}
	
	/**
	 * convert view to bitmap according to with and height
	 * @param view
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap convertViewToBitmap(View view, int width, int height){
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));
        return bitmap;
    }
}
