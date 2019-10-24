package edu.mum.cs.cs425.pcrepairtrackingsystem.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Entity
@Table(name="tickets")
public class Ticket {


	public Ticket() {
		super();
	}

	public Ticket(@NotNull String status, @NotNull String deviceDetails, @NotNull String deviceType, String deviceBrand,
			@NotNull String customerComments) {
		super();
		this.status = status;
		this.deviceDetails = deviceDetails;
		this.deviceType = deviceType;
		this.deviceBrand = deviceBrand;
		this.customerComments = customerComments;
		this.dateOpened = LocalDate.now();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ticketId;
	
	@NotNull
	private String status;
	
	@Nullable
	private String paymentStatus;
	
	@NotNull
	private String deviceDetails;
	
	@NotNull
	private String deviceType; 
	
	@Nullable
	private String deviceBrand;
	
	@NotNull
	private String customerComments;
	
	@Nullable
	private String technicianComments;
	
	@NotNull
	private Double amountOwed;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOpened = LocalDate.now();
	
	@Nullable
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateClosed;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_fk", nullable = false)
    @NotNull(message = "*Customer is required")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeviceDetails() {
		return deviceDetails;
	}

	public void setDeviceDetails(String deviceDetails) {
		this.deviceDetails = deviceDetails;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceBrand() {
		return deviceBrand;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}

	public String getTechnicianComments() {
		return technicianComments;
	}

	public void setTechnicianComments(String technicianComments) {
		this.technicianComments = technicianComments;
	}

	public Double getAmountOwed() {
		return amountOwed;
	}

	public void setAmountOwed(Double amountOwed) {
		this.amountOwed = amountOwed;
	}

	public LocalDate getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(LocalDate dateOpened) {
		this.dateOpened = dateOpened;
	}

	public LocalDate getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(LocalDate dateClosed) {
		this.dateClosed = dateClosed;
	}

	public String getCustomerComments() {
		return customerComments;
	}

	public void setCustomerComments(String customerComments) {
		this.customerComments = customerComments;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public long getUserId() {
		return customer.getUserId();
	}
}
