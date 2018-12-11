package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

@Data
public class UserSimpleDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String street;
    private String postcode;
    private String city;
    private String nip;
    private String regon;
    private Boolean active;

}
