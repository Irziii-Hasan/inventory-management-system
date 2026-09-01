package com.jhgadgets.ims.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jhgadgets.ims.dto.CustomerResponseDTO;
import com.jhgadgets.ims.exception.ResourceNotFoundException;
import com.jhgadgets.ims.mapper.CustomerMapper;
import com.jhgadgets.ims.model.Customer;
import com.jhgadgets.ims.repository.CustomerRepository;
import com.jhgadgets.ims.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	
	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		super();
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public CustomerResponseDTO createCustomer(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		logger.info("Customer created with id: {}", savedCustomer.getId());
		return customerMapper.toDto(savedCustomer);
	}

	@Override
	public CustomerResponseDTO getCustomerById(Long customerId) {
		return customerMapper.toDto(customerRepository.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException("Customer", "id", customerId)));
	}

	@Override
	public List<CustomerResponseDTO> getAllCustomers() {
		return customerMapper.toDTOList(customerRepository.findAll());
	}

	@Override
	public void deleteCustomerById(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException("Customer", "id", customerId));
		customerRepository.deleteById(customer.getId());
	}

}
