package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Favorites", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"VideoId", "UserId"})
})
public class favorite {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tên biến là "user" (viết thường hoàn toàn)
    @ManyToOne 
    @JoinColumn(name = "UserId")
    private user user;

    // Tên biến là "video" (viết thường hoàn toàn)
    @ManyToOne 
    @JoinColumn(name = "VideoId")
    private video video;

    @Temporal(TemporalType.DATE)
    private Date likeDate = new Date();

    public favorite() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public user getUser() { return user; }
    public void setUser(user user) { this.user = user; }
    public video getVideo() { return video; }
    public void setVideo(video video) { this.video = video; }
    public Date getLikeDate() { return likeDate; }
    public void setLikeDate(Date likeDate) { this.likeDate = likeDate; }
}