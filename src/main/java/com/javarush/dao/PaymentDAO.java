package com.javarush.dao;

import com.javarush.demain.Payment;
import org.hibernate.SessionFactory;

public class PaymentDAO extends GenericDao<Payment>{
    public PaymentDAO(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
