package com.whfrp3.database.converters;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.whfrp3.database.entities.model.Characteristic;
import com.whfrp3.database.enums.CharacteristicEnum;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.HashMap;
import java.util.Map;

public class CharacteristicsMapConverter implements PropertyConverter<Map<CharacteristicEnum, Characteristic>, String> {
    @Override
    public Map<CharacteristicEnum, Characteristic> convertToEntityProperty(String databaseValue) {
        Gson gson = new Gson();
        Map<CharacteristicEnum, Characteristic> res = new HashMap<>();

        Map<String, LinkedTreeMap<String, String>> actual = gson.fromJson(databaseValue, res.getClass());
        for (Map.Entry<String, LinkedTreeMap<String, String>> entry : actual.entrySet()) {
            CharacteristicEnum characteristicEnum = CharacteristicEnum.valueOf(entry.getKey());
            Characteristic characteristic = gson.fromJson(entry.getValue().toString(), Characteristic.class);

            res.put(characteristicEnum, characteristic);
        }

        return res;
    }

    @Override
    public String convertToDatabaseValue(Map<CharacteristicEnum, Characteristic> entityProperty) {
        Gson gson = new Gson();

        return gson.toJson(entityProperty);
    }
}
