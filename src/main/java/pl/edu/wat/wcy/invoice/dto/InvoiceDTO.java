package pl.edu.wat.wcy.invoice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class InvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private UserSimpleDTO user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Warsaw")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Warsaw")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate saleDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Warsaw")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate paymentDate;
    private Double netAmount;
    private Double grossAmount;
    private PaymentTypeDTO paymentType;
    private InvoiceTypeDTO invoiceVersion;
    private ClientDTO client;
    private Set<InvoicePositionDTO> invoicePositions;

}
