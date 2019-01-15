package pl.edu.wat.wcy.invoice.model;

import javax.persistence.*;

@Entity
@Table(name = "vat_type")
public class VatType {

    private Long id;
    private String name;
    private Double value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "value", nullable = false)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
