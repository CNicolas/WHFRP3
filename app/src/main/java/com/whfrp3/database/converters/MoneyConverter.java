package com.whfrp3.database.converters;

import com.google.gson.Gson;
import com.whfrp3.database.entities.model.Money;

import org.greenrobot.greendao.converter.PropertyConverter;


public class MoneyConverter implements PropertyConverter<Money, String> {

    @Override
    public Money convertToEntityProperty(String databaseValue) {
        Gson gson = new Gson();

        return gson.fromJson(databaseValue, Money.class);
    }

    @Override
    public String convertToDatabaseValue(Money entityProperty) {
        Gson gson = new Gson();

        return gson.toJson(entityProperty);
    }
}