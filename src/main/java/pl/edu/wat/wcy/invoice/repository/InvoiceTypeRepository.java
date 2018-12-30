package pl.edu.wat.wcy.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.invoice.model.InvoiceType;

public interface InvoiceTypeRepository extends JpaRepository<InvoiceType, Long> {
}
