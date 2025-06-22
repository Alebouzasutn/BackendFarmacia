package com.alejandro.controller;

import com.alejandro.entidad.Employee;
import com.alejandro.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;

@Tag(name = "Employees", description = "Operaciones relacionadas con empleados")
@RestController
@RequestMapping("/api/employees")

public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    @Operation (summary = "Empleado se loguea", description = "Logueo con username y password")
    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestParam String username, @RequestParam String password) {
        Optional<Employee> employee = service.login(username, password);
        return employee.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.status(401).build());
    }
    
    @Operation(summary = "Registrar nuevo empleado", description = "Crea un cliente con empleado, contacto y dirección, puesto, telefono, contraseña")
    @PostMapping
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        return ResponseEntity.ok(service.register(employee));
    }
    @Operation(summary = "Listar empleados", description = "Devuelve una lista de todos los empleados registrados")
    @GetMapping
    public ResponseEntity<List<Employee>> list() {
        return ResponseEntity.ok(service.list());
    }
    
    @Operation(summary = "Listar empleados por id", description = "Devuelve un registro segun id del empleado buscado")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
   
    @Operation(summary = "Modificar empleado", description = "Modificar un empleado por su ID")
   @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee updated) {
        updated.setId(id);
        return ResponseEntity.ok(service.update(updated));
    }
    
    @Operation(summary = "Eliminar empleado", description = "Borra un empleado por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/password")
    public ResponseEntity<Void> changePassword(@PathVariable String username, @RequestBody String newPassword) {
        boolean updated = service.updatePassword(username, newPassword);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
