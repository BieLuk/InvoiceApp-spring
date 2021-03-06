package pl.edu.wat.wcy.invoice.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "invoice", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "invoice_number", "user_id"
        })
})
public class Invoice {

    private Long id;
    private String invoiceNumber;
    private User user;
    private LocalDate createDate;
    private LocalDate saleDate;
    private LocalDate paymentDate;
    private Double netAmount;
    private Double grossAmount;
    private Double vatAmount;
    private PaymentType paymentType;
    private InvoiceType invoiceVersion;
    private Client client;
    private Set<InvoicePosition> invoicePositions;
    private Set<InvoiceVat> invoiceVats;
    private Boolean active;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "invoice_number", nullable = false)
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", updatable = false, nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "create_date", nullable = false)
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Column(name = "sale_date", nullable = false)
    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    @Column(name = "payment_date", nullable = false)
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Column(name = "net_value", nullable = false)
    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    @Column(name = "gross_value", nullable = false)
    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    @Column(name = "vat_value", nullable = false)
    public Double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Double vatAmount) {
        this.vatAmount = vatAmount;
    }

    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id", nullable = false)
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @ManyToOne
    @JoinColumn(name = "invoice_type_id", referencedColumnName = "id", nullable = false)
    public InvoiceType getInvoiceVersion() {
        return invoiceVersion;
    }

    public void setInvoiceVersion(InvoiceType invoiceVersion) {
        this.invoiceVersion = invoiceVersion;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "invoice"
    )
    public Set<InvoicePosition> getInvoicePositions() {
        return invoicePositions;
    }

    public void setInvoicePositions(Set<InvoicePosition> invoicePositions) {
        this.invoicePositions = invoicePositions;
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "invoice"
    )
    public Set<InvoiceVat> getInvoiceVats() {
        return invoiceVats;
    }

    public void setInvoiceVats(Set<InvoiceVat> invoiceVats) {
        this.invoiceVats = invoiceVats;
    }

    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
