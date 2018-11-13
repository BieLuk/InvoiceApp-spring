package pl.edu.wat.wcy.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.invoice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
