package edu.mum.cs.cs425.pcrepairtrackingsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Ticket;


public interface TicketService {
	
	public abstract List<Ticket> getAllTickets();
	public abstract Ticket addNewTicket(Ticket ticket);
	public abstract Ticket saveTicket(Ticket ticket);
	public abstract Page<Ticket> getAllTicketsBySearchValue(String searchString, int pageNo);
	public abstract Page<Ticket> getAllOpenTicketsBySearchValue(String searchString, int pageNo);
	public abstract Ticket getTicketByticketId(Long ticketId);
}
