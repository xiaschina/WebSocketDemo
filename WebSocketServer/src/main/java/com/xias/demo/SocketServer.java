package com.xias.demo;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class SocketServer extends WebSocketServer {

	public SocketServer(InetSocketAddress address) {
		super(address);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
		// TODO Auto-generated method stub
		if(arg0 != null)
			System.out.println("closed " + arg0.getRemoteSocketAddress() + " with exit code " + arg1 + " additional info: " + arg2);
	}

	@Override
	public void onError(WebSocket arg0, Exception arg1) {
		// TODO Auto-generated method stub
		if(arg0 != null){
			System.err.println("an error occured on connection " + arg0.getRemoteSocketAddress()  + ":" + arg1);
		}
	}

	@Override
	public void onMessage(WebSocket arg0, String arg1) {
		// TODO Auto-generated method stub
		if(arg0 != null) {
			System.out.println("received message from "	+ arg0.getRemoteSocketAddress() + ": " + arg1);
			arg0.send(arg1);
		}
	}

	@Override
	public void onOpen(WebSocket arg0, ClientHandshake arg1) {
		// TODO Auto-generated method stub
		if(arg0 != null)
			arg0.send("Welcome to the SocketServer!");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		System.out.println("SocketServer started successfully");
	}
}
