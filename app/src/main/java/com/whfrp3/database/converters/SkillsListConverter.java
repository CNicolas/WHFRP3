package com.whfrp3.database.converters;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.whfrp3.database.entities.model.skills.Skill;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

public class SkillsListConverter implements PropertyConverter<List<Skill>, String> {
    @Override
    public List<Skill> convertToEntityProperty(String databaseValue) {
        Gson gson = new Gson();
        List<Skill> res = new ArrayList<>();

        List<LinkedTreeMap<String, String>> actual = gson.fromJson(databaseValue, res.getClass());
        for (LinkedTreeMap<String, String> entry : actual) {
            Skill skill = gson.fromJson(entry.toString(), Skill.class);
            res.add(skill);
        }

        return res;
    }

    @Override
    public String convertToDatabaseValue(List<Skill> entityProperty) {
        Gson gson = new Gson();

        return gson.toJson(entityProperty);
    }
}
