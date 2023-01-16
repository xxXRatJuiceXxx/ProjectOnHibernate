package com.javarush.dao;

import com.javarush.demain.Actor;
import com.javarush.demain.Address;
import org.hibernate.SessionFactory;

public class ActorDAO extends GenericDao<Actor> {
public ActorDAO(SessionFactory sessionFactory) {
        super (Actor.class, sessionFactory);}
}
