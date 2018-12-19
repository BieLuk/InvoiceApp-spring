package pl.edu.wat.wcy.invoice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment_type")
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
