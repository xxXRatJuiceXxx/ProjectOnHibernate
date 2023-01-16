package com.javarush.dao;

import com.javarush.demain.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RentalDAO extends GenericDao<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getReturnRental() {
        Query<Rental> query = getCurrentSession().createQuery("select r from Rental r where r.raturnDate is null",Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
