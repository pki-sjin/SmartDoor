package org.zero.tool;

import java.security.MessageDigest;

public class Util {

	public static String encrypt(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bt = password.getBytes("UTF-8");
			md.update(bt);
			byte[] data = md.digest();
			return bytes2HexString(data);
		} catch (Exception e) {
			e.printStackTrace();
			return password;
		}
	}

	private static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}
}