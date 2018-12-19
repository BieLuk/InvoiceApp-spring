package pl.edu.wat.wcy.invoice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "invoice_position")
public class InvoicePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="invoice_id", referencedColumnName = "id", updatable = false)
    private Invoice invoice;

    private String name;
    private String unit;
    private Double quantity;
    private Double netPrice;
    private Double netValue;
    private Double grossValue;

    @ManyToOne
    @JoinColumn(name = "vat_type", referencedColumnName = "id")
    private VatType vatType;
    private Double vatValue;


}
