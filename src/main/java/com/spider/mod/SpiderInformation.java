package com.spider.mod;

import com.alibaba.fastjson.JSONObject;

/**
 * 单个爬虫信息
 */
public class SpiderInformation {

    //url开头部分
    String urlPrefix;
    //url结尾部分
    String urlSuffix;
    //增序
    int spiderStep;
    //是否前置补零
    boolean putZero;
    //关键词总长度(和补零搭配使用)
    int keywordLength;
    //头
    int sourceHeadNum;
    //尾
    int sourceLastNum;
    //单个资源下载失败后的操作 -1:退出整个程序 0:跳过此条资源 >0:重复此int值次数下载此资源
    int exceptionTactics;
    //保存到的文件位置
    String savePath;
    //文件名前缀
    String saveNamePrefix;
    //文件名后缀(用于确认文件格式，也可以不确认)
    String saveNameSuffix;
    //文件名日志路径
    String fileLogPath;

    public SpiderInformation() {
    }

    public SpiderInformation(String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            this.urlPrefix = jsonObject.getString("urlPrefix");
            this.urlSuffix = jsonObject.getString("urlSuffix");
            this.spiderStep = jsonObject.getIntValue("spiderStep");
            this.putZero = jsonObject.getBoolean("putZero");
            this.keywordLength = jsonObject.getIntValue("keywordLength");
            this.sourceHeadNum = jsonObject.getIntValue("sourceHeadNum");
            this.sourceLastNum = jsonObject.getIntValue("sourceLastNum");
            this.exceptionTactics = jsonObject.getIntValue("exceptionTactics");
            this.savePath = jsonObject.getString("savePath");
            this.saveNamePrefix = jsonObject.getString("saveNamePrefix");
            this.saveNameSuffix = jsonObject.getString("saveNameSuffix");
            this.fileLogPath = jsonObject.getString("fileLogPath");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public SpiderInformation(String urlPrefix, String urlSuffix, int spiderStep, boolean putZero, int keywordLength, int sourceHeadNum, int sourceLastNum, int exceptionTactics, String savePath, String saveNamePrefix, String saveNameSuffix, String fileLogPath) {
        this.urlPrefix = urlPrefix;
        this.urlSuffix = urlSuffix;
        this.spiderStep = spiderStep;
        this.putZero = putZero;
        this.keywordLength = keywordLength;
        this.sourceHeadNum = sourceHeadNum;
        this.sourceLastNum = sourceLastNum;
        this.exceptionTactics = exceptionTactics;
        this.savePath = savePath;
        this.saveNamePrefix = saveNamePrefix;
        this.saveNameSuffix = saveNameSuffix;
        this.fileLogPath = fileLogPath;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }

    public int getSpiderStep() {
        return spiderStep;
    }

    public void setSpiderStep(int spiderStep) {
        this.spiderStep = spiderStep;
    }

    public boolean isPutZero() {
        return putZero;
    }

    public void setPutZero(boolean putZero) {
        this.putZero = putZero;
    }

    public int getKeywordLength() {
        return keywordLength;
    }

    public void setKeywordLength(int keywordLength) {
        this.keywordLength = keywordLength;
    }

    public int getSourceHeadNum() {
        return sourceHeadNum;
    }

    public void setSourceHeadNum(int sourceHeadNum) {
        this.sourceHeadNum = sourceHeadNum;
    }

    public int getSourceLastNum() {
        return sourceLastNum;
    }

    public void setSourceLastNum(int sourceLastNum) {
        this.sourceLastNum = sourceLastNum;
    }

    public int getExceptionTactics() {
        return exceptionTactics;
    }

    public void setExceptionTactics(int exceptionTactics) {
        this.exceptionTactics = exceptionTactics;
    }

    public String getSavePath() {
        //确认保存位置正确
        if (savePath.lastIndexOf("/") != savePath.length() - 1 &&
                savePath.lastIndexOf("\\") != savePath.length() - 1) {
            savePath = savePath + "/";
        }
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSaveNamePrefix() {
        return saveNamePrefix;
    }

    public void setSaveNamePrefix(String saveNamePrefix) {
        this.saveNamePrefix = saveNamePrefix;
    }

    public String getSaveNameSuffix() {
        return saveNameSuffix;
    }

    public void setSaveNameSuffix(String saveNameSuffix) {
        this.saveNameSuffix = saveNameSuffix;
    }

    public String getFileLogPath() {
        return fileLogPath;
    }

    public void setFileLogPath(String fileLogPath) {
        this.fileLogPath = fileLogPath;
    }
}
