package com.greatlearning.Crm.service;

import com.greatlearning.Crm.entity.Customer;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService
{
    List<Customer> findAll();
    
    Customer findById(final int p0);
    
    void save(final Customer p0);
    
    void deleteById(final int p0);
}
