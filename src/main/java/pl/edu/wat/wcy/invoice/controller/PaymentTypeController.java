package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.invoice.dto.PaymentTypeDTO;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.PaymentTypeService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/payment")
@RequiredArgsConstructor
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;

    @GetMapping
    public SingleResponse<PaymentTypeDTO> getClient(@PathParam("paymentTypeId") Long paymentTypeId) {
        return new SingleResponse<>(paymentTypeService.getPaymentType(paymentTypeId));
    }

}

