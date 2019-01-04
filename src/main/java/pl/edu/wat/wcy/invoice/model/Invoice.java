package pl.edu.wat.wcy.invoice.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class Invoice {

    private Long id;
    private String invoiceNumber;
    private User user;
    private LocalDate createDate;
    private LocalDate saleDate;
    private LocalDate paymentDate;
    private Double netValue;
    private Double grossValue;
    private PaymentType paymentType;
    private InvoiceType invoiceType;
    private Client client;
    private Set<InvoicePosition> invoicePositions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }


    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getNetValue() {
        return netValue;
    }

    public void setNetValue(Double netValue) {
        this.netValue = netValue;
    }

    public Double getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(Double grossValue) {
        this.grossValue = grossValue;
    }

    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @ManyToOne
    @JoinColumn(name = "invoice_type_id", referencedColumnName = "id")
    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(mappedBy = "invoice")
    public Set<InvoicePosition> getInvoicePositions() {
        return invoicePositions;
    }

    public void setInvoicePositions(Set<InvoicePosition> invoicePositions) {
        this.invoicePositions = invoicePositions;
    }
}
