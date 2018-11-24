package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String street;
    private String postcode;
    private String city;
    private String nip;
    private String phone;
    private String email;
    private String website;
    private String comment;
    private UserSimpleDTO user;


}
