package com.javarush.dao;

import com.javarush.demain.Inventory;
import org.hibernate.SessionFactory;

public class InventoryDAO  extends GenericDao<Inventory>{
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
