package com.whfrp3.database.entities.model.inventory;


import com.whfrp3.database.enums.ItemType;
import com.whfrp3.database.enums.Quality;

import java.io.Serializable;

/**
 * Item's class.
 */
public class Item implements Serializable {
    //region Properties
    private Long id;
    /**
     * Item's name.
     */
    private String name;

    /**
     * Item's description.
     */
    private String description;

    /**
     * Item's encumbrance.
     */
    private int encumbrance;

    /**
     * Item's quantity.
     */
    private int quantity;

    /**
     * Item's quality.
     */
    private Quality quality;

    /**
     * Item's type.
     */
    private ItemType type;
    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    public Item() {
        setType(ItemType.ITEM);
    }

    /**
     * Constructor from another Item.
     *
     * @param item the given item.
     */
    public Item(Item item) {
        setName(item.getName());
        setDescription(item.getDescription());
        setEncumbrance(item.getEncumbrance());
        setQuantity(item.getQuantity());
        setQuality(item.getQuality());
        setType(item.getType());
    }
    //endregion

    /**
     * Indicate if the item can be equipped.
     *
     * @return true if the item can be equipped, false otherwise.
     */
    public boolean isEquipable() {
        return false;
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

    public int getEncumbrance() {
        return encumbrance;
    }

    public void setEncumbrance(int encumbrance) {
        this.encumbrance = encumbrance;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
    //endregion

    //region Item conversion methods

    /**
     * Convert the item into an armor, if possible.
     *
     * @return Armor.
     * @throws UnsupportedOperationException The item cannot be converted into an armor.
     */
    public Armor toArmor() throws UnsupportedOperationException {
        if (getType() != ItemType.ARMOR) {
            throw new UnsupportedOperationException("This item is not an armor : " + toString());
        }

        return (Armor) this;
    }

    /**
     * Convert the item into a weapon, if possible.
     *
     * @return Weapon.
     * @throws UnsupportedOperationException The item cannot be converted into a weapon.
     */
    public Weapon toWeapon() throws UnsupportedOperationException {
        if (getType() != ItemType.WEAPON) {
            throw new UnsupportedOperationException("This item is not a weapon : " + toString());
        }

        return (Weapon) this;
    }

    /**
     * Convert the item into an usable item, if possible.
     *
     * @return Usable item.
     * @throws UnsupportedOperationException The item cannot be converted into an usable item.
     */
    public UsableItem toUsableItem() throws UnsupportedOperationException {
        if (getType() != ItemType.USABLE_ITEM) {
            throw new UnsupportedOperationException("This item is not an usable item : " + toString());
        }

        return (UsableItem) this;
    }
    //endregion

    String attributesToString() {
        return "name='" + getName() + '\'' +
                ", description=" + getDescription() +
                ", encumbrance=" + getEncumbrance() +
                ", quantity=" + getQuantity() +
                ", quality=" + getQuality() +
                ", type=" + getType();
    }

    @Override
    public String toString() {
        return "Item{" + attributesToString() + "}";
    }
}
