package co.edu.sena.hardware_store.repository;

import co.edu.sena.hardware_store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
