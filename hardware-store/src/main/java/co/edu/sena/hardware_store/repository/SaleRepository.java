package co.edu.sena.hardware_store.repository;

import co.edu.sena.hardware_store.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository <Sale, Long> {
}
