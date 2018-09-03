package com.auto.mobile.moudle.model;

/**
 * 汽修厂实体类
 */
public class Factory {

    //头像
    private String headImg;
    //昵称
    private String nickName;
    //定位
    private String location;
    //时间
    private String dataTime;
    //距离
    private String distance;
    //描述
    private String desc;
    //图片
    private String picture;
    //录音的url
    private String recordUrl;
    //电话
    private String telPhone;
    //录音时长
    private String recordLengthTime;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRecordUrl() {
        return recordUrl;
    }

    public void setRecordUrl(String recordUrl) {
        this.recordUrl = recordUrl;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getRecordLengthTime() {
        return recordLengthTime;
    }

    public void setRecordLengthTime(String recordLengthTime) {
        this.recordLengthTime = recordLengthTime;
    }

    @Override
    public String toString() {
        return "Factory{" +
                "headImg='" + headImg + '\'' +
                ", nickName='" + nickName + '\'' +
                ", location='" + location + '\'' +
                ", dataTime='" + dataTime + '\'' +
                ", distance='" + distance + '\'' +
                ", desc='" + desc + '\'' +
                ", picture='" + picture + '\'' +
                ", recordUrl='" + recordUrl + '\'' +
                ", telPhone='" + telPhone + '\'' +
                ", recordLengthTime='" + recordLengthTime + '\'' +
                '}';
    }
}
