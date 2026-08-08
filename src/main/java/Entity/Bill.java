package Entity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.tomcat.jakartaee.commons.lang3.builder.ToStringExclude;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillId")
    private Integer billId;

    @ManyToOne
    @JoinColumn(name = "UserId")
    @ToString.Exclude
    private Users user;

    @Column(name = "TableNumber", nullable = false)
    private String tableNumber;

    @Column(name = "Total",precision = 12,scale = 2)
    private BigDecimal total;

    @Column(name = "Status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "Note")
    private String note;
}