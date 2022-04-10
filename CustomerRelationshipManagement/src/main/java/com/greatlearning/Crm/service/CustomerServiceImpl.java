package com.greatlearning.Crm.service;

import java.io.Serializable;
import org.hibernate.Transaction;
import com.greatlearning.Crm.entity.Customer;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerServiceImpl implements CustomerService
{
    private SessionFactory sessionfactory;
    private Session session;
    
    public CustomerServiceImpl(final SessionFactory sessionfactory) {
        this.sessionfactory = sessionfactory;
        try {
            this.session = sessionfactory.getCurrentSession();
        }
        catch (HibernateException e) {
            this.session = sessionfactory.openSession();
        }
    }
    
    @Override
    public List<Customer> findAll() {
        final Transaction transaction = this.session.beginTransaction();
        final List<Customer> customers = (List<Customer>)this.session.createQuery("from Customer").list();
        transaction.commit();
        return customers;
    }
    
    @Override
    public Customer findById(final int theid) {
        final Transaction transaction = this.session.beginTransaction();
        final Customer customer = (Customer)this.session.get((Class)Customer.class, (Serializable)theid);
        transaction.commit();
        return customer;
    }
    
    @Override
    public void save(final Customer customer) {
        final Transaction transaction = this.session.beginTransaction();
        this.session.saveOrUpdate((Object)customer);
        transaction.commit();
    }
    
    @Override
    public void deleteById(final int id) {
        final Transaction transaction = this.session.beginTransaction();
        final Customer customer = (Customer)this.session.get((Class)Customer.class, (Serializable)id);
        this.session.delete((Object)customer);
        transaction.commit();
    }
}
