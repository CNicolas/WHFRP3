package com.whfrp3.database.converters;

import android.test.suitebuilder.annotation.SmallTest;

import com.whfrp3.database.entities.model.skills.Skill;
import com.whfrp3.database.enums.CharacteristicEnum;
import com.whfrp3.database.enums.SkillType;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@SmallTest
public class SkillsListConverterTest {
    @Test
    public void convertToEntityProperty() throws Exception {
        SkillsListConverter converter = new SkillsListConverter();

        List<Skill> expected = new ArrayList<>();
        expected.add(new Skill(1L, "NOM", CharacteristicEnum.AGILITY, SkillType.BASIC));
        expected.add(new Skill(2L, "NOM2", CharacteristicEnum.STRENGTH, SkillType.BASIC));

        String toParse = "[{\"id\":1,\"name\":\"NOM\",\"characteristic\":\"AGILITY\",\"type\":\"BASIC\",\"level\":0,\"specialized\":false},{\"id\":2,\"name\":\"NOM2\",\"characteristic\":\"STRENGTH\",\"type\":\"BASIC\",\"level\":0,\"specialized\":false}]";
        List<Skill> actual = converter.convertToEntityProperty(toParse);

        Assert.assertEquals(CharacteristicEnum.AGILITY.toString(), expected.get(0).toString(), actual.get(0).toString());
        Assert.assertEquals(CharacteristicEnum.STRENGTH.toString(), expected.get(1).toString(), actual.get(1).toString());
    }

    @Test
    public void convertToDatabaseValue() throws Exception {
        SkillsListConverter converter = new SkillsListConverter();

        String expected = "[{\"id\":1,\"name\":\"NOM\",\"characteristic\":\"AGILITY\",\"type\":\"BASIC\",\"level\":0,\"specialized\":false},{\"id\":2,\"name\":\"NOM2\",\"characteristic\":\"STRENGTH\",\"type\":\"BASIC\",\"level\":0,\"specialized\":false}]";

        List<Skill> map = new ArrayList<>();
        map.add(new Skill(1L, "NOM", CharacteristicEnum.AGILITY, SkillType.BASIC));
        map.add(new Skill(2L, "NOM2", CharacteristicEnum.STRENGTH, SkillType.BASIC));

        String actual = converter.convertToDatabaseValue(map);

        Assert.assertEquals(CharacteristicEnum.AGILITY.toString(), expected, actual);
    }
}
