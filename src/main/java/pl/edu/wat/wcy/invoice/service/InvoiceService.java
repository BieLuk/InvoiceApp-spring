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
import pl.edu.wat.wcy.invoice.model.Client;
import pl.edu.wat.wcy.invoice.model.Invoice;
import pl.edu.wat.wcy.invoice.model.InvoicePosition;
import pl.edu.wat.wcy.invoice.model.InvoiceVat;
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

    public boolean deleteInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exist id = " + invoiceId));
        invoice.setActive(false);
        invoiceRepository.save(invoice);
        return true;
    }

    public ResponseEntity<InputStreamSource> generateInvoicePdf(Long invoiceId) throws IOException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exist id = " + invoiceId));
        String filename = invoice.getInvoiceNumber() + "-" + invoice.getClient().getName() + ".pdf";

        PdfGenerator pdfGenerator = new PdfGenerator();
        ByteArrayInputStream bis = pdfGenerator.generate(invoice);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + filename);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }



}
