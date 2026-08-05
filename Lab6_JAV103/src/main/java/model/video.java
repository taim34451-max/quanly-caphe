package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Videos")
public class video {
    @Id
    private String id;
    private String title;
    private String poster;
    private String description;
    private Integer views = 0;
    private Boolean active = true;

    // Trỏ chính xác vào tên biến "video" nằm trong class favorite
    @OneToMany(mappedBy = "video")
    private List<favorite> favorites;

    public video() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getViews() { return views; }
    public void setViews(Integer views) { this.views = views; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public List<favorite> getFavorites() { return favorites; }
    public void setFavorites(List<favorite> favorites) { this.favorites = favorites; }
}