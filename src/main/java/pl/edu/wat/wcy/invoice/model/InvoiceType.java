package pl.edu.wat.wcy.invoice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "invoice_type")
public class InvoiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
