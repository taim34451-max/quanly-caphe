package Entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "BillDetail")
public class BillDetail {
	@Id
	@Column(name = "IDBillDetail")
	String IDBillDetail;
	@Column(name = "Price")
	float Price;
	@Column(name = "Quantity")
	int Quantity;
	@ManyToOne@JoinColumn(name = "IDBill")
	Bill Bills;
	@ManyToOne@JoinColumn(name = "IDDrink")
	Drink Drinks;
	/**
	 * @return the iDBillDetail
	 */
	public String getIDBillDetail() {
		return IDBillDetail;
	}
	/**
	 * @param iDBillDetail the iDBillDetail to set
	 */
	public void setIDBillDetail(String iDBillDetail) {
		IDBillDetail = iDBillDetail;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return Price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		Price = price;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return Quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	/**
	 * @return the bill
	 */
	public Bill getBill() {
		return Bills;
	}
	/**
	 * @param bill the bill to set
	 */
	public void setBill(Bill bill) {
		Bills = bill;
	}
	/**
	 * @return the drink
	 */
	public Drink getDrink() {
		return Drinks;
	}
	/**
	 * @param drink the drink to set
	 */
	public void setDrink(Drink drink) {
		Drinks = drink;
	}
	public BillDetail(String iDBillDetail, float price, int quantity, Bill bills, Drink drinks) {
		super();
		IDBillDetail = iDBillDetail;
		Price = price;
		Quantity = quantity;
		Bills = bills;
		Drinks = drinks;
	}
	public BillDetail() {
		super();
	}
	
	
}
