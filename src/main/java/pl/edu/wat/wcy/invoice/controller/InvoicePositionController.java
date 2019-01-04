package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.invoice.dto.InvoicePositionDTO;
import pl.edu.wat.wcy.invoice.response.ListResponse;
import pl.edu.wat.wcy.invoice.response.ObjectReference;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.InvoicePositionService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/invoice/position")
@RequiredArgsConstructor
public class InvoicePositionController {

    private final InvoicePositionService invoicePositionService;

    @PostMapping
    public SingleResponse<ObjectReference> createInvoicePosition(@RequestBody InvoicePositionDTO invoicePositionDTO){
        return new SingleResponse<>(invoicePositionService.createInvoicePosition(invoicePositionDTO));
    }

    @GetMapping
    public ListResponse<InvoicePositionDTO> getAllInvoicePositionsByInvoiceId(@PathParam("invoiceId") Long invoiceId) {
        return new ListResponse<>(invoicePositionService.getAllInvoicePositionsByInvoiceId(invoiceId));
    }

}
