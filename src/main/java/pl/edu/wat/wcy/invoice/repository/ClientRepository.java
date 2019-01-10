package pl.edu.wat.wcy.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.invoice.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAllByUserIdAndActive(Long userId, boolean active);
    Optional<Client> findByIdAndActive(Long userId, boolean active);

}
