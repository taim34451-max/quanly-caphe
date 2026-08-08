package entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @Column(name = "IDCate", length = 10)
    private String idCate;

    @Column(name = "Catename", length = 50)
    private String catename;

    @Column(name = "Catetype", length = 50)
    private String catetype;

    @OneToMany(mappedBy = "category")
    private List<Drink> drinks;

    public Category() {}

    public String getIdCate() { return idCate; }
    public void setIdCate(String idCate) { this.idCate = idCate; }

    public String getCatename() { return catename; }
    public void setCatename(String catename) { this.catename = catename; }

    public String getCatetype() { return catetype; }
    public void setCatetype(String catetype) { this.catetype = catetype; }

    public List<Drink> getDrinks() { return drinks; }
    public void setDrinks(List<Drink> drinks) { this.drinks = drinks; }
}