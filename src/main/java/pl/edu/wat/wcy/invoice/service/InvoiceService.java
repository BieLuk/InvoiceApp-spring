package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.InvoiceDTO;
import pl.edu.wat.wcy.invoice.model.Invoice;
import pl.edu.wat.wcy.invoice.repository.InvoiceRepository;
import pl.edu.wat.wcy.invoice.response.ObjectReference;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<InvoiceDTO> getInvoicesByUserId(Long userId) {
        return invoiceRepository.findAllByUserId(userId).stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDTO.class)).collect(Collectors.toList());
    }

    public InvoiceDTO getInvoice(Long id){
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exisst id = " + id));
        return modelMapper.map(invoice, InvoiceDTO.class);
    }

    public ObjectReference createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = modelMapper.map(invoiceDTO, Invoice.class);
        invoiceRepository.save(invoice);
        return new ObjectReference(invoice.getId());
    }

}
