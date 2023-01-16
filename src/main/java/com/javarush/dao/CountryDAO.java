package com.javarush.dao;

import com.javarush.demain.Country;
import org.hibernate.SessionFactory;

public class CountryDAO extends GenericDao<Country>{
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
