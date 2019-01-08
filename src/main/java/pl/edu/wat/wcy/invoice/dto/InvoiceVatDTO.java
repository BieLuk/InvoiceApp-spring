package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;
import pl.edu.wat.wcy.invoice.model.VatType;

@Data
public class InvoiceVatDTO {

    private Long id;
    private VatType vatType;
    private Double netValue;
    private Double vatValue;
    private Double grossValue;
}
