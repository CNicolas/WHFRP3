package com.whfrp3.database.entities.model.skills;

import com.whfrp3.database.enums.CharacteristicEnum;
import com.whfrp3.database.enums.SkillType;

import java.io.Serializable;

/**
 * Skill of a player.
 */
public class Skill implements Serializable {

    private Long id;
    /**
     * Skill name.
     */
    private String name;

    /**
     * Associated characteristic.
     */
    private CharacteristicEnum characteristic;

    /**
     * Skill type.
     */
    private SkillType type;

    /**
     * Level of the associated skill.
     */
    private int level;

    private boolean specialized;

    public boolean isFightSkill() {
        return isWeaponSkill() || isBallisticSkill();
    }

    public boolean isWeaponSkill() {
        return getId() == 9;
    }

    public boolean isBallisticSkill() {
        return getId() == 2;
    }

    //region Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacteristicEnum getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(CharacteristicEnum characteristic) {
        this.characteristic = characteristic;
    }

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isSpecialized() {
        return specialized;
    }

    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
    }
    //endregion

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Skill{");
        sb.append("name='").append(name).append('\'');
        sb.append(", characteristic=").append(characteristic);
        sb.append(", type=").append(type);
        sb.append(", level=").append(level);
        sb.append(", specialized=").append(specialized);
        sb.append('}');
        return sb.toString();
    }
}