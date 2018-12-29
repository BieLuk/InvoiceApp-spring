package pl.edu.wat.wcy.invoice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private UserSimpleDTO user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Warsaw")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDate createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Warsaw")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDate saleDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Warsaw")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDate paymentDate;
    private Double netValue;
    private Double grossValue;
    private PaymentTypeDTO paymentType;
    private InvoiceTypeDTO invoiceType;
    private ClientDTO client;
}
