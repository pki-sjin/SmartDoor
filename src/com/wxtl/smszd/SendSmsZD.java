package com.wxtl.smszd;

import com.lxt2.javaapi.ActiveSubmitSender;
import com.lxt2.protocol.cbip20.CbipSubmit;
import com.lxt2.protocol.cbip20.CbipSubmitMms;
import com.wxtl.userImpl.MessageClientEngine;
import com.wxtl.util.GetData;

public class SendSmsZD {
	public static boolean sendSms(final String mobile, final String content,
			final int productID, final long seqid) {

		if (MessageClientEngine.getClientEngine().isConnected()) {
			// 初始化主动发送器
			ActiveSubmitSender activeSubmitSender = new ActiveSubmitSender();

			try {
				// 实例化短信对象
				CbipSubmit smsSubmit = GetData.getSmsSubmit(mobile, content,
						productID, seqid);

				// 发送短信
				activeSubmitSender.sendSubmit(smsSubmit);
				return true;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean sendMms(final String mobile, final byte[] data,
			final int productID, final long seqid) {
		if (MessageClientEngine.getClientEngine().isConnected()) {
			// 初始化主动发送器
			ActiveSubmitSender activeSubmitSender = new ActiveSubmitSender();

			try {
				// 实例化短信对象
				CbipSubmitMms mmsSubmit = GetData.getMmsSubmit(mobile, data,
						productID, seqid);

				// 发送短信
				activeSubmitSender.sendSubmit(mmsSubmit);
				return true;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		} else {
			return false;
		}
	}

	// 将编码从数字转变成new String识别的字符串
	public static String getEncode(int i) {
		String s = null;
		switch (i) {
		case 0: // '\0'
			s = "US-ASCII";
			break;

		case 4: // '\004'
			s = "ISO-8859-1";
			break;

		case 8: // '\b'
			s = "iso-10646-ucs-2";
			break;

		case 15: // '\017'
			s = "GB2312";
			break;

		default:
			s = "ISO-8859-1";
			break;
		}
		return s;
	}
}
