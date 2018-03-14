package com.xias.demo.websocket;

/**
 * Created by XIAS on 2018/3/14.
 */

public interface OnWebSocketConnectListener {

    void onOpen(String msg);
    void onMessage(String msg);
    void onClose(String msg);
    void onError(String msg);
}
