package pl.edu.wat.wcy.invoice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Data
public class InvoiceDTO {

    private Long id;

    @NotBlank
    private String invoiceNumber;

    @NotBlank
    private UserSimpleDTO user;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Warsaw")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createDate;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Warsaw")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate saleDate;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Warsaw")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate paymentDate;

    @NotBlank
    private Double netAmount;

    @NotBlank
    private Double grossAmount;

    @NotBlank
    private Double vatAmount;

    @NotBlank
    private PaymentTypeDTO paymentType;

    @NotBlank
    private InvoiceTypeDTO invoiceVersion;

    @NotBlank
    private ClientDTO client;

    @NotBlank
    private Set<InvoicePositionDTO> invoicePositions;

    @NotBlank
    private Set<InvoiceVatDTO> invoiceVats;


}
