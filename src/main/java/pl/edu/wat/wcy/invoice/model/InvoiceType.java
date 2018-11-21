package pl.edu.wat.wcy.invoice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "invoice_type")
public class InvoiceType {

    @Id
    private Long id;

    private String name;
}
