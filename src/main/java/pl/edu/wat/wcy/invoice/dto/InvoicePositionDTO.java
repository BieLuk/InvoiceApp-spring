package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

@Data
public class InvoicePositionDTO {

    private Long id;
    private InvoiceDTO invoice;
    private String name;
    private String unit;
    private Double quantity;
    private Double netPrice;
    private Double netValue;
    private Double grossValue;
    private VatTypeDTO vatType;
    private Double vatValue;
}
