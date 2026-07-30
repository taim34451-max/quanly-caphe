package Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name ="category")
public class Category{
	@Id
	@Column(name = "IDCate")
	int IDCate;
	@Column(name = "Catename")
	String Catename;
	@Column(name = "Catetype")
	String Catetype;
	@OneToMany(mappedBy = "cate")
	List<Drink> drinks;
	/**
	 * @return the iDCate
	 */
	public int getIDCate() {
		return IDCate;
	}
	/**
	 * @param iDCate the iDCate to set
	 */
	public void setIDCate(int iDCate) {
		IDCate = iDCate;
	}
	/**
	 * @return the catename
	 */
	public String getCatename() {
		return Catename;
	}
	/**
	 * @param catename the catename to set
	 */
	public void setCatename(String catename) {
		Catename = catename;
	}
	/**
	 * @return the catetype
	 */
	public String getCatetype() {
		return Catetype;
	}
	/**
	 * @param catetype the catetype to set
	 */
	public void setCatetype(String catetype) {
		Catetype = catetype;
	}
	/**
	 * @return the drinks
	 */
	public List<Drink> getDrinks() {
		return drinks;
	}
	/**
	 * @param drinks the drinks to set
	 */
	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}
	public Category(int iDCate, String catename, String catetype, List<Drink> drinks) {
		super();
		IDCate = iDCate;
		Catename = catename;
		Catetype = catetype;
		this.drinks = drinks;
	}
	public Category() {
		super();
	}
	
}
