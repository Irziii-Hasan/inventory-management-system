package com.jhgadgets.ims.service;

import java.util.List;

import com.jhgadgets.ims.dto.CustomerResponseDTO;
import com.jhgadgets.ims.model.Customer;

public interface CustomerService {

	public CustomerResponseDTO createCustomer(Customer customer);
	public CustomerResponseDTO getCustomerById(Long customerId);
	public List<CustomerResponseDTO> getAllCustomers();
	public void deleteCustomerById(Long customerId);
}
