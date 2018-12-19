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

    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", updatable = false)
    private User user;
    private LocalDate createDate;
    private LocalDate saleDate;
    private LocalDate paymentDate;
    private Double netValue;
    private Double grossValue;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    private PaymentType paymentType;


    @ManyToOne
    @JoinColumn(name = "invoice_type_id", referencedColumnName = "id")
    private InvoiceType invoiceType;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;


}
