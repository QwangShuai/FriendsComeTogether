package com.youzhiapp.network.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Base64Util
{
	/**
	 * BASE64解密
	 * @param key
	 * @return
	 */
	public static String decryptBASE64(String key) {               

        return new String(Base64.decode(key, Base64.DEFAULT));
    }               
                  
    /**         
     * BASE64加密   
     * @param key          
     * @return          
     * @throws Exception          
     */              
    public static String encryptBASE64(String key) {
        return Base64.encodeToString(key.getBytes(), Base64.DEFAULT);               
    }
	/**
	 * Bitmap 转换为base64加密字符串
	 * 
	 * @param bitmap
	 * @return
	 */
	public static String bitmapToBase64(Bitmap bitmap)
	{
		String result = null;
		ByteArrayOutputStream baos = null;
		try
		{
			if (bitmap != null)
			{
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (baos != null)
				{
					baos.flush();
					baos.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 将base64字符解密为bitmap
	 * 
	 * @param string
	 * @return
	 */
	public static Bitmap base64ToBitmap(String string)
	{
		byte[] bytes = Base64.decode(string, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	/**
	 * 将文件加密为base64字符串
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String FileToBase64(String filePath) throws IOException
	{
		File file = new File(filePath);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length() + 100];
		int length = in.read(buffer);
		return Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
	}
	
	/**
	 * 将base64字符串解析为文件
	 * @param base64
	 * @param filePath
	 * @return
	 */
	public static File base64ToFile(String base64 , String filePath)
	{
		return getFileFromBytes(Base64.decode(base64, Base64.DEFAULT), filePath);
	} 
	/**
	 * 将字节转换为文件存放制定路径
	 * @param b
	 * @param outputFile
	 * @return
	 */
	
	public static File getFileFromBytes(byte[] b, String outputFile) {
	      BufferedOutputStream stream = null;
	       File file = null;
	       try {
	      file = new File(outputFile);
	           FileOutputStream fstream = new FileOutputStream(file);
	           stream = new BufferedOutputStream(fstream);
	           stream.write(b);
	       } catch (Exception e) {
	           e.printStackTrace();
	      } finally {
	          if (stream != null) {
	               try {
	                  stream.close();
	               } catch (IOException e1) {
	                  e1.printStackTrace();
	              }
	          }
	      }
	       return file;
	   }
	

}
