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

		return smsSubmit;

	}

	public static CbipSubmitMms getMmsSubmit(String mobile, byte[] data,
			int prcid, long seqid) throws Exception {
		CbipSubmitMms mmsSubmit = new CbipSubmitMms();

		// 添加图片类型文件
		String picFileName = "qrcode.jpg";
		mmsSubmit.addMessageContent(picFileName, data);
		// 添加文本类型文件
		String txtFileName = "sign.txt";
		mmsSubmit.addMessageContent(txtFileName, "[国展中心]".getBytes("UTF-8"));

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
