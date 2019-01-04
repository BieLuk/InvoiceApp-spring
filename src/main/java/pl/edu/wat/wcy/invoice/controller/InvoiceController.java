package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.invoice.dto.InvoiceDTO;
import pl.edu.wat.wcy.invoice.model.Invoice;
import pl.edu.wat.wcy.invoice.response.ListResponse;
import pl.edu.wat.wcy.invoice.response.ObjectReference;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.InvoiceService;
import pl.nip24.client.AllData;
import pl.nip24.client.InvoiceData;
import pl.nip24.client.NIP24Client;

import javax.websocket.server.PathParam;
import java.net.MalformedURLException;

@RestController
@RequestMapping(value = "/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping(value = "/invoice")
    public SingleResponse<InvoiceDTO> getInvoice(@PathParam("invoiceId") Long invoiceId){
        return new SingleResponse<>(invoiceService.getInvoice(invoiceId));
    }

    @PostMapping
    public SingleResponse<ObjectReference> createInvoice(@RequestBody InvoiceDTO invoiceDTO){
        return new SingleResponse<>(invoiceService.createInvoice(invoiceDTO));
    }

    @GetMapping
    public ListResponse<InvoiceDTO> getInvoicesByUserId(@PathParam("userId") Long userId) {
        return new ListResponse<>(invoiceService.getInvoicesByUserId(userId));
    }


}
