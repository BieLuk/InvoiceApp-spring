package pl.edu.wat.wcy.invoice.model;

import javax.persistence.*;

@Entity
@Table(name = "invoice_position")
public class InvoicePosition {

    private Long id;
    private Invoice invoice;
    private String name;
    private String unit;
    private Double quantity;
    private Double netPrice;
    private Double netValue;
    private Double grossValue;

    private VatType vatType;
    private Double vatValue;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_id", referencedColumnName = "id", nullable = false)
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "quantity", nullable = false)
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Column(name = "net_price", nullable = false)
    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }

    @Column(name = "net_value", nullable = false)
    public Double getNetValue() {
        return netValue;
    }

    public void setNetValue(Double netValue) {
        this.netValue = netValue;
    }

    @Column(name = "gross_value", nullable = false)
    public Double getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(Double grossValue) {
        this.grossValue = grossValue;
    }

    @ManyToOne
    @JoinColumn(name = "vat_type", referencedColumnName = "id", nullable = false)
    public VatType getVatType() {
        return vatType;
    }

    public void setVatType(VatType vatType) {
        this.vatType = vatType;
    }

    @Column(name = "vat_value", nullable = false)
    public Double getVatValue() {
        return vatValue;
    }

    public void setVatValue(Double vatValue) {
        this.vatValue = vatValue;
    }
}
