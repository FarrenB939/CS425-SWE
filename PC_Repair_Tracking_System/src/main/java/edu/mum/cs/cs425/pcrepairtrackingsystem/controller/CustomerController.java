package edu.mum.cs.cs425.pcrepairtrackingsystem.controller;


import java.util.ListIterator;
import java.util.Optional;

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

import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Address;
import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Customer;
import edu.mum.cs.cs425.pcrepairtrackingsystem.model.Ticket;
import edu.mum.cs.cs425.pcrepairtrackingsystem.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@Autowired
	private CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	

	@GetMapping(value = "/pcrepair/technician/customerList")
   	public ModelAndView displayAllCustomers(
   			@RequestParam(value = "searchValue", defaultValue = "", required = false) String searchValue,
   			@RequestParam(value = "includeClosed", defaultValue = "off", required = false) String includeClosed,
   			@RequestParam(defaultValue = "0", required = false) int pageno) { 
   		
		
		ModelAndView mav = new ModelAndView();
   		

		if (includeClosed.equalsIgnoreCase("on")) {
			mav.addObject("customers", customerService.getAllCustomersBySearchValue(searchValue, pageno));
		}
		else {
			mav.addObject("customers", customerService.getAllOpenCustomersBySearchValue(searchValue, pageno));
		}
			
		mav.addObject("currentPageNo", pageno);
   		mav.setViewName("technician/customerList");

   		return mav;
   	}
	
    @GetMapping(value = {"/pcrepair/technician/customerNew"})
    public String displayNewCustomerForm(Model model) {
    	Customer newCust = new Customer();
    	Address newAddr = new Address();
    	newAddr.setCustomer(newCust);
    	newCust.setAddress(newAddr);
    	
        model.addAttribute("customer", newCust);
        model.addAttribute("address", newAddr);
        return "technician/customerNew";
    }

    @PostMapping(value = {"/pcrepair/technician/customerNew"})
    public String addNewCustomer(@Valid @ModelAttribute("customer") Customer customer,
    						 @Valid @ModelAttribute("address") Address address,
                                     BindingResult bindingResult, Model model) {
    	
    	
        if (bindingResult.hasErrors()) {
        	System.out.println("In errors");
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "technician/customerNew";
        }
        
        customer.setAddress(address);
        address.setCustomer(customer);

        customer = customerService.saveCustomer(customer);
        return "redirect:/pcrepair/technician/customerList";
    }
    
    @GetMapping(value = {"/pcrepair/technician/customerEdit/{userId}"})
    public String editCustomer(@PathVariable Integer userId, Model model) throws Exception {
        Optional<Customer> customerById = customerService.getCustomerById(userId);
        
        if (!customerById.isPresent()) {
            throw new Exception("Customer with id " + userId + " does not exist");
        }
        
        Customer customer = customerById.get();
        Address address = customer.getAddress();
        
        if (customer != null) {
            model.addAttribute("customer", customer);
            model.addAttribute("address", address);
            return "technician/customerEdit";
        }
        
        return "technician/customerList";
    }

    @PostMapping(value = {"/pcrepair/technician/customerEdit"})
    public String updateCustomer(@Valid @ModelAttribute("customer") Customer customer,
                                BindingResult bindingResult, Model model) {
    	
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "technician/customerEdit";
        }
        
        customer = customerService.saveCustomer(customer);
        return "redirect:/technician/customerList";
    }
    
    @GetMapping(value = {"/pcrepair/technician/customerClose/{userId}"})
    public String closeCustomer(@PathVariable Integer userId, Model model) throws Exception {
    	Optional<Customer> customerById = customerService.getCustomerById(userId);
    	
    	if (!customerById.isPresent()) {
            throw new Exception("Customer with id " + userId + " does not exist");
        }
    	
    	Customer customer = customerById.get();
    	
    	if (customer != null) {
    		Customer realCust = customerService.getCustomerByIdNonOptional(userId);
    		ListIterator<Ticket> listIter = realCust.getTickets().listIterator();
    		boolean bFoundOpenTicket = false;
    		
    		while (listIter.hasNext()) {
    			Ticket currentTicket = listIter.next();
    			if (currentTicket.getStatus().equalsIgnoreCase("OPEN")) {
    				bFoundOpenTicket = true;
    				break;
    			}
    		}
    		
    		//Don't delete customers with open tickets
    		if (!bFoundOpenTicket)
    			customerService.closeCustomerById(userId);
    	}
    	
        //customerService.deleteCustomerById(userId);
        return "redirect:/pcrepair/technician/customerList";
    }
}
