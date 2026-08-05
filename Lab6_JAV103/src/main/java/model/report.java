package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class report {
    @Id
    private Serializable group;
    private Long likes;
    private Date newest;
    private Date oldest;

    // Constructor mặc định (bắt buộc đối với JPA)
    public report() {}

    // Constructor dùng để hứng dữ liệu từ JPQL
    public report(Serializable group, Long likes, Date newest, Date oldest) {
        this.group = group;
        this.likes = likes;
        this.newest = newest;
        this.oldest = oldest;
    }

    // Getters và Setters
    public Serializable getGroup() { return group; }
    public void setGroup(Serializable group) { this.group = group; }
    public Long getLikes() { return likes; }
    public void setLikes(Long likes) { this.likes = likes; }
    public Date getNewest() { return newest; }
    public void setNewest(Date newest) { this.newest = newest; }
    public Date getOldest() { return oldest; }
    public void setOldest(Date oldest) { this.oldest = oldest; }
}