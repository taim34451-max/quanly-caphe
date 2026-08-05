package com.oe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Video")
public class video {

    @Id
    @Column(name = "Id")
    private String id; // Lưu trực tiếp Youtube ID (VD: Ytet_bPiRCU)

    @Column(name = "Title")
    private String title;

    @Column(name = "Poster")
    private String poster;

    @Column(name = "Views")
    private Integer views = 0; // Mặc định khi mới thêm video thì lượt xem = 0

    @Column(name = "Description")
    private String description;

    @Column(name = "Active")
    private Boolean active = true; // Mặc định video được kích hoạt

    // Constructor mặc định bắt buộc của Hibernate
    public video() {
    }

    // Constructor có tham số hỗ trợ khởi tạo nhanh
    public video(String id, String title, String poster, Integer views, String description, Boolean active) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.views = views;
        this.description = description;
        this.active = active;
    }

    // ================= GETTER & SETTER =================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}