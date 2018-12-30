package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.invoice.dto.InvoiceTypeDTO;
import pl.edu.wat.wcy.invoice.dto.VatTypeDTO;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.InvoiceTypeService;
import pl.edu.wat.wcy.invoice.service.VatTypeService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/invoice/type")
@RequiredArgsConstructor
public class InvoiceTypeController {
    private final InvoiceTypeService invoiceTypeService;

    @GetMapping
    public SingleResponse<InvoiceTypeDTO> getClient(@PathParam("invoiceTypeId") Long invoiceTypeId) {
        return new SingleResponse<>(invoiceTypeService.getInvoiceType(invoiceTypeId));
    }

}

