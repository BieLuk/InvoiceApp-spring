package pl.edu.wat.wcy.invoice.model;

import javax.persistence.*;

@Entity
@Table(name = "invoice_vat")
public class InvoiceVat {

    private Long id;
    private VatType vatType;
    private Double netValue;
    private Double vatValue;
    private Double grossValue;
    private Invoice invoice;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "vat_type", referencedColumnName = "id")
    public VatType getVatType() {
        return vatType;
    }

    public void setVatType(VatType vatType) {
        this.vatType = vatType;
    }

    public Double getNetValue() {
        return netValue;
    }

    public void setNetValue(Double netValue) {
        this.netValue = netValue;
    }

    public Double getVatValue() {
        return vatValue;
    }

    public void setVatValue(Double vatValue) {
        this.vatValue = vatValue;
    }

    public Double getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(Double grossValue) {
        this.grossValue = grossValue;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_id", referencedColumnName = "id")
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
