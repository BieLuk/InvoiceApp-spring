package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VatTypeDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private Double value;
}
