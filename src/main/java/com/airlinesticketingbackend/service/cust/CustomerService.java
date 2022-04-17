package com.airlinesticketingbackend.service.cust;

import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.cust.Customer;

public interface CustomerService {
    GenericResult<Customer> persistCustomer(Customer request);
}
