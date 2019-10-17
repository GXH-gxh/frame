package cn.smbms.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具
 * @author Administrator
 *
 */
public class PasswordUtil {

	
	/**
	 * Md5加密
	 * @param str 原始密码 
	 * @param salt	佐料
	 * @return
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		String password="0000000";
		//以用户名作为佐料
		System.out.println("Md5加密："+PasswordUtil.md5(password, "liming"));
	}
}
