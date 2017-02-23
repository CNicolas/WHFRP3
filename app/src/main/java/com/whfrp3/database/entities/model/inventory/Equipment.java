package com.whfrp3.database.entities.model.inventory;

/**
 * Equipment class (Weapon or Armor).
 */
public abstract class Equipment extends Item {
    /**
     * Is equipment equipped ?
     */
    private boolean isEquipped;

    /**
     * Default constructor.
     */
    public Equipment() {
        super();
    }

    /**
     * Constructor from another Item.
     *
     * @param item the given item.
     */
    public Equipment(Item item) {
        super(item);

        if (item instanceof Equipment) {
            Equipment equipment = (Equipment) item;
            setEquipped(equipment.isEquipable() && equipment.isEquipped());
        }
    }

    @Override
    public boolean isEquipable() {
        return true;
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }

}
