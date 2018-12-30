package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.InvoiceTypeDTO;
import pl.edu.wat.wcy.invoice.dto.PaymentTypeDTO;
import pl.edu.wat.wcy.invoice.model.InvoiceType;
import pl.edu.wat.wcy.invoice.repository.InvoiceTypeRepository;

@Service
@RequiredArgsConstructor
public class InvoiceTypeService {

    private final InvoiceTypeRepository invoiceTypeRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public InvoiceTypeDTO getInvoiceType(Long invoiceTypeId) {
        InvoiceType invoiceType = invoiceTypeRepository.findById(invoiceTypeId)
                .orElseThrow( () -> new ResourceNotFoundException("InvoiceType not exist id: " + invoiceTypeId));
        return modelMapper.map(invoiceType, InvoiceTypeDTO.class);
    }
}
