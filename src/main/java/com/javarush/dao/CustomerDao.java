package com.javarush.dao;

import com.javarush.demain.Customer;
import org.hibernate.SessionFactory;

public class CustomerDao  extends GenericDao<Customer>{
    public CustomerDao(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
