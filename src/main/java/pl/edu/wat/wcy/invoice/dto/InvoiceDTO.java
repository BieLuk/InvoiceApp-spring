package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDTO {

    private Long userId;
    private LocalDate date = LocalDate.now();
    private Double netValue;
    private Double grossValue;
    private Long typeId;

}
