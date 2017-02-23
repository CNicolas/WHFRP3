package com.whfrp3.database.entities.model.inventory;

import com.whfrp3.database.enums.ItemType;

/**
 * Usable item's class.
 */
public class UsableItem extends Item {
    /**
     * Item's loads.
     */
    private int load;

    /**
     * Default constructor.
     */
    public UsableItem() {
        super();

        setType(ItemType.USABLE_ITEM);
    }

    /**
     * Constructor from another Item.
     *
     * @param item the given item.
     */
    public UsableItem(Item item) {
        super(item);

        setType(ItemType.USABLE_ITEM);
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    @Override
    public String toString() {
        return "UsableItem{" + attributesToString() + ", load=" + getLoad() + "}";
    }
}
