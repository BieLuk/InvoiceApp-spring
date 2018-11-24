package pl.edu.wat.wcy.invoice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String street;
    private String postcode;
    private String city;
    @Column(unique = true, nullable = false)
    private String nip;

    private String phone;
    @Email
    private String email;
    private String website;
    private String comment;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

}
