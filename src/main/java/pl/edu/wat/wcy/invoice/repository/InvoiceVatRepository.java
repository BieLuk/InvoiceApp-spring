package pl.edu.wat.wcy.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.invoice.model.InvoiceVat;

public interface InvoiceVatRepository extends JpaRepository<InvoiceVat, Long> {
}
