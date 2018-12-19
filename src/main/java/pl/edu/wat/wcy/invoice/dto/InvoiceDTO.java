package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private UserSimpleDTO user;
    private LocalDate createDate;
    private LocalDate saleDate;
    private LocalDate paymentDate;
    private Double netValue;
    private Double grossValue;
    private PaymentTypeDTO paymentType;
    private InvoiceTypeDTO invoiceType;
    private ClientDTO client;
}
