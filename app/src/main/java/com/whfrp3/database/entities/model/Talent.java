package com.whfrp3.database.entities.model;

import com.whfrp3.database.enums.CooldownType;
import com.whfrp3.database.enums.TalentType;

/**
 * Talent of a player.
 */
public class Talent implements Comparable<Talent> {

    //region Properties
    private Long id;
    /**
     * Name.
     */
    private String name;

    /**
     * Description.
     */
    private String description;

    /**
     * Type.
     */
    private TalentType type;

    /**
     * Cooldown type.
     */
    private CooldownType cooldown;

    /**
     * Is talent equipped.
     */
    private boolean equipped;

    /**
     * Is talent exhausted.
     */
    private boolean exhausted;

    //endregion

    public boolean isExhaustible() {
        CooldownType cooldown = getCooldown();
        return cooldown != CooldownType.NO_COOLDOWN;
    }

    //region Get & Set

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TalentType getType() {
        return type;
    }

    public void setType(TalentType type) {
        this.type = type;
    }

    public CooldownType getCooldown() {
        return cooldown;
    }

    public void setCooldown(CooldownType cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public boolean isExhausted() {
        return exhausted && isExhaustible();
    }

    public void setExhausted(boolean exhausted) {
        this.exhausted = exhausted;
    }
    //endregion

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Talent{");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", type=").append(type);
        sb.append(", cooldown=").append(cooldown);
        sb.append(", equipped=").append(equipped);
        sb.append(", exhausted=").append(exhausted);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Talent talent) {
        int compared = type.compareTo(talent.getType());
        if (compared == 0) {
            compared = name.compareTo(talent.getName());
        }
        return compared;
    }
}
