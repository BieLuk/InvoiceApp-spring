package pl.edu.wat.wcy.invoice.model;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "users")
public class User  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

//    private String street;
//
//    private String postcode;
//
//    private String city;
//
//    private String nip;
//
//    private String regon;
//
//
//
//    @Email
//
//
//    @JsonIgnore
//    private String password;
//
//    @Builder.Default()
//    private boolean active = true;


}
