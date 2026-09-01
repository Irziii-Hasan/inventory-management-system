package com.jhgadgets.ims.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhgadgets.ims.dto.CustomerResponseDTO;
import com.jhgadgets.ims.model.Customer;
import com.jhgadgets.ims.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@PostMapping
	public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody Customer customer){
		return new ResponseEntity<>(customerService.createCustomer(customer),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers(){
		return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long customerId){
		return new ResponseEntity<CustomerResponseDTO>(customerService.getCustomerById(customerId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomerById(@PathVariable Long customerId){
		customerService.deleteCustomerById(customerId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
