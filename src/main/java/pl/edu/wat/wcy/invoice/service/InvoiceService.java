package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.InvoiceDTO;
import pl.edu.wat.wcy.invoice.model.*;
import pl.edu.wat.wcy.invoice.repository.InvoiceRepository;
import pl.edu.wat.wcy.invoice.response.ObjectReference;
import pl.edu.wat.wcy.invoice.utils.PdfGenerator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<InvoiceDTO> getInvoicesByUserId(Long userId) {
        return invoiceRepository.findAllByUserIdAndActive(userId, true).stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDTO.class)).collect(Collectors.toList());
    }

    public List<InvoiceDTO> getFirstInvoices5ByUserId(Long userId) {
        return invoiceRepository.findFirst5ByUserIdAndActiveOrderByCreateDateDesc(userId, true).stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDTO.class)).collect(Collectors.toList());
    }

    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAllByActive(true).stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDTO.class)).collect(Collectors.toList());
    }

    public InvoiceDTO getInvoice(Long invoiceId){
        Invoice invoice = invoiceRepository.findByIdAndActive(invoiceId, true)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exist id = " + invoiceId));

        return modelMapper.map(invoice, InvoiceDTO.class);
    }

    public ObjectReference createInvoice(InvoiceDTO invoiceDTO) {

        Invoice invoice = modelMapper.map(invoiceDTO, Invoice.class);
        invoice.setActive(true);

        Set<InvoicePosition> positions = invoice.getInvoicePositions();
        positions.forEach(position -> position.setInvoice(invoice));

        Set<InvoiceVat> vats = invoice.getInvoiceVats();
        vats.forEach(vat -> vat.setInvoice(invoice));

        invoiceRepository.save(invoice);
        return new ObjectReference(invoice.getId());
    }

    public InvoiceDTO updateInvoice(InvoiceDTO invoice) {
        Invoice invoiceDs = invoiceRepository.findByIdAndActive(invoice.getId(), true)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found id: " + invoice.getId()));
        modelMapper.map(invoice, invoiceDs);
        invoiceDs.setActive(true);

        invoiceRepository.save(invoiceDs);
        return invoice;
    }

    public boolean deleteInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exist id = " + invoiceId));
        invoice.setActive(false);
        invoiceRepository.save(invoice);
        return true;
    }

    public InputStreamSource generateInvoicePdf(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exist id = " + invoiceId));

        PdfGenerator pdfGenerator = new PdfGenerator();
        ByteArrayInputStream bis = pdfGenerator.generate(invoice);

        return new InputStreamResource(bis);
    }



}
