package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class user {
    @Id
    private String id;
    private String password;
    private String fullname;
    private String email;
    private Boolean admin = false;

    // Trỏ chính xác vào tên biến "user" nằm trong class favorite
    @OneToMany(mappedBy = "user")
    private List<favorite> favorites;

    public user() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getAdmin() { return admin; }
    public void setAdmin(Boolean admin) { this.admin = admin; }
    public List<favorite> getFavorites() { return favorites; }
    public void setFavorites(List<favorite> favorites) { this.favorites = favorites; }
}