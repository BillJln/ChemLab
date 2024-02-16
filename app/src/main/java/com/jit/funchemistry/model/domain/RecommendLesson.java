package com.jit.funchemistry.model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public class RecommendLesson {
    private int image_URL;
    private String lesson_Name;
    private String maker;
    private long watched_Number;
    private String video_URL;
    private int stringID;

    public RecommendLesson(int image_URL, String lesson_Name, String maker, long watched_Number, String video_URL, int stringID) {
        this.image_URL = image_URL;
        this.lesson_Name = lesson_Name;
        this.maker = maker;
        this.watched_Number = watched_Number;
        this.video_URL = video_URL;
        this.stringID = stringID;
    }

    public int getStringID() {
        return stringID;
    }

    public void setStringID(int stringID) {
        this.stringID = stringID;
    }

    public RecommendLesson(int image_URL, String lesson_Name, String maker, long watched_Number, String video_URL) {
        this.image_URL = image_URL;
        this.lesson_Name = lesson_Name;
        this.maker = maker;
        this.watched_Number = watched_Number;
        this.video_URL = video_URL;
    }

    public int getImage_URL() {
        return image_URL;
    }

    public void setImage_URL(int image_URL) {
        this.image_URL = image_URL;
    }

    public String getLesson_Name() {
        return lesson_Name;
    }

    public void setLesson_Name(String lesson_Name) {
        this.lesson_Name = lesson_Name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public long getWatched_Number() {
        return watched_Number;
    }

    public void setWatched_Number(long watched_Number) {
        this.watched_Number = watched_Number;
    }

    public String getVideo_URL() {
        return video_URL;
    }

    public void setVideo_URL(String video_URL) {
        this.video_URL = video_URL;
    }


}
