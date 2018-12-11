package pl.edu.wat.wcy.invoice.model;

import lombok.Data;

import javax.persistence.*;

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
    private String nip;

    private String phone;
    private String email;
    private String website;
    private String comment;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id") //, insertable = false, updatable = false
    private User user;

}
