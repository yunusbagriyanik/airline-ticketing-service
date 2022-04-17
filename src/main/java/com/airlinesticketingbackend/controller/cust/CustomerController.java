package com.airlinesticketingbackend.controller.cust;

import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.cust.Customer;
import com.airlinesticketingbackend.service.cust.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/createCustomer")
    public ResponseEntity<GenericResult<Customer>> createCustomer(@RequestBody Customer request) {
        GenericResult<Customer> response = customerService.persistCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
