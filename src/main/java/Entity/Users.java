package Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name ="users")
public class Users {
	@Id
	@Column(name = "IDUser")
	String IDUser;
	@Column(name = "UserName")
	String UserName;
	@Column(name = "UserPass")
	String UserPass;
	@Column(name = "UserPhone")
	String UserPhone;
	@Column(name = "UserEmail")
	String UserEmail;
	@Column(name = "Role")
	int Role;
	@OneToMany(mappedBy = "users")
	List<Bill> bills;
	/**
	 * @return the iDUser
	 */
	public String getIDUser() {
		return IDUser;
	}
	/**
	 * @param iDUser the iDUser to set
	 */
	public void setIDUser(String iDUser) {
		IDUser = iDUser;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}
	/**
	 * @return the userPass
	 */
	public String getUserPass() {
		return UserPass;
	}
	/**
	 * @param userPass the userPass to set
	 */
	public void setUserPass(String userPass) {
		UserPass = userPass;
	}
	/**
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return UserPhone;
	}
	/**
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return UserEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	/**
	 * @return the role
	 */
	public int isRole() {
		return Role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(int role) {
		Role = role;
	}
	/**
	 * @return the bills
	 */
	public List<Bill> getBills() {
		return bills;
	}
	/**
	 * @param bills the bills to set
	 */
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	public Users(String iDUser, String userName, String userPass, String userPhone, String userEmail, int role,
			List<Bill> bills) {
		super();
		IDUser = iDUser;
		UserName = userName;
		UserPass = userPass;
		UserPhone = userPhone;
		UserEmail = userEmail;
		Role = role;
		this.bills = bills;
	}
	public Users() {
		super();
	}
	
}
