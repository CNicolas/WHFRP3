package com.whfrp3.database.converters;

import android.test.suitebuilder.annotation.SmallTest;

import com.whfrp3.database.entities.model.Characteristic;
import com.whfrp3.database.enums.CharacteristicEnum;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


@SmallTest
public class CharacteristicsMapConverterTest {

    @Test
    public void convertToEntityProperty() throws Exception {
        CharacteristicsMapConverter converter = new CharacteristicsMapConverter();

        Map<CharacteristicEnum, Characteristic> expected = new HashMap<>();
        expected.put(CharacteristicEnum.AGILITY, new Characteristic(CharacteristicEnum.AGILITY, 5, 2));

        String toParse = "{\"AGILITY\":{\"characteristicEnum\":\"AGILITY\",\"value\":5,\"fortuneValue\":2}}";
        Map<CharacteristicEnum, Characteristic> actual = converter.convertToEntityProperty(toParse);

        Assert.assertEquals(CharacteristicEnum.AGILITY.toString(), expected.get(CharacteristicEnum.AGILITY).toString(), actual.get(CharacteristicEnum.AGILITY).toString());
    }

    @Test
    public void convertToDatabaseValue() throws Exception {
        CharacteristicsMapConverter converter = new CharacteristicsMapConverter();

        String expected = "{\"AGILITY\":{\"characteristicEnum\":\"AGILITY\",\"value\":5,\"fortuneValue\":2}}";

        Map<CharacteristicEnum, Characteristic> map = new HashMap<>();
        map.put(CharacteristicEnum.AGILITY, new Characteristic(CharacteristicEnum.AGILITY, 5, 2));

        String actual = converter.convertToDatabaseValue(map);

        Assert.assertEquals(CharacteristicEnum.AGILITY.toString(), expected, actual);
    }

}