package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.ClientDTO;
import pl.edu.wat.wcy.invoice.model.Client;
import pl.edu.wat.wcy.invoice.repository.ClientRepository;
import pl.edu.wat.wcy.invoice.response.ObjectReference;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public ClientDTO getClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow( () -> new ResourceNotFoundException("Client not exist id: " + clientId));
        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);

        return clientDTO;
    }

    public ObjectReference createClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        clientRepository.save(client);
        return new ObjectReference(client.getId());
    }

    public ClientDTO updateClient(ClientDTO client) {
        Client clientDs = clientRepository.findById(client.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found id: " + client.getId()));

        clientDs.setEmail(client.getEmail());
        clientDs.setName(client.getName());
        clientDs.setStreet(client.getStreet());
        clientDs.setPostcode(client.getPostcode());
        clientDs.setCity(client.getCity());
        clientDs.setNip(client.getNip());
        clientDs.setPhone(client.getPhone());
        clientDs.setWebsite(client.getWebsite());
        clientDs.setComment(client.getComment());

        clientRepository.save(clientDs);
        return client;
    }

}
