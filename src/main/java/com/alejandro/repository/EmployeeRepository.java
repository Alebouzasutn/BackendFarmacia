package com.alejandro.repository;

import com.alejandro.entidad.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsernameAndPassword(String username, String password);
    Optional<Employee> findByUsername(String username);
    List <Employee> findByFullNameCotainingIgnoreCase(String value);
}
