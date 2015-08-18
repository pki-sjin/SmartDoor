package org.zero.tool;

import java.security.MessageDigest;

import org.json.JSONObject;

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

	public static boolean sendSms(String httpPath, String cid, String pwd,
			String productid, String mobile, String content) {
		StringBuffer params = null;
		// 子号,默认留空，但需保留这个参数
		String lcode = "";
		// 流水号，控制在18位以内
		String ssid = String.valueOf(System.currentTimeMillis());
		// 短信编码，普通短信15，长短信32，，默认填写32
		String format = "32";
		// 短信签名，默认不起作用，但需保留这个参数
		String sign = "";
		// 自定义字段
		String custom = "";
		try {
			params = new StringBuffer();
			params.append("cid=").append(CodingUtils.encodeBase64URL(cid))
					.append("&").append("pwd=").append(
							CodingUtils.encodeBase64URL(pwd)).append("&")
					.append("productid=").append(
							CodingUtils.encodeURL(productid)).append("&")
					.append("mobile=").append(
							CodingUtils.encodeBase64URL(mobile)).append("&")
					.append("content=").append(
							CodingUtils.encodeBase64URL(content)).append("&")
					.append("lcode=").append(lcode).append("&").append("ssid=")
					.append(ssid).append("&").append("format=").append(format)
					.append("&").append("sign=").append(
							CodingUtils.encodeBase64URL(sign)).append("&")
					.append("custom=").append(CodingUtils.encodeURL(custom));
			String result = HttpUtil.sendPostRequestByParam(httpPath, params
					.toString());
			System.out.println(result);
			JSONObject json = new JSONObject(result);
			String status = json.getString("status");
			if ("0".equalsIgnoreCase(status)) {
				// 发送请求成功
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public static void fetchReport(String httpPath, String cid, String pwd) {
		StringBuffer params = new StringBuffer();
		// 一次获取的状态报告个数，建议100个，最大500 ， 按队列提取，取完就没有了
		int cnt = 1;
		try {
			params.append("cid=").append(CodingUtils.encodeBase64URL(cid))
					.append("&").append("pwd=").append(
							CodingUtils.encodeBase64URL(pwd)).append("&")
					.append("cnt=").append(cnt);
			String result = HttpUtil.sendPostRequestByParam(httpPath, params
					.toString());
			System.out.println(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		 sendSms("http://58.246.39.26:9195/communication/sendSms.ashx",
		 "9009",
		 "hzyp", "20120629", "18985624821", "123");
		fetchReport("http://58.246.39.26:9195/communication/fetchReports.ashx",
				"9009", "hzyp");
	}
}