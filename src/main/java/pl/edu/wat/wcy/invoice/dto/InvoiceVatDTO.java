package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;
import pl.edu.wat.wcy.invoice.model.VatType;

import javax.validation.constraints.NotBlank;

@Data
public class InvoiceVatDTO {

    private Long id;

    @NotBlank
    private VatType vatType;

    @NotBlank
    private Double netValue;

    @NotBlank
    private Double vatValue;

    @NotBlank
    private Double grossValue;
}
