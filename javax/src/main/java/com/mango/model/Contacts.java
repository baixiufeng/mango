package com.mango.model;

import java.util.Date;

public class Contacts {
    private String id;

    private Date createTime;

    private String headPortrait;

    private String hate;

    private String likeMovie;

    private String likes;

    private String name;

    private String record;

    private String seat;

    private String uid;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait == null ? null : headPortrait.trim();
    }

    public String getHate() {
        return hate;
    }

    public void setHate(String hate) {
        this.hate = hate == null ? null : hate.trim();
    }

    public String getLikeMovie() {
        return likeMovie;
    }

    public void setLikeMovie(String likeMovie) {
        this.likeMovie = likeMovie == null ? null : likeMovie.trim();
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes == null ? null : likes.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}