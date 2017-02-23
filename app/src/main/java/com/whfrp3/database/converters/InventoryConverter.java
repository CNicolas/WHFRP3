package com.whfrp3.database.converters;

import com.google.gson.Gson;
import com.whfrp3.database.entities.model.inventory.Inventory;

import org.greenrobot.greendao.converter.PropertyConverter;


public class InventoryConverter implements PropertyConverter<Inventory, String> {

    @Override
    public Inventory convertToEntityProperty(String databaseValue) {
        Gson gson = new Gson();

        return gson.fromJson(databaseValue, Inventory.class);
    }

    @Override
    public String convertToDatabaseValue(Inventory entityProperty) {
        Gson gson = new Gson();

        return gson.toJson(entityProperty);
    }
}