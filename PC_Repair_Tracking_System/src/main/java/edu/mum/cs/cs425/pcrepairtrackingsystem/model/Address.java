package edu.mum.cs.cs425.pcrepairtrackingsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="addresses")
public class Address {

	public Address() {
		super();
	}

	public Address(@NotBlank(message = "*Street must not be blank.") String street,
			@NotBlank(message = "*City must not be blank.") String city,
			@NotBlank(message = "*State must not be blank.") String state,
			@NotBlank(message = "*ZIP code must not be blank.") String zipCode, Customer customer) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.customer = customer;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	
	@NotBlank(message="*Street must not be blank.")
	private String street;
	
	@NotBlank(message="*City must not be blank.")
	private String city;
	
	@NotBlank(message="*State must not be blank.")
	private String state;
	
	@NotBlank(message="*ZIP code must not be blank.")
	private String zipCode;
	
	@OneToOne(mappedBy="address")
	private Customer customer;

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
