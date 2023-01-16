package com.javarush.dao;

import com.javarush.demain.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO  extends  GenericDao<Staff>{
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
