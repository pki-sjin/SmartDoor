package com.wxtl.userImpl;

import com.lxt2.javaapi.IReceiver;
import com.lxt2.protocol.cbip20.CbipDeliver;

public class DeliverReceiver implements IReceiver<CbipDeliver> {

	public void receive(CbipDeliver deliver) {
		String content = null;
		String mobile = null;

		mobile = deliver.getSrcMobile();
		content = deliver.getContentString();

		System.out.println("收到上行-" + "手机号码:" + mobile + "上行内容:" + content
				+ "目的号码:" + deliver.getDestNumber() + "编码"
				+ deliver.getMessageFormat());

		// 上行触发下行示例，已注释，防止客户没看这里，导致不必要下发短信
		if (content.equals("1234567890")) {
			// SendSmsZD
			// .sendSms(mobile,
			// "感谢您使用本公司产品PHS无线上网卡，您购买的为我司正品【无线天利】",1);
		} else {
			// SendSmsZD.sendSms(mobile,
			// "您的短信验证没有成功，请核对您的短信验证码，可能您购买的并非我司正品【无线天利】",1);
		}
	}

}
