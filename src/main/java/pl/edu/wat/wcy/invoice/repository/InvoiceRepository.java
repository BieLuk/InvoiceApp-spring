package pl.edu.wat.wcy.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.invoice.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
