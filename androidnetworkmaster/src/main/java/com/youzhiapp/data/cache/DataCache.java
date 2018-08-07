package com.youzhiapp.data.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;

import com.youzhiapp.network.application.BaseApplication;
import com.youzhiapp.network.utils.SystemUtil;

/**
 * 数据库缓存http请求json
 * @author slg
 *
 */
public class DataCache {
	/*sql:
	 * DROP TABLE IF EXISTS "main"."url_cache";
	 *	CREATE TABLE "url_cache"( id  INTEGER PRIMARY KEY AUTOINCREMENT, url  TEXT UNIQUE NOT NULL,data TEXT UNIQUE NOT NULL,timestamp TimeStamp NOT NULL DEFAULT (datetime('now','localtime')) ,validity  INTEGER NOT NULL DEFAULT -1);
	 */
	private static final int DB_VER = 1;
    public static final String dbName = "http_cache.db";
    private static final String TABLE = "url_cache";
    private static final String ID = "id";
    private static final String URL = "url";
    private static final String DATA = "data";
    private static final String TIMESTAMP = "timestamp";
    private static final String VALIDITY ="validity";//有效时间长度，DEFAULT_VALIDITY为一直有效
    public static final long AWAY_VALIDITY = -1l;
    private static SQLiteDatabase database;
    private static File dbFile;
    private static DataCache dataCache;
 
    private static final String TAG = DataCache.class.getSimpleName();

    private  DataCache(Context context) {
        init(context);
    }

    public static DataCache getInstance(Context context) {
        if (dbFile==null||dataCache == null||!dbFile.exists()) {
            dataCache = new DataCache(context);
        }
        return dataCache;
    }

    /**
     * 获取数据库实例
     * @return
     */
    public  DataCache getDataCache() {
        return dataCache;
    }


    private boolean init(Context context) {
        try {                
        	String path = getSDPath(context)+ File.separator + dbName;
        	boolean copy = MakeFile(context,path,dbName);
        	dbFile = new File(path);
        	database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);      	
        	if(!copy){        		       	
        		int oldVer = database.getVersion();
        		if(oldVer<DB_VER){       			
        			dbFile.delete();
        			MakeFile(context,path,dbName);
        			database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);  
        			database.setVersion(DB_VER);  
        		}
        	}else{
        		database.setVersion(DB_VER);  
        	}
        	
            return dbFile != null;
        } catch (Exception e) {
            Log.e(TAG, "初始化数据库错误：=========="+e.getMessage());
        }
      
        return false;
    }
    /**
     * 保存缓存
     * @param url
     * @param jsontext
     * @return
     */
    public synchronized long saveToCache(String url, String jsontext){
    	return saveToCache(url,jsontext,BaseApplication.INSTANCE.getCacheTime());
    }
    

    /**
     * 保存缓存
     * @param url
     * @param jsontext
     * @return
     */
    public synchronized long saveToCache(String url, String jsontext,long validity) {
       
        if (database == null) {
            return -1l;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(URL, toMD5_32(url));
            contentValues.put(DATA, jsontext);
            contentValues.put(TIMESTAMP, System.currentTimeMillis() );
            contentValues.put(VALIDITY, validity);
            long id=  database.insertWithOnConflict(TABLE,DATA,contentValues,SQLiteDatabase.CONFLICT_REPLACE);
            return id;
        } catch (Exception e) {
        	Log.e(TAG, "保存数据库缓存错误：=========="+e.getMessage());
        }
		return -1l;


    }
    /**
     * 查询缓存
     * @param url 请求url
     * @return
     */

    public synchronized CacheEntity queryCache(String url) {
    	CacheEntity entity =null;
        if (database == null) {
            return entity;
        }
        try {
        	Log.v(TAG, "CacheKey========="+url);
        	url=toMD5_32(url);       	       	
            Cursor cursor = database.rawQuery("SELECT "+DATA+","+VALIDITY+","+TIMESTAMP+",("+System.currentTimeMillis()+"-"+TIMESTAMP+") AS cha  FROM "+TABLE+" WHERE url == ?", new String[]{url});
            String jsonStr = "";
            long cha = 0l;
            long validity = 0l;
            if (cursor.moveToFirst()) {
            	entity= new CacheEntity();
            	entity.setUpdataTime(cursor.getLong(cursor.getColumnIndex(TIMESTAMP)));
            	jsonStr =  cursor.getString(cursor.getColumnIndex(DATA));
            	validity= cursor.getLong(cursor.getColumnIndex(VALIDITY));            	
            	cha = cursor.getLong(cursor.getColumnIndex("cha"));
            	entity.setJsonStr(jsonStr);
                if(validity==AWAY_VALIDITY ||cha>validity){//没过期
                	entity.setValid(false);
                }else{
                	entity.setValid(true);
                }
            }
			Log.v(TAG, "CacheJson====="+jsonStr);
        } catch (Exception e) {
        	Log.e(TAG, "查询数据库缓存错误：=========="+e.getMessage());
        } 

        return entity;
    }

    /**
     * 清除数据库缓存
     */
    public void clearCache() {
        if (database != null) {
            try {
                database.delete(TABLE, null, null);
                database.setTransactionSuccessful();
            } catch (Exception e) {
            	Log.e(TAG, "清除数据库缓存错误：=========="+e.getMessage());
            } finally {
               database.endTransaction();
               if( dbFile.exists()){
            	   dbFile.delete();
               }
               dataCache = null;
            }
        }
    }
    /**
     * 获取数据所在路径
     * @param context
     */
	public static String getDBPath(Context context){
		return getSDPath(context)+dbName;
	}
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (database != null) {
            try {
                database.close();
            } catch (Exception e) {
            	Log.e(TAG, "回收数据库缓存错误：=========="+e.getMessage());
            }

        }
    }
    /**
	 * MD5加密(32位)
	 * 
	 * @return
	 */
	public  String toMD5_32(String plainText)
	{
		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try
		{
			byte[] btInput = plainText.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			Log.e(TAG, "数据库缓存MD5错误：=========="+e.getMessage());
			
			return null;
		}
	}
	/**
	 * 获取缓存所在目录文件夹
	 * @return
	 */
	private static String getSDPath(Context context){ 
		String path = "/";
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在 
		if (sdCardExist) { 
			path = context.getExternalCacheDir().getPath();//获取跟目录 
		} else{
			path = context.getCacheDir().getPath();
		} 
		Log.i(TAG, "http数据库缓存路径====="+path);
		return path;
	}
	/**
	 * 释放assets中的文件
	 * @param context
	 * @param fileFullName 释放后的路径包含文件名
	 * @param fileName assets中的文件名
	 */
 	private boolean MakeFile(Context context,String fileFullName,String fileName)
    {
    	File file = new File(fileFullName);
    	boolean is = !file.exists();
    	if(is)
    	{
    		try 
    		{
				InputStream in = context.getAssets().open(fileName);
				OutputStream out = new FileOutputStream(file);
				byte[] buf = new byte[1024];    
				int len;
				while ((len = in.read(buf)) > 0)    
				{    
	                 out.write(buf, 0, len);    
				}
				out.flush();
				in.close();				
	            out.close();    
			}
    		catch (IOException e) 
			{
				e.printStackTrace();
			}
    		
    	}
    	return is;
    }
	
    
//  private void initTable() {
//      try {
//              database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE + " (" +
//                      ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                      URL + "  TEXT UNIQUE NOT NULL, " +
//                      DATA + " TEXT UNIQUE NOT NULL," +
//                      TIMESTAMP +" TimeStamp NOT NULL DEFAULT (datetime('now','localtime')) ,"+
//                      VALIDITY + "  INTEGER NOT NULL DEFAULT "+DEFAULT_VALIDITY+
//                      " );");
//
//          } catch (Exception e) {
//          	Log.e(TAG, "初始化数据缓存表错误：=========="+e.getMessage());
//
//       }
//   }
}
