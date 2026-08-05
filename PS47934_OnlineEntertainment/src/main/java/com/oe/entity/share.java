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

@Entity
@Table(name = "Share")
public class share {

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

    @Column(name = "Emails")
    private String emails;

    @Temporal(TemporalType.DATE)
    @Column(name = "ShareDate")
    private Date shareDate = new Date(); // Tự động chốt ngày hiện tại khi có người share

    // Constructor mặc định bắt buộc của Hibernate
    public share() {
    }

    // Constructor có tham số hỗ trợ khởi tạo nhanh
    public share(user user, video video, String emails, Date shareDate) {
        this.user = user;
        this.video = video;
        this.emails = emails;
        this.shareDate = shareDate;
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

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }
}