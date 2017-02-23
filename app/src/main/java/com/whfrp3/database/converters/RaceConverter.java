package com.whfrp3.database.converters;

import com.whfrp3.database.enums.Race;

import org.greenrobot.greendao.converter.PropertyConverter;

public class RaceConverter implements PropertyConverter<Race, String> {

    @Override
    public Race convertToEntityProperty(String databaseValue) {
        return Race.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(Race entityProperty) {
        return entityProperty.name();
    }
}