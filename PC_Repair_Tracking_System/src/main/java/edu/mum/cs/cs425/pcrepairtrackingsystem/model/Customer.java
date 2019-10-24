package edu.mum.cs.cs425.pcrepairtrackingsystem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Customers")
public class Customer extends User {
	
	public Customer() {
		super();
	}

	public Customer(
			@NotNull(message = "*User Name is required") @NotBlank(message = "* User Name is required") String username,
			@NotNull(message = "*Password is required") @NotBlank(message = "*Password is required") String password,
			@NotNull(message = "*Email Address is required") @NotBlank(message = "*Email Address is required") String email,
			@NotNull(message = "*First Name is required") @NotBlank(message = "*First Name is required") String firstName,
			String middleName,
			@NotNull(message = "*Last Name is required") @NotBlank(message = "*Last Name is required") String lastName) {
		super(username, password, email, firstName, middleName, lastName);
		// TODO Auto-generated constructor stub
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_fk")
	private Address address;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ticket> tickets = new ArrayList<Ticket>();

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
