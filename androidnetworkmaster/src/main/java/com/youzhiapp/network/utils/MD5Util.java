package com.youzhiapp.network.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**
	 * MD5加密(32位)
	 *
	 * @param string
	 * @return
	 */
	public static String toMD5_32(String plainText)
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
			e.printStackTrace();
			return null;
		}
	}
	/**
	 *  MD5加密(32位)
	 * @param plainText
	 * @return
	 */
	public static String toMd5_16(String plainText) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString().substring(8, 24);
//		   System.out.println("mdt 16bit: " + buf.toString().substring(8, 24));
//		   System.out.println("md5 32bit: " + buf.toString() );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取请求key
	 * 请求地址的MD5加密的前10位再MD5
	 * @return
	 */
	public static String getMD5Key(String urlPath){
		return toMD5_32(toMD5_32(urlPath).substring(0, 10));

	}

}
