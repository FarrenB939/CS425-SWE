package edu.mum.cs.cs425.pcrepairtrackingsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Address;
import edu.mum.cs.cs425.pcrepairtrackingsystem.repository.AddressRepository;
import edu.mum.cs.cs425.pcrepairtrackingsystem.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public List<Address> getAllAddresses() {
		// TODO Auto-generated method stub
		return addressRepository.findAll(Sort.by("street"));
	}

	@Override
	public List<Address> getAddressesByStreet(String street) {
		// TODO Auto-generated method stub
		return addressRepository.findAddressesByStreet(street);
	}

	@Override
	public List<Address> getAddressesByCity(String city) {
		// TODO Auto-generated method stub
		return addressRepository.findAddressesByCity(city);
	}

	@Override
	public List<Address> getAddressesByState(String state) {
		// TODO Auto-generated method stub
		return addressRepository.findAddressesByState(state);
	}

	@Override
	public List<Address> getAddressesByZipCode(String zipCode) {
		// TODO Auto-generated method stub
		return addressRepository.findAddressesByZipCode(zipCode);
	}

	@Override
	public Page<Address> getAllAddressesPaged(int pageNo) {
		// TODO Auto-generated method stub
		return addressRepository.findAll(PageRequest.of(pageNo, 3, Sort.by("street")));
	}

	@Override
	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

	@Override
	public Optional<Address> getAddressById(long addressId) {
		// TODO Auto-generated method stub
		return addressRepository.findById(addressId);
	}

	@Override
	public void deleteAddressById(long addressId) {
		// TODO Auto-generated method stub
		addressRepository.deleteById(addressId);
	}

	

}
