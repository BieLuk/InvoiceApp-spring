package pl.edu.wat.wcy.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.invoice.model.Client;
import pl.edu.wat.wcy.invoice.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByUserIdAndActive(Long userId, boolean active);
    Optional<Invoice> findByIdAndActive(Long userId, boolean active);
    List<Invoice> findAllByActive(boolean active);

    List<Invoice> findFirst5ByUserIdAndActiveOrderByCreateDateDesc(Long userId, boolean active);
}
