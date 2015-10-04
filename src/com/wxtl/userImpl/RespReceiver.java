package com.wxtl.userImpl;

import com.lxt2.javaapi.IRespReceiver;
import com.lxt2.protocol.IApiSubmit;
import com.lxt2.protocol.IApiSubmitResp;
import com.lxt2.protocol.cbip20.CbipSubmit;
import com.lxt2.protocol.cbip20.CbipSubmitResp;

public class RespReceiver implements IRespReceiver {

	@Override
	public void receive(IApiSubmit submit, IApiSubmitResp resp) {
		// 可以不用转换类，呵呵
		CbipSubmitResp respNew = (CbipSubmitResp) resp;
		CbipSubmit submitNew = (CbipSubmit) submit;
		System.out.println("发送数据:" + "submit:" + submitNew);
		System.out.println("响应数据:" + "resp: " + "客户流水号="
				+ submitNew.getClientSeq() + " 系统流水号=" + respNew.getSysSeq()
				+ " 提交状态=" + respNew.getStatus());
	}

}
