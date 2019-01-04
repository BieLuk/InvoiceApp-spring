package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.InvoicePositionDTO;
import pl.edu.wat.wcy.invoice.model.InvoicePosition;
import pl.edu.wat.wcy.invoice.repository.InvoicePositionRepository;
import pl.edu.wat.wcy.invoice.response.ObjectReference;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoicePositionService {

    private final InvoicePositionRepository invoicePositionRepository;
    ModelMapper modelMapper = new ModelMapper();

    public List<InvoicePositionDTO> getAllInvoicePositionsByInvoiceId(Long invoiceId) {
        return invoicePositionRepository.findAllByInvoiceId(invoiceId).stream()
                .map(invoicePosition -> modelMapper.map(invoicePosition, InvoicePositionDTO.class)).collect(Collectors.toList());
    }

    public ObjectReference createInvoicePosition(InvoicePositionDTO invoicePositionDTO) {
        InvoicePosition invoicePosition = modelMapper.map(invoicePositionDTO, InvoicePosition.class);
        invoicePositionRepository.save(invoicePosition);
        return new ObjectReference(invoicePosition.getId());
    }

}