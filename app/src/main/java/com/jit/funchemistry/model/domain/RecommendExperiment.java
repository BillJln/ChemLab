package com.jit.funchemistry.model.domain;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public class RecommendExperiment {
    private int image_URL;
    private String lesson_Name;
    private String maker;
    private long watched_Number;

    public RecommendExperiment(int image_URL, String lesson_Name, String maker, long watched_Number) {
        this.image_URL = image_URL;
        this.lesson_Name = lesson_Name;
        this.maker = maker;
        this.watched_Number = watched_Number;
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
}
