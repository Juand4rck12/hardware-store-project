package co.edu.sena.hardware_store.repository;

import co.edu.sena.hardware_store.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
