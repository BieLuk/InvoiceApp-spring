package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.invoice.dto.PaymentTypeDTO;
import pl.edu.wat.wcy.invoice.dto.VatTypeDTO;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.PaymentTypeService;
import pl.edu.wat.wcy.invoice.service.VatTypeService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/vat")
@RequiredArgsConstructor
public class VatTypeController {
    private final VatTypeService vatTypeService;

    @GetMapping
    public SingleResponse<VatTypeDTO> getClient(@PathParam("vatTypeId") Long vatTypeId) {
        return new SingleResponse<>(vatTypeService.getVatType(vatTypeId));
    }

}

