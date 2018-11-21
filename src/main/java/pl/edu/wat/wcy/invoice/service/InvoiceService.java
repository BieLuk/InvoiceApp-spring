package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.InvoiceDTO;
import pl.edu.wat.wcy.invoice.model.Invoice;
import pl.edu.wat.wcy.invoice.repository.InvoiceRepository;
import pl.edu.wat.wcy.invoice.response.ObjectReference;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public InvoiceDTO getInvoice(Long id){
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exisst id = " + id));
        InvoiceDTO invoiceDTO = modelMapper.map(invoice, InvoiceDTO.class);

        return invoiceDTO;
    }

    public ObjectReference createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = modelMapper.map(invoiceDTO, Invoice.class);
        invoiceRepository.save(invoice);
        return new ObjectReference(invoice.getId());
    }

}
