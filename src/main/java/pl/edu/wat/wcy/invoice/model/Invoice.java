package pl.edu.wat.wcy.invoice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    private LocalDate createDate;

    private Double netValue;

    private Double grossValue;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private InvoiceType type;
}
