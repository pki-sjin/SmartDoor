package com.wxtl.userImpl;

import com.lxt2.javaapi.ClientEngine;

public class MessageClientEngine {

	private static ClientEngine client;

	public static ClientEngine getClientEngine() {
		if (client == null) {
			client = new ClientEngine(new RespReceiver(), new ReportReceiver(),
					new DeliverReceiver());
		}
		return client;
	}
}
