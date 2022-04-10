package com.greatlearning.Crm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.greatlearning.Crm.entity.Customer;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.greatlearning.Crm.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/customer" })
public class CustomerController
{
    @Autowired
    private CustomerService customerservice;
    
    @RequestMapping({ "/list" })
    public String listcustomers(final Model model) {
        final List<Customer> customers = (List<Customer>)this.customerservice.findAll();
        model.addAttribute("Customer", (Object)customers);
        return "list-customer";
    }
    
    @RequestMapping({ "/showFormForAdd" })
    public String showFormForAdd(final Model model) {
        final Customer customer = new Customer();
        model.addAttribute("Customer", (Object)customer);
        return "customer-form";
    }
    
    @RequestMapping({ "/showFormForUpdate" })
    public String showFormForUpdate(@RequestParam("customerId") final int id, final Model model) {
        Customer customer = new Customer();
        customer = this.customerservice.findById(id);
        model.addAttribute("Customer", (Object)customer);
        return "customer-form";
    }
    
    @PostMapping({ "/save" })
    public String savecustomer(@RequestParam("id") final int id, @RequestParam("firstName") final String Firstname, @RequestParam("lastName") final String Lastname, @RequestParam("email") final String Email) {
        System.out.print(id);
        Customer customer;
        if (id != 0) {
            customer = this.customerservice.findById(id);
            customer.setFirstName(Firstname);
            customer.setLastName(Lastname);
            customer.setEmail(Email);
        }
        else {
            customer = new Customer(Firstname, Lastname, Email);
        }
        this.customerservice.save(customer);
        return "redirect:/customer/list";
    }
    
    @RequestMapping({ "/delete" })
    public String deleteCustomer(@RequestParam("customerId") final int id) {
        this.customerservice.deleteById(id);
        return "redirect:/customer/list";
    }
}