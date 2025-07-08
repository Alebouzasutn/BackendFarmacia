/*package com.alejandro;

import org.springframework.http.ResponseEntity;

import com.alejandro.entidad.Customers;
import com.alejandro.entidad.Employee;
import com.alejandro.repository.CustomersRepository;

@Service
public class CustomerService{

	private CustomersRepository customerRepo;
	
	
	public CustomerService (Customers Repository, customerRepo) {
		     this.customerRepo = customerRepo;
		     
	}
	
	
	public Customers register (Customers customer) {
		customer.setCreated(LocalDateTime.now());
		customer.setUpdated(LocalDateTime.now());
		return repository.save(customer);
		}
	
	// MEJOR PRACTICA DECLARO UNA SOLA INSTANCIA DE TIEMPO Y LOS DOS CAMPOS TIENE LA MISMA MARCA DE TIEMPO
	
	public Supplier createSupplier(Supplier supplier) {
        LocalDateTime now = LocalDateTime.now();
        supplier.setCreated(now);
        supplier.setUpdated(now);
        return supplierRepository.save(supplier);
	
	
	
	public List <Customers> list(String value){
		if(value == null || value.trim()isEmpty()) {
		return customerRepo.findAll();
	}else {
		return 
		customerRepo.findByFullNameContainingIgnoreCase(value);
		
	public Optional <Customer> getById(Integer Id){
		return  customerRepo.findById(Id);
	}
	
	@GetMapping
	public ResponseEntity<Employee> getById (@PathVariable Integer Id){
		return service.getById(Id)
				.map(ResponseEntity::ok)
				orElse(ResponseEntity.notFound().build());
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity <Employee> update(@PathVariable Integer Id, @RequestBody Employee updated){
		updated.setId(id);
		return ResponseEntity(service.update(updated));
	}
	
	@PostMapping
	
	public ResponseEntity <Employee> register (@RequestBody Employee employee){
		
		return ResponseEntity.ok(service.register(employee));
	}
	
	@GetMapping
	public ResponseEntity <Employee> list(@RequestBody Employee employee){
		return ResponseEntity.ok(service.list(employees));
		
		/*“@RequestBody se usa cuando el cliente envía datos en el cuerpo del request. desde el front o pruebas
		 * Es común en operaciones de escritura como POST para crear o PUT/PATCH para actualizar. 
		 * El servidor toma ese JSON, lo mapea a un objeto Java employee y lo usa como entrada del método.*/
		 
/*	}
		
	}
	
	public Optional <Customer> getById (Integer Id){
		return customerRepo.findById(Id);	}
	
	public Optional <Employee> login(String username, string password){
		return customerRepo.findbyUsernameAndPassword(username, password);
	}
	
	public Customers update (Customers customer) {
		customer.setUpdated(LocalDateTime.now());
		return customerRepo.save(customer)
		
	}
	
	public Optional <Supplier> getById (Integer id){
		return supplierrepo.findById(Id);
	}
	
	public <List<Suppliers> list(String value){
		if(supplier == null || supplier.trim().isEmpty()) {
			return supplerrepo.findAll();
		}else {
			return supplierrepo.findByContaingningIgnoreCase(value);
		}
		
		public void delete (Integer id) {
			customerrepo.deleteById(id);
			
		}
		
		public ResponseEntity <void> delete (Integer id){
			customerservice.delete(id);
			return ResponseEntity.noContent().build();
		}
		}*/
	
	
