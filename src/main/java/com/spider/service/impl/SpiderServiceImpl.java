package com.spider.service.impl;

import com.spider.mod.SpiderInformation;
import com.spider.service.SpiderService;
import com.spider.util.DownUtil;
import com.spider.web.WebSocketAPI;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 服务
 */
@Service("spiderService")
public class SpiderServiceImpl implements SpiderService {

    /**
     * 日常蜘蛛
     *
     * @param si
     */
    @Override
    public void downFiles(SpiderInformation si, Session session, WebSocketAPI webSocketAPI) {
        try {
            session.getBasicRemote().sendText("小蜘蛛已启动，加载资源队列，即将开始下载。");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //日志sb
        StringBuilder sb = new StringBuilder();
        int reDown = 0;
        //重复爬
        for (int i = si.getSourceHeadNum(); i <= si.getSourceLastNum(); i = i + si.getSpiderStep()) {
            if (webSocketAPI.shutdown == 1) {
                try {
                    session.getBasicRemote().sendText("Shutdown-->已安全退出。");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            StringBuilder str = new StringBuilder(Integer.toString(i));
            //补零
            if (si.isPutZero()) {
                if (str.length() < si.getKeywordLength()) {
                    for (int j = 0; j < si.getKeywordLength() - str.length();) {
                        str.insert(0, "0");
                    }
                }
            }
            //本地保存文件名
            String fileMainName = str.toString();
            //组合url
            str.insert(0, si.getUrlPrefix()).append(si.getUrlSuffix());
            try {
                //开爬
                try {
                    session.getBasicRemote().sendText("正在下载资源:" + str.toString() + "-->" + si.getSavePath() + si.getSaveNamePrefix() + fileMainName + si.getSaveNameSuffix());
                } catch (IOException ignored) {
                }
                DownUtil.httpDownload(str.toString(), si.getSavePath() + si.getSaveNamePrefix() + fileMainName + si.getSaveNameSuffix());
            } catch (IOException ex) {
                ex.printStackTrace();
                try {
                    if (si.getExceptionTactics() == -1) {
                        session.getBasicRemote().sendText("资源下载出错:" + str.toString() + "-->根据预定策略模式，已停止全部队列。");
                        break;
                    } else if (si.getExceptionTactics() == 0) {
                        session.getBasicRemote().sendText("资源下载出错:" + str.toString() + "-->根据预定策略模式，已跳过此资源。");
                        continue;
                    } else if (si.getExceptionTactics() > 0) {
                        if (si.getExceptionTactics() < reDown) {
                            i = i - si.getSpiderStep();
                            reDown++;
                            session.getBasicRemote().sendText("资源下载出错:" + str.toString() + "-->根据预定策略模式，重复尝试下载，即将进行本资源第" + reDown + "次尝试。");
                        } else {
                            session.getBasicRemote().sendText("资源下载出错:" + str.toString() + "-->根据预定策略模式，重复尝试下载，已进行" + reDown + "次尝试，根据策略，已跳过此资源。");
                        }
                        continue;
                    }
                } catch (IOException ignored) {
                }
            }
            sb.append("file '").append(si.getSaveNamePrefix()).append(fileMainName).append(si.getSaveNameSuffix()).append("'\r\n");
            reDown = 0;
        }

        //下载的文件名称log
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(si.getFileLogPath(), false)));
            out.write(sb.toString());
            out.flush();
            out.close();
            session.getBasicRemote().sendText("小蜘蛛结束了他的任务！您下载的资源请至" + si.getSavePath() + "文件夹中查看。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
