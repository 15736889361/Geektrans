package com.blindingdark.geektrans.trans.baidu.bean;

import android.content.SharedPreferences;

import com.blindingdark.geektrans.trans.baidu.BaiduSettingsString;

/**
 * Created by BlindingDark on 2016/8/22 0022.
 */
public class BaiduSettings {
    String baiduAppId = "";
    String baiduKey = "";
    String baiduFrom = "";
    String baiduTo = "";

    public BaiduSettings(String baiduAppId, String baiduKey, String baiduFrom, String baiduTo) {
        this.baiduAppId = baiduAppId;
        this.baiduKey = baiduKey;
        this.baiduFrom = baiduFrom;
        this.baiduTo = baiduTo;
    }

    public BaiduSettings(SharedPreferences preferences) {
        this.baiduAppId = preferences.getString(BaiduSettingsString.baiduAppId, "");
        this.baiduKey = preferences.getString(BaiduSettingsString.baiduKey, "");
        this.baiduFrom = preferences.getString(BaiduSettingsString.baiduFrom, "auto");
        this.baiduTo = preferences.getString(BaiduSettingsString.baiduTo, "zh");
    }

    public String getBaiduAppId() {
        return baiduAppId;
    }

    public void setBaiduAppId(String baiduAppId) {
        this.baiduAppId = baiduAppId;
    }

    public String getBaiduKey() {
        return baiduKey;
    }

    public void setBaiduKey(String baiduKey) {
        this.baiduKey = baiduKey;
    }

    public String getBaiduFrom() {
        return baiduFrom;
    }

    public void setBaiduFrom(String baiduFrom) {
        this.baiduFrom = baiduFrom;
    }

    public String getBaiduTo() {
        return baiduTo;
    }

    public void setBaiduTo(String baiduTo) {
        this.baiduTo = baiduTo;
    }
}
