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
@Table(name = "drink")
public class Drink {
	@Id
	@Column(name = "IDDrink")
	String IDDrink;
	@Column(name = "DrinkName")
	String DrinkName;
	@Column(name = "DrinkPrice")
	int DrinkPrice;
	@Column(name = "DrinkIMG")
	String DrinkIMG;
	@Column(name = "DrinkActive")
	boolean DrinkActive;
	@Column(name = "DrinkDescription")
	String DrinkDescription;
	@ManyToOne@JoinColumn(name = "IDCate")
	Category cate;
	@OneToMany(mappedBy = "Drinks")
	List<BillDetail> BillDetails;
	/**
	 * @return the iDDrink
	 */
	public String getIDDrink() {
		return IDDrink;
	}
	/**
	 * @param iDDrink the iDDrink to set
	 */
	public void setIDDrink(String iDDrink) {
		IDDrink = iDDrink;
	}
	/**
	 * @return the drinkName
	 */
	public String getDrinkName() {
		return DrinkName;
	}
	/**
	 * @param drinkName the drinkName to set
	 */
	public void setDrinkName(String drinkName) {
		DrinkName = drinkName;
	}
	/**
	 * @return the drinkPrice
	 */
	public int getDrinkPrice() {
		return DrinkPrice;
	}
	/**
	 * @param drinkPrice the drinkPrice to set
	 */
	public void setDrinkPrice(int drinkPrice) {
		DrinkPrice = drinkPrice;
	}
	/**
	 * @return the drinkIMG
	 */
	public String getDrinkIMG() {
		return DrinkIMG;
	}
	/**
	 * @param drinkIMG the drinkIMG to set
	 */
	public void setDrinkIMG(String drinkIMG) {
		DrinkIMG = drinkIMG;
	}
	/**
	 * @return the drinkActive
	 */
	public boolean isDrinkActive() {
		return DrinkActive;
	}
	/**
	 * @param drinkActive the drinkActive to set
	 */
	public void setDrinkActive(boolean drinkActive) {
		DrinkActive = drinkActive;
	}
	/**
	 * @return the drinkDescription
	 */
	public String getDrinkDescription() {
		return DrinkDescription;
	}
	/**
	 * @param drinkDescription the drinkDescription to set
	 */
	public void setDrinkDescription(String drinkDescription) {
		DrinkDescription = drinkDescription;
	}
	/**
	 * @return the cate
	 */
	public Category getCate() {
		return cate;
	}
	/**
	 * @param cate the cate to set
	 */
	public void setCate(Category cate) {
		this.cate = cate;
	}
	/**
	 * @return the billDetail
	 */
	public List<BillDetail> getBillDetail() {
		return BillDetails;
	}
	/**
	 * @param billDetail the billDetail to set
	 */
	public void setBillDetail(List<BillDetail> billDetail) {
		BillDetails = billDetail;
	}

	public Drink(String iDDrink, String drinkName, int drinkPrice, String drinkIMG, boolean drinkActive,
			String drinkDescription, Category cate, List<BillDetail> billDetails) {
		super();
		IDDrink = iDDrink;
		DrinkName = drinkName;
		DrinkPrice = drinkPrice;
		DrinkIMG = drinkIMG;
		DrinkActive = drinkActive;
		DrinkDescription = drinkDescription;
		this.cate = cate;
		BillDetails = billDetails;
	}
	public Drink() {
		super();
	}
	
	
}
