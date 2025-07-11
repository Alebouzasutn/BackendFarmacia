package com.alejandro.service;

import com.alejandro.entidad.Employee;
import com.alejandro.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    
    public Employee register(Employee employee) {
        
    	employee.setCreated(LocalDateTime.now());
        employee.setUpdated(LocalDateTime.now());
       
        return repo.save(employee);
    
        }
    


    public List<Employee> list(String fullName) {
        
    	if(fullName == null || fullName.trim().isEmpty()) {
    	return repo.findAll();
    }else {
    	return repo.findByFullNameContainingIgnoreCase(fullName);
    }
    }    
    
        
     public Optional<Employee> getById(Integer id) {
        return repo.findById(id);
    }

    public Optional<Employee> login(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }

    /*Este enfoque es útil cuando el objeto recibido 
    ya ha sido validado y contiene todos los datos necesarios. Permite rapidez y simplicidad en contextos controlados*/
    
    public Employee update(Employee employee) {
        employee.setUpdated(LocalDateTime.now());
        return repo.save(employee);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public boolean updatePassword(String username, String newPassword) {
        Optional<Employee> employee = repo.findByUsername(username);
        if (employee.isPresent()) {
            Employee e = employee.get();
            e.setPassword(newPassword);
            e.setUpdated(LocalDateTime.now());
            repo.save(e);
            return true;
        }
        return false;
    }
}
