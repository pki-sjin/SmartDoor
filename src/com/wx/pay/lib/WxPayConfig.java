package com.wx.pay.lib;

public class WxPayConfig {
	// =======【基本信息设置】=====================================
	/*
	 * 微信公众号信息配置 APPID：绑定支付的APPID（必须配置） MCHID：商户号（必须配置）
	 * KEY：商户支付密钥，参考开户邮件设置（必须配置） APPSECRET：公众帐号secert（仅JSAPI支付的时候需要配置）
	 */
	public static String APPID = "";
	public static String MCHID = "";
	public static String KEY = "";
	public static String APPSECRET = "";

	// =======【证书路径设置】=====================================
	/*
	 * 证书路径,注意应该填写绝对路径（仅退款、撤销订单时需要）
	 */
	public static String SSLCERT_PATH = "";
	public static String SSLCERT_PASSWORD = "";

	// =======【支付结果通知url】=====================================
	/*
	 * 支付结果通知回调url，用于商户接收支付结果
	 */
	public static String NOTIFY_URL = "";
	
	public static String OAUTH_REDIRECT_URL = "";

	// =======【商户系统后台机器IP】=====================================
	/*
	 * 此参数可手动配置也可在程序中自动获取
	 */
	public static String IP = "";

	// =======【代理服务器设置】===================================
	/*
	 * 默认IP和端口号分别为0.0.0.0和0，此时不开启代理（如有需要才设置）
	 */
	public static String PROXY_URL = "";

	// =======【上报信息配置】===================================
	/*
	 * 测速上报等级，0.关闭上报; 1.仅错误时上报; 2.全量上报
	 */
	public static int REPORT_LEVENL = 1;
}
