package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleDTO {

    private Long id;

    @NotBlank
    private String name;
}
