package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.invoice.dto.InvoiceDTO;
import pl.edu.wat.wcy.invoice.model.Invoice;
import pl.edu.wat.wcy.invoice.response.ObjectReference;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.InvoiceService;
import pl.nip24.client.AllData;
import pl.nip24.client.InvoiceData;
import pl.nip24.client.NIP24Client;

import java.net.MalformedURLException;

@RestController
@RequestMapping(value = "/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping(value = "/{id}")
    public SingleResponse<InvoiceDTO> getInvoice(@PathVariable("id") Long id){
        return new SingleResponse<>(invoiceService.getInvoice(id));
    }

    @PostMapping
    public SingleResponse<ObjectReference> createInvoice(@RequestBody InvoiceDTO invoiceDTO){
        return new SingleResponse<>(invoiceService.createInvoice(invoiceDTO));
    }

//    @GetMapping(value = "/client/{nip}")
//    public InvoiceData getNIP(@PathVariable("nip") String nip){
//        try {
//            NIP24Client nip24 = new NIP24Client("w4PuMSZH8D4i", "hMlmlqgMCB5O");
//            return nip24.getInvoiceData(nip);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
