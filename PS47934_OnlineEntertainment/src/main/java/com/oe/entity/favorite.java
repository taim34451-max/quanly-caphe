package com.oe.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
// Khai báo UniqueConstraint để ngăn chặn 1 user bấm Like 1 video nhiều lần sinh ra dữ liệu rác
@Table(name = "Favorite", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"UserId", "VideoId"})
})
public class favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Thiết lập quan hệ N-1 với bảng User
    @ManyToOne
    @JoinColumn(name = "UserId")
    private user user;

    // Thiết lập quan hệ N-1 với bảng Video
    @ManyToOne
    @JoinColumn(name = "VideoId")
    private video video;

    @Temporal(TemporalType.DATE)
    @Column(name = "LikeDate")
    private Date likeDate = new Date(); // Mặc định lấy ngày giờ hiện tại khi khởi tạo

    // Constructor không tham số (Bắt buộc phải có đối với Hibernate)
    public favorite() {
    }

    // Constructor có tham số để tiện khởi tạo nhanh
    public favorite(user user, video video, Date likeDate) {
        this.user = user;
        this.video = video;
        this.likeDate = likeDate;
    }

    // ================= GETTER & SETTER =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public video getVideo() {
        return video;
    }

    public void setVideo(video video) {
        this.video = video;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }
}