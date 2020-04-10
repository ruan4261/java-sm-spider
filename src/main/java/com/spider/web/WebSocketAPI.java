package com.spider.web;

import com.spider.mod.SpiderInformation;
import com.spider.service.SpiderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.IOException;

@ServerEndpoint(value = "/ws")
@Component
@Scope("prototype")
public class WebSocketAPI {

    //服务上下文
    private static ApplicationContext applicationContext;

    //爬虫服务
    private SpiderService spiderService;

    //会话
    private Session session;

    //强制熔断
    public volatile int shutdown = 1;

    /**
     * 开启连接
     *
     * @param session
     */
    @OnOpen
    public void open(Session session) {
        if (spiderService == null) spiderService = applicationContext.getBean(SpiderService.class);
        this.session = session;
        try {
            this.session.getBasicRemote().sendText("成功连接到小蜘蛛，您现在可以发送命令。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 收到客户端信息
     *
     * @param message
     */
    @OnMessage
    public void message(String message) {
        if (message.equals("shutdown")) {
            if (this.shutdown == 0)
                this.shutdown = 1;
            return;
        }
        SpiderInformation si = new SpiderInformation(message);
        new File(si.getSavePath()).mkdirs();
        File par = new File(si.getFileLogPath()).getParentFile();
        if (!par.exists()) par.mkdirs();
        this.shutdown = 0;
        spiderService.downFiles(si, this.session, this);
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void close() {
        try {
            this.session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注入上下文
     *
     * @param applicationContext
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketAPI.applicationContext = applicationContext;
    }
}
