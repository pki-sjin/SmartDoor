package com.wxtl.userImpl;

import com.lxt2.javaapi.IRespReceiver;
import com.lxt2.protocol.IApiSubmit;
import com.lxt2.protocol.IApiSubmitResp;

public class RespReceiver implements IRespReceiver {

	public void receive(IApiSubmit submit, IApiSubmitResp resp) {
		// 可以不用转换类，呵呵
		System.out.println("发送数据:" + "submit:" + submit);
		System.out.println("响应数据:" + "resp: " + resp);
	}

}
