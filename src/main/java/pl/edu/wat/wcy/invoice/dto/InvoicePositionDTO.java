package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InvoicePositionDTO {

    private Long id;

    @NotBlank
    private String name;
    private String unit;

    @NotBlank
    private Double quantity;

    @NotBlank
    private Double netPrice;

    @NotBlank
    private Double netValue;

    @NotBlank
    private Double grossValue;

    @NotBlank
    private VatTypeDTO vatType;

    @NotBlank
    private Double vatValue;
}
