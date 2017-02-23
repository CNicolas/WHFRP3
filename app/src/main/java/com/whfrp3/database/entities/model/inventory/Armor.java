package com.whfrp3.database.entities.model.inventory;

import com.whfrp3.database.enums.ItemType;

/**
 * Armor's class.
 */
public class Armor extends Equipment {

    //region Properties
    /**
     * Defense.
     */
    private int defense;
    /**
     * Soak.
     */
    private int soak;
    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    public Armor() {
        super();

        setType(ItemType.ARMOR);
    }

    /**
     * Constructor from another Item.
     *
     * @param item the given item.
     */
    public Armor(Item item) {
        super(item);

        setType(ItemType.ARMOR);
    }
    //endregion

    //region Get & Set
    public int getSoak() {
        return soak;
    }

    public void setSoak(int soak) {
        this.soak = soak;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    //endregion

    @Override
    public String toString() {
        return "Armor{" + attributesToString() +
                ", equipped=" + isEquipped() +
                ", soak=" + getSoak() +
                ", defense=" + getDefense() + "}";
    }
}
