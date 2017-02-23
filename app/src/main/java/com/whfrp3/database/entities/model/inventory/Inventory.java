package com.whfrp3.database.entities.model.inventory;

import com.whfrp3.database.enums.ItemType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<ItemType, List<Item>> itemsMap;

    public Inventory() {
        itemsMap = new HashMap<>();
        itemsMap.put(ItemType.ITEM, new ArrayList<Item>());
        itemsMap.put(ItemType.ARMOR, new ArrayList<Item>());
        itemsMap.put(ItemType.WEAPON, new ArrayList<Item>());
        itemsMap.put(ItemType.USABLE_ITEM, new ArrayList<Item>());
    }

    //region Get Items

    /**
     * Get all items in the Inventory of the Player
     *
     * @return allItems
     */
    public List<Item> getAllItems() {
        List<Item> allItems = new ArrayList<>();

        Collection<List<Item>> itemsMapValues = itemsMap.values();
        for (List<? extends Item> items : itemsMapValues) {
            allItems.addAll(items);
        }

        return allItems;
    }

    /**
     * Get items of Item type
     *
     * @return items
     */
    public List<Item> getItems() {
        return itemsMap.get(ItemType.ITEM);
    }

    /**
     * Get items of Armor type
     *
     * @return armors
     */
    public List<Armor> getArmors() {
        List<Armor> armors = new ArrayList<>();

        List<Item> itemsArmors = itemsMap.get(ItemType.ARMOR);
        for (Item itemArmor : itemsArmors) {
            armors.add((Armor) itemArmor);
        }

        return armors;
    }

    /**
     * Get items of Weapon type
     *
     * @return weapons
     */
    public List<Weapon> getWeapons() {
        List<Weapon> weapons = new ArrayList<>();

        List<Item> itemWeapons = itemsMap.get(ItemType.WEAPON);
        for (Item itemWeapon : itemWeapons) {
            weapons.add((Weapon) itemWeapon);
        }

        return weapons;
    }

    /**
     * Get items of UsableItem type
     *
     * @return usableItems
     */
    public List<UsableItem> getUsableItems() {
        List<UsableItem> armors = new ArrayList<>();

        List<Item> itemsUsableItems = itemsMap.get(ItemType.USABLE_ITEM);
        for (Item itemsUsableItem : itemsUsableItems) {
            armors.add((UsableItem) itemsUsableItem);
        }

        return armors;
    }

    /**
     * Get an Item knowing its Id
     *
     * @param itemId the id of the item to seek
     * @return item
     */
    public Item getItemById(long itemId) {
        Collection<List<Item>> itemsMapValues = itemsMap.values();

        for (List<Item> items : itemsMapValues) {
            for (Item item : items) {
                if (item.getId() == itemId) {
                    return item;
                }
            }
        }

        return null;
    }
    //endregion

    //region Add, Remove and Update items
    public void addItem(Item item) {
        if (item != null) {
            itemsMap.get(item.getType()).add(item);
        }
    }

    public boolean removeItem(Item item) {
        if (item != null) {
            return itemsMap.get(item.getType()).remove(item);
        }

        return false;
    }

    public void updateItem(Item item) {
        Item oldItem = getItemById(item.getId());
        if (oldItem.getType() == item.getType()) {
            List<Item> list = itemsMap.get(oldItem.getType());

            int index = list.indexOf(oldItem);
            list.set(index, item);
        } else {
            itemsMap.get(oldItem.getType()).remove(oldItem);
            itemsMap.get(item.getType()).add(item);
        }
    }
    //endregion

    //region Weapons
    public List<Weapon> getEquippedWeapons() {
        List<Weapon> equippedWeapons = new ArrayList<>();

        List<Weapon> allWeapons = getWeapons();
        for (Weapon weapon :
                allWeapons) {
            if (weapon.isEquipped()) {
                equippedWeapons.add(weapon);
            }
        }

        return equippedWeapons;
    }

    public List<Weapon> getMeleeEquippedWeapons() {
        List<Weapon> meleeWeapons = new ArrayList<>();

        List<Weapon> equippedWeapons = getEquippedWeapons();
        for (Weapon weapon :
                equippedWeapons) {
            if (weapon.isMelee()) {
                meleeWeapons.add(weapon);
            }
        }

        return meleeWeapons;
    }

    public List<Weapon> getDistanceEquippedWeapons() {
        List<Weapon> meleeWeapons = new ArrayList<>();

        List<Weapon> equippedWeapons = getEquippedWeapons();
        for (Weapon weapon :
                equippedWeapons) {
            if (weapon.isDistance()) {
                meleeWeapons.add(weapon);
            }
        }

        return meleeWeapons;
    }
    //endregion

    //region Defense & Soak
    public int getFullDefenseAmount() {
        int res = 0;

        List<Armor> armors = getArmors();
        for (Armor armor : armors) {
            if (armor.isEquipped()) {
                res += armor.getDefense();
            }
        }

        return res;
    }

    public int getFullSoakAmount() {
        int res = 0;

        List<Armor> armors = getArmors();
        for (Armor armor : armors) {
            if (armor.isEquipped()) {
                res += armor.getSoak();
            }
        }

        return res;
    }
    //endregion

    public int getCurrentEncumbrance() {
        int encumbrance = 0;

        List<Item> allItems = getAllItems();
        for (Item item : allItems) {
            encumbrance += item.getEncumbrance() * item.getQuantity();
        }

        return encumbrance;
    }

    //region Generated
    public Map<ItemType, List<Item>> getItemsMap() {
        return itemsMap;
    }

    public void setItemsMap(Map<ItemType, List<Item>> itemsMap) {
        this.itemsMap = itemsMap;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Inventory{");
        sb.append("itemsMap=").append(itemsMap);
        sb.append('}');
        return sb.toString();
    }
    //endregion
}
