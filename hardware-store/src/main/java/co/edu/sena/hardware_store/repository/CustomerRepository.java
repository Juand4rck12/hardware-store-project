package co.edu.sena.hardware_store.repository;

import co.edu.sena.hardware_store.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
