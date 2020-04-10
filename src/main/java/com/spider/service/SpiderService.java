package com.spider.service;

import com.spider.mod.SpiderInformation;
import com.spider.web.WebSocketAPI;

import javax.websocket.Session;

public interface SpiderService {

    public void downFiles(SpiderInformation si, Session session, WebSocketAPI webSocketAPI);
}
