package com.airlinesticketingbackend.service.cust;

import com.airlinesticketingbackend.base.exception.ProcessResultException;
import com.airlinesticketingbackend.base.exception.ProvideExceptionHandler;
import com.airlinesticketingbackend.base.mapper.DozerMapper;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.dto.cust.Customer;
import com.airlinesticketingbackend.entity.cust.CustEntity;
import com.airlinesticketingbackend.repository.cust.CustEntityRepository;
import com.airlinesticketingbackend.service.base.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl extends AbstractService implements CustomerService {

    private final CustEntityRepository custEntityRepository;
    private final DozerMapper dozerMapper;

    @Autowired
    public CustomerServiceImpl(CustEntityRepository custEntityRepository, DozerMapper dozerMapper) {
        this.custEntityRepository = custEntityRepository;
        this.dozerMapper = dozerMapper;
    }

    @Override
    @Transactional
    public GenericResult<Customer> persistCustomer(Customer request) {
        this.validateCustomer(request);
        GenericResult<Customer> response = new GenericResult<>();

        CustEntity custEntity = dozerMapper.map(request, CustEntity.class, "Customer_CustEntity");

        try {
            this.custEntityRepository.save(custEntity);
        } catch (Exception exc) {
            ProcessResult errorResponse = ProcessResult.internalServerError("Error received during create customer. " + exc);
            response.setProcessResult(errorResponse);
            throw new ProvideExceptionHandler("Error received while registering customer: " + exc);
        }

        response.setProcessResult(ProcessResult.success("Persist Customer API"));
        response.setResult(request);
        return response;
    }

    private void validateCustomer(Customer request) {
        if (StringUtils.isBlank(request.getCustomerCode()))
            throw new ProcessResultException(ProcessResult.badRequest("CUSTOMER_CODE_CANNOT_BE_EMPTY" + request.getCustomerCode()), true);
        if (request.getCustomerCode() == null)
            throw new ProcessResultException(ProcessResult.badRequest("CUSTOMER_CODE_CANNOT_BE_NULL" + request.getCustomerCode()), true);
        if (this.checkIfExistingCustomerCode(request.getCustomerCode()))
            throw new ProcessResultException(ProcessResult.badRequest("There is already a customer with this code. " + request.getCustomerCode()), true);
        if (request.getMail() == null)
            throw new ProcessResultException(ProcessResult.badRequest("EMAIL_CANNOT_BE_NULL" + request.getMail()), true);
        if (this.checkIfExistingEmail(request.getMail()))
            throw new ProcessResultException(ProcessResult.badRequest("There is already a customer with this email. " + request.getMail()), true);
        if (request.getUname() == null)
            throw new ProcessResultException(ProcessResult.badRequest("USERNAME_CANNOT_BE_NULL" + request.getUname()), true);
    }

    private boolean checkIfExistingCustomerCode(String customerCode) {
        CustEntity customerEntity = this.custEntityRepository.findByCustCode(customerCode);
        if (customerEntity != null) {
            return true;
        }

        return false;
    }

    private boolean checkIfExistingEmail(String email) {
        CustEntity customerEntity = this.custEntityRepository.findByEmail(email);
        if (customerEntity != null) {
            return true;
        }

        return false;
    }
}
