package edu.mum.cs.cs425.pcrepairtrackingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Customer;

public interface CustomerService {

	public abstract List<Customer> getAllCustomers();
	public abstract Page<Customer> getCustomersByFirstName(String firstName, int pageNo);
	public abstract Page<Customer> getCustomersByLastName(String lastName, int pageNo);
	public abstract Page<Customer> getAllCustomersPaged(int pageNo);
	public abstract Customer saveCustomer (Customer customer);
	public abstract Optional<Customer> getCustomerById(long customerId);
	public abstract Customer getCustomerByIdNonOptional(long customerId);
	public abstract Optional<Customer> getCustomerByEmail(String email);
	public abstract void deleteCustomerById(long customerId);
	public abstract void closeCustomerById(long customerId);
	public abstract Page<Customer> getAllCustomersBySearchValue(String searchValue, int pageNo);
	public abstract Page<Customer> getAllOpenCustomersBySearchValue(String searchValue, int pageNo);
}
