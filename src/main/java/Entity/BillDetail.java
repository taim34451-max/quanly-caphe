	package Entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BillDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DetailId")
    private Integer detailId;

    @ManyToOne
    @JoinColumn(name = "BillId")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Product product;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Price", precision = 12,scale = 2)
    private BigDecimal price;

    @Column(name = "Note")
    private String note;
}