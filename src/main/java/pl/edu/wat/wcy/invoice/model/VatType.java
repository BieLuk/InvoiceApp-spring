package pl.edu.wat.wcy.invoice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vat_type")
public class VatType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
