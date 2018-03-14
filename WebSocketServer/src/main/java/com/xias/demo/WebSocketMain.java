package com.xias.demo;

import java.net.InetSocketAddress;

import org.java_websocket.server.WebSocketServer;

public class WebSocketMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int port = 8888;

		WebSocketServer server = new SocketServer(new InetSocketAddress("172.24.42.12", port));
		System.out.println(server.getAddress());
		server.start();
	}

}
