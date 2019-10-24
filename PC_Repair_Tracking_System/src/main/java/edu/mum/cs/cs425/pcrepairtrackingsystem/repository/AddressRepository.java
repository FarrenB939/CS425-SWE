package edu.mum.cs.cs425.pcrepairtrackingsystem.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	@Query("SELECT a FROM Address a WHERE a.street LIKE %?1%")
	List<Address> findAddressesByStreet(String street);
	
	@Query("SELECT a FROM Address a WHERE a.city LIKE %?1%")
	List<Address> findAddressesByCity(String city);
	
	@Query("SELECT a FROM Address a WHERE a.state LIKE %?1%")
	List<Address> findAddressesByState(String state);
	
	@Query("SELECT a FROM Address a WHERE a.zipCode LIKE %?1%")
	List<Address> findAddressesByZipCode(String zipCode);

}
