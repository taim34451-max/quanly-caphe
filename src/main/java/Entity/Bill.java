package Entity;

import java.sql.Date;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bill")
public class Bill {
	@Id
	@Column(name = "IdBill")
	int IdBill;
	@Column(name = "CreatedDate")
	Date CreatedDate;
	@Column(name = "Total")
	float Total;
	@Column(name = "Status")
	String Status;
	@ManyToOne@JoinColumn(name = "IDUser")
	Users users;
	@OneToMany(mappedBy = "Bills")
	List<BillDetail> BillDetail;
	/**
	 * @return the idBill
	 */
	public int getIdBill() {
		return IdBill;
	}
	/**
	 * @param idBill the idBill to set
	 */
	public void setIdBill(int idBill) {
		IdBill = idBill;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return CreatedDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
	/**
	 * @return the total
	 */
	public float getTotal() {
		return Total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(float total) {
		Total = total;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return Status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		Status = status;
	}
	/**
	 * @return the users
	 */
	public Users getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(Users users) {
		this.users = users;
	}
	/**
	 * @return the cards
	 */

	/**
	 * @return the billDetail
	 */
	public List<BillDetail> getBillDetail() {
		return BillDetail;
	}
	/**
	 * @param billDetail the billDetail to set
	 */
	public void setBillDetail(List<BillDetail> billDetail) {
		BillDetail = billDetail;
	}
	
}
