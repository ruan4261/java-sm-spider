package com.spider;

import com.spider.web.WebSocketAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebSocket
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpiderApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpiderApplication.class, args);
        WebSocketAPI.setApplicationContext(applicationContext);
    }

}
