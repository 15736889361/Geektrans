package com.blindingdark.geektrans.trans.youdao.bean;

/**
 * Created by BlindingDark on 2016/8/20 0020.
 */
public class ReadableTransResults {
    String translation = "";// 整句翻译
    String basic = "";//基本翻译
    String web = "";//网络翻译

    String divisionLine;

    YoudaoSettings youdaoSettings;

    public YoudaoSettings getYoudaoSettings() {
        return youdaoSettings;
    }

    public void setYoudaoSettings(YoudaoSettings youdaoSettings) {
        this.youdaoSettings = youdaoSettings;
        divisionLine =  youdaoSettings.divisionLine;
    }





    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {

        this.basic = basic;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {

        String result = "";

        if (!getTranslation().equals("")) {
            result += getTranslation();
        }
        if (!getBasic().equals("")) {
            result += "\n";
            result += divisionLine;
            result += "\n";
            result += getBasic();
        }
        if (!getWeb().equals("")) {
            result += "\n";
            result += divisionLine;
            result += "\n";
            result += getWeb();
        }

        return result;
    }
}
