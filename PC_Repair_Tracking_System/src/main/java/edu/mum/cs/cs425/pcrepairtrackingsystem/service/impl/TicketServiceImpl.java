package edu.mum.cs.cs425.pcrepairtrackingsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Ticket;
import edu.mum.cs.cs425.pcrepairtrackingsystem.repository.TicketRepository;
import edu.mum.cs.cs425.pcrepairtrackingsystem.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public List<Ticket> getAllTickets() {
		// TODO Auto-generated method stub
		return ticketRepository.findAll();
	}

	@Override
	public Ticket addNewTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		
		return ticketRepository.save(ticket);
	}

	@Override
	public Page<Ticket> getAllTicketsBySearchValue(String searchString, int pageNo) {
		// TODO Auto-generated method stub
		return ticketRepository.findAllTicketsByValue(searchString, PageRequest.of(pageNo, 50, Sort.by("dateOpened")));
	}

	@Override
	public Page<Ticket> getAllOpenTicketsBySearchValue(String searchString, int pageNo) {
		// TODO Auto-generated method stub
		return ticketRepository.findAllOpenTicketsByValue(searchString, PageRequest.of(pageNo, 50, Sort.by("dateOpened")));
	}

	@Override
	public Ticket getTicketByticketId(Long ticketId) {
		// TODO Auto-generated method stub
		return ticketRepository.findTicketByticketId(ticketId);
	}

	@Override
	public Ticket saveTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketRepository.save(ticket);
	}
	

}
