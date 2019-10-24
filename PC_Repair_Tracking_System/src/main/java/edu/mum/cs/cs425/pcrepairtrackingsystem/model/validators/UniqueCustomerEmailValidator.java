package edu.mum.cs.cs425.pcrepairtrackingsystem.model.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.cs.cs425.pcrepairtrackingsystem.service.CustomerService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueCustomerEmailValidator implements ConstraintValidator<UniqueCustomerEmail, Long> {

    @Autowired
    private CustomerService customerService;

    public UniqueCustomerEmailValidator() { }

    public UniqueCustomerEmailValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void initialize(UniqueCustomerEmail constraintAnnotation) {

    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean valid;
        if(customerService != null) {
            valid = (email != null && !customerService.getCustomerByEmail(email).isPresent());
        } else {
            valid = true;
        }
        return valid;
    }

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}
}
