package co.edu.sena.hardware_store.repository;

import co.edu.sena.hardware_store.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    List<SaleDetail> findBySale_Id(Long idSale);
}
