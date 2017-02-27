package com.whfrp3.database.entities.model;


import com.whfrp3.database.enums.CharacteristicEnum;

public class Characteristic {
    private CharacteristicEnum characteristicEnum;

    private int value;

    private int fortuneValue;

    public Characteristic(CharacteristicEnum characteristicEnum, int value, int fortuneValue) {
        this.characteristicEnum = characteristicEnum;
        this.value = value;
        this.fortuneValue = fortuneValue;
    }

    public CharacteristicEnum getCharacteristicEnum() {
        return characteristicEnum;
    }

    public void setCharacteristicEnum(CharacteristicEnum characteristicEnum) {
        this.characteristicEnum = characteristicEnum;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFortuneValue() {
        return fortuneValue;
    }

    public void setFortuneValue(int fortuneValue) {
        this.fortuneValue = fortuneValue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CharacteristicEnum{");
        sb.append("characteristicEnum=").append(characteristicEnum);
        sb.append(", value=").append(value);
        sb.append(", fortuneValue=").append(fortuneValue);
        sb.append('}');
        return sb.toString();
    }
}
