package com.javarush.dao;

import com.javarush.demain.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends GenericDao<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
