package edu.mum.cs.cs425.pcrepairtrackingsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Customer;
import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

	public Ticket findTicketByticketId(Long ticketId);
	
	public Page<Ticket> findAllTicketsByCustomer(Customer customer, Pageable pageable);
	
	public Long countByStatus(String status);
	
	@Query("SELECT t FROM Ticket AS t WHERE t.customerComments LIKE %?1% OR t.paymentStatus LIKE %?1% OR t.technicianComments LIKE %?1% OR t.deviceDetails LIKE %?1% OR t.deviceType LIKE %?1% OR t.deviceBrand LIKE %?1% OR t.customer.lastName LIKE %?1% OR t.customer.firstName LIKE %?1%")
	public Page<Ticket> findAllTicketsByValue(String searchString, Pageable pageable);
	
	@Query("SELECT t FROM Ticket AS t WHERE t.status = 'Open' AND (t.paymentStatus LIKE %?1% OR t.customerComments LIKE %?1% OR t.technicianComments LIKE %?1% OR t.deviceDetails LIKE %?1% OR t.deviceType LIKE %?1% OR t.deviceBrand LIKE %?1% OR t.customer.lastName LIKE %?1% OR t.customer.firstName LIKE %?1%)")
	public Page<Ticket> findAllOpenTicketsByValue(String searchString, Pageable pageable);
}
