package Entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Integer productId;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "Category")
    private String category;

    @Column(name = "IsAvailable")
    private Boolean isAvailable;

    @Column(name = "ProductIMG")
    private String productIMG;
    
    @Column(name = "Price")
    private BigDecimal price;
    
    @Column(name = "DrinkDescription")
    private String drinkDescription;	

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<BillDetail> billDetails;
    
    @OneToOne(mappedBy = "product",
    	    cascade = CascadeType.ALL,
    	    orphanRemoval = true)
    private SizePrice sizePrices;
}