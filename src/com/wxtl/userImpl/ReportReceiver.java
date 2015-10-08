package com.wxtl.userImpl;

import com.lxt2.javaapi.IReceiver;
import com.lxt2.protocol.cbip20.CbipReport;

public class ReportReceiver implements IReceiver<CbipReport> {
	int i = 1;

	@Override
	public void receive(CbipReport report) {

		System.out.println("收到report-" + "流水号:" + report.getClientSeq()
				+ " 系统流水号:" + report.getSysSeq() + " 手机号:"
				+ report.getDestMobile() + " 错误码:" + report.getErrorCode()
				+ " 状态值:" + report.getStatus() + " 拆分序号:"
				+ report.getPkNumber() + " 拆分总数:" + report.getPkTotal());

		System.out.println("已收到状态报告条数=" + (i++));
	}
}
