package pl.edu.wat.wcy.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.invoice.model.Client;
import pl.edu.wat.wcy.invoice.model.Invoice;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Client> findAllByUserId(Long userId);
}
