package co.edu.sena.hardware_store.repository;

import co.edu.sena.hardware_store.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
