package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClientDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String street;

    @NotBlank
    private String postcode;

    @NotBlank
    private String city;

    @NotBlank
    private String nip;
    private String phone;
    private String email;
    private String website;
    private String comment;

    @NotBlank
    private UserSimpleDTO user;


}
