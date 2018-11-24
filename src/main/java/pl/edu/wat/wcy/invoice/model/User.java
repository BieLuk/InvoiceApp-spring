package pl.edu.wat.wcy.invoice.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;


@Data
@Entity
@Table(name = "users")
public class User  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    // TODO nullable false
    private String password;

    private String phone;
    private String street;
    private String postcode;
    private String city;

    @Column(unique = true)
    private String nip;
    private String regon;
    private Boolean active;

}
