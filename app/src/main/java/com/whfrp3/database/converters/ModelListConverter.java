package com.whfrp3.database.converters;

import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

public class ModelListConverter<T> implements PropertyConverter<List<T>, String> {

    @Override
    public List<T> convertToEntityProperty(String databaseValue) {
        Gson gson = new Gson();

        return gson.fromJson(databaseValue, List.class);
    }

    @Override
    public String convertToDatabaseValue(List<T> entityProperty) {
        Gson gson = new Gson();

        return gson.toJson(entityProperty);
    }
}