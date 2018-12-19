package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.ClientDTO;
import pl.edu.wat.wcy.invoice.model.Client;
import pl.edu.wat.wcy.invoice.repository.ClientRepository;
import pl.edu.wat.wcy.invoice.response.ObjectReference;
import pl.nip24.client.InvoiceData;
import pl.nip24.client.NIP24Client;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<ClientDTO> getClientsByUserId(Long userId) {
        return clientRepository.findAllByUserId(userId).stream()
                .map(client -> modelMapper.map(client, ClientDTO.class)).collect(Collectors.toList());
    }

    public ClientDTO getClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow( () -> new ResourceNotFoundException("Client not exist id: " + clientId));
        return modelMapper.map(client, ClientDTO.class);
    }

    public ObjectReference createClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        clientRepository.save(client);
        return new ObjectReference(client.getId());
    }

    public ClientDTO updateClient(ClientDTO client) {
        Client clientDs = clientRepository.findById(client.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found id: " + client.getId()));
        modelMapper.map(client, clientDs);

        clientRepository.save(clientDs);
        return client;
    }

    public InvoiceData getClientFromApiByNip(String nip) {
        try {
            NIP24Client nip24 = new NIP24Client("w4PuMSZH8D4i", "hMlmlqgMCB5O");
            return nip24.getInvoiceData(nip);
        } catch  (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }

}
