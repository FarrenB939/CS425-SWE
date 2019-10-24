package edu.mum.cs.cs425.pcrepairtrackingsystem.service.impl;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Address;
import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Customer;
import edu.mum.cs.cs425.pcrepairtrackingsystem.repository.CustomerRepository;
import edu.mum.cs.cs425.pcrepairtrackingsystem.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll(Sort.by("lastName"));
	}

	@Override
	public Page<Customer> getCustomersByFirstName(String firstName, int pageNo) {
		// TODO Auto-generated method stub
		return customerRepository.findAllByFirstName(firstName, PageRequest.of(pageNo, 50, Sort.by("lastName")));
	}

	@Override
	public Page<Customer> getCustomersByLastName(String lastName, int pageNo) {
		// TODO Auto-generated method stub
		return customerRepository.findAllByLastName(lastName, PageRequest.of(pageNo, 50, Sort.by("lastName")));
	}

	@Override
	public Page<Customer> getAllCustomersPaged(int pageNo) {
		// TODO Auto-generated method stub
		return customerRepository.findAll(PageRequest.of(pageNo, 50, Sort.by("lastName")));
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	@Override
	public Optional<Customer> getCustomerById(long customerId) {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId);
	}

	@Override
	public void closeCustomerById(long customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		
		if (optionalCustomer != null) {
			Customer closedCustomer = optionalCustomer.get();
			closedCustomer.setLastName("Closed Customer" );
			closedCustomer.setMiddleName(" ");
			closedCustomer.setFirstName("#" + closedCustomer.getUserId());
			closedCustomer.setUsername("deletedCust" + closedCustomer.getUserId());
			closedCustomer.setEmail("deletedCust" + closedCustomer.getUserId() + "@deleted.com");
			
			byte[] array = new byte[8]; 
		    new Random().nextBytes(array);
		    String generatedString = new String(array, Charset.forName("UTF-8"));
		    closedCustomer.setPassword(generatedString);
			
			Address blankAddress = new Address("BLANK", "BLANK", "BLANK", "BLANK", closedCustomer);
			closedCustomer.setAddress(blankAddress);
			
			customerRepository.save(closedCustomer);
		}
		
	}

	@Override
	public void deleteCustomerById(long customerId) {
		customerRepository.deleteById(customerId);
	}
	@Override
	public Optional<Customer> getCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		return customerRepository.findCustomerByEmail(email);
	}

	@Override
	public Page<Customer> getAllCustomersBySearchValue(String searchValue, int pageNo) {
		// TODO Auto-generated method stub
		return customerRepository.findAllByValue(searchValue, PageRequest.of(pageNo, 50, Sort.by("lastName")));
	}

	@Override
	public Customer getCustomerByIdNonOptional(long customerId) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findCustomerByUserId(customerId);
		return customer;
	}

	@Override
	public Page<Customer> getAllOpenCustomersBySearchValue(String searchValue, int pageNo) {
		// TODO Auto-generated method stub
		return customerRepository.findAllOpenByValue(searchValue, PageRequest.of(pageNo, 50, Sort.by("lastName")));
	}

	
}
