package edu.mum.cs.cs425.pcrepairtrackingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Address;

public interface AddressService {

	public abstract List<Address> getAllAddresses();
	public abstract List<Address> getAddressesByStreet(String street);
	public abstract List<Address> getAddressesByCity(String city);
	public abstract List<Address> getAddressesByState(String state);
	public abstract List<Address> getAddressesByZipCode(String zipCode);
	public abstract Page<Address> getAllAddressesPaged(int pageNo);
	public abstract Address saveAddress(Address address);
	public abstract Optional<Address> getAddressById(long addressId);
	public abstract void deleteAddressById(long addressId);
}
