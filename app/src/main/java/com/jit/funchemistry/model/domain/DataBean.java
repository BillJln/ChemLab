package com.jit.funchemistry.model.domain;

import com.jit.funchemistry.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaNingIf
 * @String 2021/3/31
 */
public class DataBean {
    private int image_URL;
    private String lesson_Name;
    private String maker;
    private long watched_Number;
    private String video_URL;
    private int stringID;

    public DataBean(int image_URL, String lesson_Name, String maker, String today,String video_URL, int stringID) {
        this.image_URL = image_URL;
        this.lesson_Name = lesson_Name;
        this.maker = maker;
        this.today = today;
        this.stringID = stringID;
        this.video_URL = video_URL;
    }

    private String today;

    public String getToday() {
        return "2021-3-31";
    }

    public void setToday(String today) {
        this.today = today;
    }

    public DataBean(int image_URL, String lesson_Name, String maker, long watched_Number, String video_URL, int stringID) {
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

    public DataBean(int image_URL, String lesson_Name, String maker, long watched_Number, String video_URL) {
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

    public DataBean(int image_URL, String lesson_Name, String maker, long watched_Number) {
        this.image_URL = image_URL;
        this.lesson_Name = lesson_Name;
        this.maker = maker;
        this.watched_Number = watched_Number;
    }

    public static List<DataBean> getTestData1() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.mipmap.chemical_1, "过氧化氢分解制取氧气", "人教教材", 5654));
        list.add(new DataBean(R.mipmap.chemical_2, "铁丝在氧气中燃烧", "人教教材", 7615));
        list.add(new DataBean(R.mipmap.chemical_3, "配置氯化钠溶液", "人教教材", 7321));
        list.add(new DataBean(R.mipmap.chemical_4, "浓硫酸的稀释", "人教教材", 7832));
        list.add(new DataBean(R.mipmap.chemical_5, "氢氧化钠，氯化钡与硫酸铜反应", "人教教材", 5214));
//        list.add(new DataBean(R.mipmap.experiment, "一氧化碳还原四氧化三铁", "人教教材", 3533));
//        list.add(new DataBean(R.mipmap.experiment, "苛性钠暴露在空气中变质", "人教教材", 10472));
//        list.add(new DataBean(R.mipmap.experiment, "碳酸钠与稀盐酸反应", "人教教材", 5738));
//        list.add(new DataBean(R.mipmap.experiment, "硫酸钠和氯化钡", "人教教材", 2432));
//        list.add(new DataBean(R.mipmap.experiment, "水在直流电的作用下分解", "人教教材", 5732));
//        list.add(new DataBean(R.mipmap.experiment, "一氧化碳在氧气中燃烧", "人教教材", 5734));
//        list.add(new DataBean(R.mipmap.experiment, "锌和硫酸铜溶液反应", "人教教材", 8615));
//        list.add(new DataBean(R.mipmap.experiment, "酒精在空气中燃烧", "人教教材", 7324));
        return list;
    }

    public static List<DataBean> getTestData2(){
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.mipmap.experiment_1, "过氧化氢分解制取氧气", "人教教材", 5654,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4",R.string.H2O2_To_O2));
        list.add(new DataBean(R.mipmap.experiment_2, "铁丝在氧气中燃烧", "人教教材", 7615,"http://2cwstudio.com/wp-content/uploads/2020/Video/Video02.mp4",R.string.Fe_O2));
        list.add(new DataBean(R.mipmap.experiment_3, "配置氯化钠溶液", "人教教材", 7321,"http://2cwstudio.com/wp-content/uploads/2020/Video/Video03.mp4",R.string.NaCl));
        list.add(new DataBean(R.mipmap.experiment_4, "浓硫酸的稀释", "人教教材", 7832,"http://2cwstudio.com/wp-content/uploads/2020/Video/Video04.mp4",R.string.H2SO4));
        list.add(new DataBean(R.mipmap.experiment_5, "氢氧化钠，氯化钡与硫酸铜反应", "人教教材", 5214,"http://2cwstudio.com/wp-content/uploads/2020/Video/Video05.mp4",R.string.NaOH_BaCl));
//        list.add(new DataBean(R.mipmap.icon, "一氧化碳还原四氧化三铁", "人教教材", 3533,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
//        list.add(new DataBean(R.mipmap.icon, "苛性钠暴露在空气中变质", "人教教材", 10472,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
//        list.add(new DataBean(R.mipmap.icon, "碳酸钠与稀盐酸反应", "人教教材", 5738,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
//        list.add(new DataBean(R.mipmap.icon, "硫酸钠和氯化钡", "人教教材", 2432,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
//        list.add(new DataBean(R.mipmap.icon, "水在直流电的作用下分解", "人教教材", 5732,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
//        list.add(new DataBean(R.mipmap.icon, "一氧化碳在氧气中燃烧", "人教教材", 5734,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
//        list.add(new DataBean(R.mipmap.icon, "锌和硫酸铜溶液反应", "人教教材", 8615,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
//        list.add(new DataBean(R.mipmap.icon, "酒精在空气中燃烧", "人教教材", 7324,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
        return list;
    }

    public static ArrayList<DataBean> getTestData3(){
        ArrayList<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.mipmap.experiment_1, "过氧化氢分解制取氧气", "人教教材", "2021-03-30","http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4",R.string.H2O2_To_O2));
        list.add(new DataBean(R.mipmap.experiment_2, "铁丝在氧气中燃烧", "人教教材", "2021-03-30","http://2cwstudio.com/wp-content/uploads/2020/Video/Video02.mp4",R.string.Fe_O2));
        list.add(new DataBean(R.mipmap.experiment_3, "配置氯化钠溶液", "人教教材", "2021-03-30","http://2cwstudio.com/wp-content/uploads/2020/Video/Video03.mp4",R.string.NaCl));
        list.add(new DataBean(R.mipmap.experiment_4, "浓硫酸的稀释", "人教教材", "2021-03-30","http://2cwstudio.com/wp-content/uploads/2020/Video/Video04.mp4",R.string.H2SO4));
        list.add(new DataBean(R.mipmap.experiment_5, "氢氧化钠，氯化钡与硫酸铜反应", "人教教材", "2021-03-30","http://2cwstudio.com/wp-content/uploads/2020/Video/Video05.mp4",R.string.NaOH_BaCl));
//        list.add(new DataBean(R.mipmap.experiment, "一氧化碳还原四氧化三铁", "人教教材", 3533));
//        list.add(new DataBean(R.mipmap.experiment, "苛性钠暴露在空气中变质", "人教教材", 10472));
//        list.add(new DataBean(R.mipmap.experiment, "碳酸钠与稀盐酸反应", "人教教材", 5738));
//        list.add(new DataBean(R.mipmap.experiment, "硫酸钠和氯化钡", "人教教材", 2432));
//        list.add(new DataBean(R.mipmap.experiment, "水在直流电的作用下分解", "人教教材", 5732));
//        list.add(new DataBean(R.mipmap.experiment, "一氧化碳在氧气中燃烧", "人教教材", 5734));
//        list.add(new DataBean(R.mipmap.experiment, "锌和硫酸铜溶液反应", "人教教材", 8615));
//        list.add(new DataBean(R.mipmap.experiment, "酒精在空气中燃烧", "人教教材", 7324));
        return list;
    }
}
