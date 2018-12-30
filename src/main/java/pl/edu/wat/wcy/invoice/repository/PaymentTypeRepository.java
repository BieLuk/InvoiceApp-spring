package pl.edu.wat.wcy.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.invoice.model.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}
