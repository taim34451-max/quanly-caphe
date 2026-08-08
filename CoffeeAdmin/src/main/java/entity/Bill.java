package entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill")
public class Bill implements Serializable {
    @Id
    @Column(name = "IDBill", length = 10)
    private String idBill;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "Status", length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "IDUser")
    private User user;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillDetail> billDetails;
}
