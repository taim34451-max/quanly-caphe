package entity;



import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "IDUser", length = 10)
    private String idUser;

    @Column(name = "UserName", length = 50)
    private String userName;

    @Column(name = "UserPass", length = 50)
    private String userPass;

    @Column(name = "UserPhone", length = 10)
    private String userPhone;

    @Column(name = "UserEmail", length = 50)
    private String userEmail;

    @Column(name = "Role")
    private Integer role;

    @Column(name = "UserImg", length = 255)
    private String userImg;

    @Column(name = "UserActive")
    private Boolean userActive;

    @OneToMany(mappedBy = "user")
    private List<Bill> bills;

    public User() {}

    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserPass() { return userPass; }
    public void setUserPass(String userPass) { this.userPass = userPass; }

    public String getUserPhone() { return userPhone; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }

    public String getUserImg() { return userImg; }
    public void setUserImg(String userImg) { this.userImg = userImg; }

    public Boolean getUserActive() { return userActive; }
    public void setUserActive(Boolean userActive) { this.userActive = userActive; }

    public List<Bill> getBills() { return bills; }
    public void setBills(List<Bill> bills) { this.bills = bills; }
}
