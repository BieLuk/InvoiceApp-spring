package pl.edu.wat.wcy.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.invoice.model.InvoicePosition;

import java.util.List;

public interface InvoicePositionRepository extends JpaRepository<InvoicePosition, Long> {
    List<InvoicePosition> findAllByInvoiceId(Long invoiceId);
}
