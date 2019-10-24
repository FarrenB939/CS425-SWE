package edu.mum.cs.cs425.pcrepairtrackingsystem.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("SELECT c FROM Customer AS c WHERE c.email LIKE %?1%")
	public Page<Customer> findAllByEmail(String email, Pageable pageable);
	
	@Query("SELECT c FROM Customer AS c WHERE c.firstName LIKE %?1%")
	public Page<Customer> findAllByFirstName(String firstName, Pageable pageable);
	
	@Query("SELECT c FROM Customer AS c WHERE c.middleName LIKE %?1%")
	public Page<Customer> findAllByMiddleName(String middleName, Pageable pageable);
	
	@Query("SELECT c FROM Customer AS c WHERE c.lastName LIKE %?1%")
	public Page<Customer> findAllByLastName(String lastName, Pageable pageable);
	
	@Query("SELECT c FROM Customer AS c WHERE c.username LIKE %?1%")
	public Page<Customer> findAllByUsername(String username, Pageable pageable);
	
	@Query("SELECT c FROM Customer AS c WHERE c.firstName LIKE %?1% OR c.middleName LIKE %?1% OR c.lastName LIKE %?1% OR c.username LIKE %?1% OR c.email LIKE %?1%")
	public Page<Customer> findAllByValue(String searchString, Pageable pageable);
	
	@Query("SELECT c FROM Customer AS c WHERE c.lastName NOT LIKE 'Closed Customer' AND (c.firstName LIKE %?1% OR c.middleName LIKE %?1% OR c.lastName LIKE %?1% OR c.username LIKE %?1% OR c.email LIKE %?1%)")
	public Page<Customer> findAllOpenByValue(String searchString, Pageable pageable);
	
	public Optional<Customer> findCustomerByEmail(String email);
	
	public Customer findCustomerByUserId(long userId);
}
