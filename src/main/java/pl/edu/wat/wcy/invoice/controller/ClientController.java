package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.invoice.dto.ClientDTO;
import pl.edu.wat.wcy.invoice.response.ObjectReference;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.ClientService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public SingleResponse<ClientDTO> getClient(@PathParam("clientId") Long clientId) {
        return new SingleResponse<>(clientService.getClient(clientId));
    }

    @PostMapping
    public SingleResponse<ObjectReference> createClient(@RequestBody ClientDTO clientDTO) {
        return new SingleResponse<>(clientService.createClient(clientDTO));
    }

    @PutMapping
    public SingleResponse<ClientDTO> updateClient(@RequestBody ClientDTO client) {
        return new SingleResponse<>(clientService.updateClient(client));
    }

}
