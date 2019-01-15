package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class UserSimpleDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    private String phone;

    @NotBlank
    private String street;

    @NotBlank
    private String postcode;

    @NotBlank
    private String city;

    @NotBlank
    private String nip;

    @NotBlank
    private String regon;
    private Boolean active;
    private Set<RoleDTO> roles;
}
