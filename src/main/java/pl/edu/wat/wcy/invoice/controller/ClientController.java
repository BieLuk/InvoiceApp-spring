package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.invoice.dto.ClientDTO;
import pl.edu.wat.wcy.invoice.response.ListResponse;
import pl.edu.wat.wcy.invoice.response.ObjectReference;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.ClientService;
import pl.nip24.client.InvoiceData;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping(value = "/client")
    public SingleResponse<ClientDTO> getClient(@PathParam("clientId") Long clientId) {
        return new SingleResponse<>(clientService.getClient(clientId));
    }

    @PostMapping
    public SingleResponse<ObjectReference> createClient(@RequestBody ClientDTO clientDTO) {
        return new SingleResponse<>(clientService.createClient(clientDTO));
    }

    @PutMapping(value = "/client")
    public SingleResponse<ClientDTO> updateClient(@RequestBody ClientDTO client) {
        return new SingleResponse<>(clientService.updateClient(client));
    }

    @GetMapping
    public ListResponse<ClientDTO> getClientsByUserId(@PathParam("userId") Long userId) {
        return new ListResponse<>(clientService.getClientsByUserId(userId));
    }

    @DeleteMapping(value = "/delete")
    public SingleResponse<Boolean> deleteClient(@PathParam("clientId") Long clientId) {
        return new SingleResponse<>(clientService.deleteClient(clientId));
    }


    @GetMapping(value = "/nip")
    public SingleResponse<InvoiceData> getClientFromApiByNip(@PathParam("nip") String nip) {
        return new SingleResponse<>(clientService.getClientFromApiByNip(nip));
    }

}
