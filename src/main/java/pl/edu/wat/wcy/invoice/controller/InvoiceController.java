package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.invoice.dto.InvoiceDTO;
import pl.edu.wat.wcy.invoice.model.Invoice;
import pl.edu.wat.wcy.invoice.response.ListResponse;
import pl.edu.wat.wcy.invoice.response.ObjectReference;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.InvoiceService;
import pl.edu.wat.wcy.invoice.utils.PdfGenerator;
import pl.nip24.client.AllData;
import pl.nip24.client.InvoiceData;
import pl.nip24.client.NIP24Client;

import javax.websocket.server.PathParam;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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

    @GetMapping(value = "/all")
    public ListResponse<InvoiceDTO> getAll() {
        return new ListResponse<>(invoiceService.getAllInvoices());
    }


    @GetMapping
    public ListResponse<InvoiceDTO> getInvoicesByUserId(@PathParam("userId") Long userId) {
        return new ListResponse<>(invoiceService.getInvoicesByUserId(userId));
    }

    @GetMapping(value = "/recent")
    public ListResponse<InvoiceDTO> getFirstInvoices5ByUserId(@PathParam("userId") Long userId) {
        return new ListResponse<>(invoiceService.getFirstInvoices5ByUserId(userId));
    }

    @PutMapping(value = "/edit")
    public SingleResponse<InvoiceDTO> updateInvoice(@RequestBody InvoiceDTO invoice) {
        return new SingleResponse<>(invoiceService.updateInvoice(invoice));
    }

    @DeleteMapping(value = "/delete")
    public SingleResponse<Boolean> deleteInvoice(@PathParam("invoiceId") Long invoiceId) {
        return new SingleResponse<>(invoiceService.deleteInvoice(invoiceId));
    }

    @GetMapping(value = "/pdf",  produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamSource> generateInvoicePdf(@PathParam("invoiceId") Long invoiceId) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(invoiceService.generateInvoicePdf(invoiceId));
    }

}
