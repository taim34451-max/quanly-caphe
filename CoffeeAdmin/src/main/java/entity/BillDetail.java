package entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "billdetail")
public class BillDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBillDetail")
    private Integer idBillDetail;

    @ManyToOne
    @JoinColumn(name = "IDBill")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "IDDrink")
    private Drink drink;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Price", precision = 10, scale = 2)
    private BigDecimal price;
}