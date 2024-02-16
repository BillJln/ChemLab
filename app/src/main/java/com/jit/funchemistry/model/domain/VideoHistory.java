package com.jit.funchemistry.model.domain;

import android.provider.ContactsContract;

import java.util.Date;

/**
 * @author XiaNingIf
 * @date 2021/3/30
 */
public class VideoHistory {
    private String VideoName;
    private long HistoryTime;
    private int ID;
    private String HistoryTitle;
    private String Maker;
    private int pic_url;
    private int videoID;

    public VideoHistory(String videoName, long historyTime, int ID, String historyTitle, String maker, int pic_url, int videoID) {
        VideoName = videoName;
        HistoryTime = historyTime;
        this.ID = ID;
        HistoryTitle = historyTitle;
        Maker = maker;
        this.pic_url = pic_url;
        this.videoID = videoID;
    }

    public int getVideoID() {
        return videoID;
    }

    public void setVideoID(int videoID) {
        this.videoID = videoID;
    }

    public VideoHistory(String videoName, long historyTime, int ID, String historyTitle, String maker, int pic_url) {
        VideoName = videoName;
        HistoryTime = historyTime;
        this.ID = ID;
        HistoryTitle = historyTitle;
        Maker = maker;
        this.pic_url = pic_url;
    }

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String videoName) {
        VideoName = videoName;
    }

    public long getHistoryTime() {
        return HistoryTime;
    }

    public void setHistoryTime(long historyTime) {
        HistoryTime = historyTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHistoryTitle() {
        return HistoryTitle;
    }

    public void setHistoryTitle(String historyTitle) {
        HistoryTitle = historyTitle;
    }

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String maker) {
        Maker = maker;
    }

    public int getPic_url() {
        return pic_url;
    }

    public void setPic_url(int pic_url) {
        this.pic_url = pic_url;
    }
}
