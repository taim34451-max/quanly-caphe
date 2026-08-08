package Entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "SizePrice")
public class SizePrice {
@Column(name = "SizeS")
private BigDecimal sizeS;

@Column(name = "SizeM")
private BigDecimal sizeM;

@Column(name = "SizeL")
private BigDecimal sizeL;

@ManyToOne
@JoinColumn(name = "ProductId")
private Product product;
}
