package com.xias.demo.websocket.util;

import com.xias.demo.websocket.OnWebSocketConnectListener;

import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Created by XIAS on 2018/3/14.
 */

public class WebSocketClient extends org.java_websocket.client.WebSocketClient {

    private OnWebSocketConnectListener listener;

    public WebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public WebSocketClient(URI serverUri, OnWebSocketConnectListener listener) {
        super(serverUri);
        this.listener = listener;
    }

    public WebSocketClient(URI serverUri, Draft protocolDraft, OnWebSocketConnectListener listener) {
        super(serverUri, protocolDraft);
        this.listener = listener;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        listener.onOpen(handshakedata.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String message) {
        listener.onMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        listener.onClose(code +"  " + reason);
    }

    @Override
    public void onError(Exception ex) {
        listener.onError(ex.getMessage());
    }
}
