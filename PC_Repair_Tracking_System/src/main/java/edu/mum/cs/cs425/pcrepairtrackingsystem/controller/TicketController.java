package edu.mum.cs.cs425.pcrepairtrackingsystem.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Customer;
import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Ticket;
import edu.mum.cs.cs425.pcrepairtrackingsystem.service.CustomerService;
import edu.mum.cs.cs425.pcrepairtrackingsystem.service.TicketService;

@Controller
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private CustomerService customerService;
	
	public TicketController(TicketService ticketService, CustomerService customerService) {
		this.ticketService = ticketService;
		this.customerService = customerService;
	}
	

	@GetMapping(value = "/pcrepair/technician/ticketList")
   	public ModelAndView displayAllTickets(
   			@RequestParam(value = "includeClosed", defaultValue = "off", required = false) String includeClosed,
   			@RequestParam(value = "searchValue", defaultValue = "", required = false) String searchValue, 
   			@RequestParam(defaultValue = "0", required = false) int pageno) { 
   		
		
		ModelAndView mav = new ModelAndView();
   		
		if (includeClosed.equalsIgnoreCase("on")) {
			mav.addObject("tickets", ticketService.getAllTicketsBySearchValue(searchValue, pageno));
		}
		else {
			mav.addObject("tickets", ticketService.getAllOpenTicketsBySearchValue(searchValue, pageno));
		}
		
		mav.addObject("currentPageNo", pageno);
   		mav.setViewName("technician/ticketList");

   		return mav;
   	}

    @GetMapping(value = {"/pcrepair/technician/ticketNew"})
    public String displayNewTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "technician/ticketNew";
    }

    @PostMapping(value = {"/pcrepair/technician/ticketNew"})
    public String addNewTicket(@Valid @ModelAttribute("ticket") Ticket ticket,
                                     BindingResult bindingResult, Model model) {
    	
    	
        if (bindingResult.hasErrors()) {
        	List<Customer> customers = customerService.getAllCustomers();
        	model.addAttribute("customers", customers);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "technician/ticketNew";
        }
        
        ticket = ticketService.addNewTicket(ticket);
        return "redirect:/pcrepair/technician/ticketList";
    }
    
    @GetMapping(value = {"/pcrepair/technician/ticketEdit/{ticketId}"})
    public String editTicket(@PathVariable Long ticketId, Model model) {
        Ticket ticket = ticketService.getTicketByticketId(ticketId);
        List<Customer> customers = new ArrayList<Customer>();
        
        if (ticket != null) {
        	
        	Customer customer = ticket.getCustomer();
        	customers.add(customer); //Don't want to be able to change customer
        	model.addAttribute("customers", customers);
        	
        	model.addAttribute("customer", customer);
            model.addAttribute("ticket", ticket);
            //System.out.println("customer is: " + ticket.getCustomer().getFirstName() + " " + ticket.getCustomer().getLastName());
            
            return "technician/ticketEdit";
        }
        
        return "technician/ticketList";
    }

    @PostMapping(value = {"/pcrepair/technician/ticketEdit"})
    public String updateTicket(@Valid @ModelAttribute("ticket") Ticket ticket,
                                BindingResult bindingResult, Model model) {
    	
        if (bindingResult.hasErrors()) {
        	//System.out.println("1. has errors: " + bindingResult.getAllErrors());
        	List<Customer> customers = customerService.getAllCustomers();
        	model.addAttribute(customers);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "technician/ticketEdit";
        }
        
        if (ticket.getStatus().equalsIgnoreCase("Closed") && ticket.getDateClosed() == null)
        	ticket.setDateClosed(LocalDate.now());
        else if (ticket.getStatus().equalsIgnoreCase("Open") && ticket.getDateClosed() != null)
        	ticket.setDateClosed(null);
        
        ticket = ticketService.saveTicket(ticket);
        return "redirect:/pcrepair/technician/ticketList";
    }
}
