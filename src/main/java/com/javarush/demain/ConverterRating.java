package com.javarush.demain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ConverterRating implements AttributeConverter<Rating,String> {

    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        Rating[] val = Rating.values();
        for (Rating rating:val) {
            if(rating.getValue().equals(dbData)){
                return rating;
            }
        }
        return null;
    }
}
