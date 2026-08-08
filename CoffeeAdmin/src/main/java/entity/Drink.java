package entity;


import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "drink")
public class Drink implements Serializable {
    @Id
    @Column(name = "IDDrink", length = 10)
    private String idDrink;

    @Column(name = "DrinkName", length = 50)
    private String drinkName;

    @Column(name = "DrinkPrice")
    private BigDecimal drinkPrice;

    @Column(name = "DrinkIMG", length = 255)
    private String drinkIMG;

    @Column(name = "DrinkDescription", length = 255)
    private String drinkDescription;

    @Column(name = "DrinkActive")
    private Boolean drinkActive;

    @ManyToOne
    @JoinColumn(name = "IDCate")
    private Category category;

    public Drink() {}

    public String getIdDrink() { return idDrink; }
    public void setIdDrink(String idDrink) { this.idDrink = idDrink; }

    public String getDrinkName() { return drinkName; }
    public void setDrinkName(String drinkName) { this.drinkName = drinkName; }

    public BigDecimal getDrinkPrice() { return drinkPrice; }
    public void setDrinkPrice(BigDecimal drinkPrice) { this.drinkPrice = drinkPrice; }

    public String getDrinkIMG() { return drinkIMG; }
    public void setDrinkIMG(String drinkIMG) { this.drinkIMG = drinkIMG; }

    public String getDrinkDescription() { return drinkDescription; }
    public void setDrinkDescription(String drinkDescription) { this.drinkDescription = drinkDescription; }

    public Boolean getDrinkActive() { return drinkActive; }
    public void setDrinkActive(Boolean drinkActive) { this.drinkActive = drinkActive; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
