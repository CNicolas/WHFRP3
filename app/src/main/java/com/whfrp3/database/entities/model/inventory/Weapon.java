package com.whfrp3.database.entities.model.inventory;


import com.whfrp3.database.enums.ItemType;
import com.whfrp3.database.enums.Range;

/**
 * Weapon's class.
 */
public class Weapon extends Equipment {
    //region Properties
    /**
     * Damage.
     */
    private int damage;

    /**
     * Critical level.
     */
    private int criticalLevel;

    /**
     * Range.
     */
    private Range range;
    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    public Weapon() {
        super();

        setType(ItemType.WEAPON);
    }

    /**
     * Constructor from another Item.
     *
     * @param item the given item.
     */
    public Weapon(Item item) {
        super(item);

        setType(ItemType.WEAPON);
    }
    //endregion

    public boolean isDistance() {
        return getRange().ordinal() >= Range.SHORT.ordinal();
    }

    public boolean isMelee() {
        return getRange().ordinal() == Range.ENGAGED.ordinal();
    }

    //region Get & Set
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCriticalLevel() {
        return criticalLevel;
    }

    public void setCriticalLevel(int criticalLevel) {
        this.criticalLevel = criticalLevel;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }
    //endregion

    //region Overrides
    @Override
    public String toString() {
        return "Weapon [" + attributesToString() +
                ", equipped=" + isEquipped() +
                ", damage=" + getDamage() +
                ", criticalLevel=" + getCriticalLevel() +
                ", range=" + getRange() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Weapon weapon = (Weapon) o;

        if (getDamage() != weapon.getDamage()) return false;
        if (getCriticalLevel() != weapon.getCriticalLevel()) return false;
        return getRange() == weapon.getRange();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getDamage();
        result = 31 * result + getCriticalLevel();
        result = 31 * result + (getRange() != null ? getRange().hashCode() : 0);
        return result;
    }
//endregion
}
