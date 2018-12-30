package pl.edu.wat.wcy.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.invoice.model.VatType;

public interface VatTypeRepository extends JpaRepository<VatType, Long> {
}
