package com.javarush.demain;

public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");
    private String value;

    Rating(String value){
        this.value =value;
    }

    public String getValue() {
        return value;
    }
}
