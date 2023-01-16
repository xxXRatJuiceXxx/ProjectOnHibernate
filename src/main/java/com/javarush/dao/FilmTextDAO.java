package com.javarush.dao;

import com.javarush.demain.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends GenericDao<FilmText> {

    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
