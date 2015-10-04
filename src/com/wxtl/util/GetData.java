package com.wxtl.util;

import com.lxt2.protocol.cbip20.CbipSubmit;
import com.lxt2.protocol.cbip20.CbipSubmitMms;

public class GetData {

	// 主动发送方法调用
	public static CbipSubmit getSmsSubmit(String mobile, String content,
			int prcid, long seqid) throws Exception {
		// 创建短信对象
		CbipSubmit smsSubmit = new CbipSubmit();
		// 提交流水号,即短信序列号,自定义，不要超过18位
		smsSubmit.setClientSeq(seqid);
		// smsSubmit.setClientSeq(-1000000000123456789L);
		// 子号，默认填写"",即不允许扩展
		smsSubmit.setSrcNumber("");
		// 优先级，默认填写1
		smsSubmit.setMessagePriority((byte) 1);
		// 是否需要状态报告 0 不需要 1 需要
		smsSubmit.setReportType((short) 1);

		System.out.println("===短信长度===" + content.length());
		// 下发消息格式 15 普通短信 32 长短信
		// 现在默认用32，由平台根据通道判断
		if (content.length() > 70) {
			smsSubmit.setMessageFormat((byte) 32);
			System.out.println("===设置为32===");
		} else {
			smsSubmit.setMessageFormat((byte) 32);
			System.out.println("===设置为15===");
		}

		// 产品编号，即通道组编号
		smsSubmit.setProductID(prcid);
		// 如果手机号码组包个数超出限制(100以内)，可能抛出异常
		smsSubmit.setDestMobiles(mobile);
		// 如果短信内容超出长度限制(1000字以内)，可能抛出异常
		smsSubmit.setContentString(content);
		// 扩展字段内容
		smsSubmit.setCustomString("");
		// 短信签名，默认不起作用
		smsSubmit.setSignString("");

		/*
		 * 以下的参数默认可以不需要
		 */
		// 过期时间戳(1970年1月1日0点0分0秒0毫秒为起点的时间值，精确到毫秒)(如不填写默认使用当前时间+1天作为过期时间戳)
		// smsSubmit.setOverTime(System.currentTimeMillis());
		// 定时发送时间戳(1970年1月1日0点0分0秒0毫秒为起点的时间值，精确到毫秒,定时时间不能晚于当前时间+1天)（如不填写默认为立即发送）
		// smsSubmit.setSendTime(System.currentTimeMillis());
		// 下行临时订购关系（默认填写 ""）
		// smsSubmit.setLinkID("");
		// 下发批次（默认填写 0）（保留字段，暂无意义）
		// smsSubmit.setSendGroupID(120);
		/*
		 * 消息的下发类型 0 免费下发 1 按条下发 2 包月下发 3 订阅请求 4 取消请求 5 包月扣费 （保留字段，暂无意义）
		 */
		// smsSubmit.setMessageType((byte) 0);
		// 设置批量发送时的手机号码，可不用设置，程序自动判断，但是必须小于规定数
		// smsSubmit.setDestMobileCount((short) 1);
		// System.out.println(smsSubmit);
		return smsSubmit;

	}

	public static CbipSubmitMms getMmsSubmit(String mobile, byte[] data,
			int prcid, long seqid) throws Exception {
		CbipSubmitMms mmsSubmit = new CbipSubmitMms();

		// 添加图片类型文件
		String picFileName = "qrcode.jpg";
		mmsSubmit.addMessageContent(picFileName, data);
		
		mmsSubmit.setMessageFormat((byte) 30);
		mmsSubmit.addDestMobile(mobile);
		mmsSubmit.setCustomString("MMS");
		mmsSubmit.setMessagePriority((byte) 1);
		mmsSubmit.setProductID(prcid);
		mmsSubmit.setReportType((byte) 1);
		mmsSubmit.setSequenceId(seqid);
		mmsSubmit.setSrcNumber("");
		mmsSubmit.setSubjectContentString("二维码");

		return mmsSubmit;

	}
}
