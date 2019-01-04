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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_id", referencedColumnName = "id")
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
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
    @JoinColumn(name = "vat_type", referencedColumnName = "id")
    public VatType getVatType() {
        return vatType;
    }

    public void setVatType(VatType vatType) {
        this.vatType = vatType;
    }

    public Double getVatValue() {
        return vatValue;
    }

    public void setVatValue(Double vatValue) {
        this.vatValue = vatValue;
    }
}
