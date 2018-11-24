package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;

    private String phone;
    private String street;
    private String postcode;
    private String city;
    private String nip;
    private String regon;
    private Boolean active;

}
