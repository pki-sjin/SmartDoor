package org.zero.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.alipay.config.AlipayConfig;
import com.lxt2.javaapi.util.MsgConstant;
import com.wx.pay.lib.WxPayConfig;
import com.wxtl.userImpl.MessageClientEngine;

@WebListener
public class ApplicationListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {

	}

	public void contextInitialized(ServletContextEvent event) {
		// 初始化微信
		WxPayConfig.APPID = event.getServletContext().getInitParameter(
				"wx_appid");
		WxPayConfig.MCHID = event.getServletContext().getInitParameter(
				"wx_mchid");
		WxPayConfig.KEY = event.getServletContext().getInitParameter("wx_key");
		WxPayConfig.APPSECRET = event.getServletContext().getInitParameter(
				"wx_appsecret");
		WxPayConfig.SSLCERT_PATH = event.getServletContext().getInitParameter(
				"wx_sslcert_path");
		WxPayConfig.SSLCERT_PASSWORD = event.getServletContext()
				.getInitParameter("wx_sslcert_password");
		WxPayConfig.NOTIFY_URL = event.getServletContext().getInitParameter(
				"wx_notify_url");
		WxPayConfig.IP = event.getServletContext().getInitParameter("wx_ip");
		WxPayConfig.PROXY_URL = event.getServletContext().getInitParameter(
				"wx_proxy_url");

		// 初始化支付宝
		AlipayConfig.partner = event.getServletContext().getInitParameter(
				"alipay_pid");
		AlipayConfig.seller_email = event.getServletContext().getInitParameter(
				"alipay_email");
		AlipayConfig.key = event.getServletContext().getInitParameter(
				"alipay_key");

		AlipayConfig.notify_url = event.getServletContext().getInitParameter(
				"alipay_notify_url");
		AlipayConfig.return_url = event.getServletContext().getInitParameter(
				"alipay_return_url");

		// 初始化短信服务
		MsgConstant.clientId = Integer.parseInt(event.getServletContext()
				.getInitParameter("clientId")); // 程序ID
		MsgConstant.loginName = event.getServletContext().getInitParameter(
				"loginName"); // 用户名
		MsgConstant.password = event.getServletContext().getInitParameter(
				"password"); // 密码
		MsgConstant.serverIp = event.getServletContext().getInitParameter(
				"serverIp"); // 服务器IP
		MsgConstant.serverPort = Integer.parseInt(event.getServletContext()
				.getInitParameter("serverPort")); // 服务器端口

		/*
		 * 以下参数必须，默认值即可，如修改请慎重
		 */
		MsgConstant.connectNum = 2; // 连接数,建议填写2，不需修改
		MsgConstant.inBufferSize = 1024;
		MsgConstant.outBufferSize = 1024;
		MsgConstant.IdleTime = 10; // 心跳间隔时间
		MsgConstant.controlWindowsSize = 16; // 滑动窗口（条）
		MsgConstant.maxSendTime = 3; // 最大重发次数
		MsgConstant.clearTimeOut = 10000;// 滑动窗口清理时间，单位是ms
		MsgConstant.clearSleepTime = 10000; // 线程休眠时间，单位是ms
		MsgConstant.reconnectTime = 3000; // 失败重连接时间，单位是ms
		MsgConstant.reSend = false; // 是否重发，true是重发，false不重发,建议关闭重发！！！

		// 启动客户端将实例化的参数传递给客户端引擎
		System.out.println("启动引擎......");
		MessageClientEngine.getClientEngine().start();
		System.out.println("启动完成......");
	}
}
