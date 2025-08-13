package co.edu.sena.hardware_store.repository;

import co.edu.sena.hardware_store.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
