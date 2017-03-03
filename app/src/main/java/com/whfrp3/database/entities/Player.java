package com.whfrp3.database.entities;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.converters.CharacteristicsMapConverter;
import com.whfrp3.database.converters.InventoryConverter;
import com.whfrp3.database.converters.ModelListConverter;
import com.whfrp3.database.converters.MoneyConverter;
import com.whfrp3.database.converters.RaceConverter;
import com.whfrp3.database.converters.SkillsListConverter;
import com.whfrp3.database.entities.model.Characteristic;
import com.whfrp3.database.entities.model.Money;
import com.whfrp3.database.entities.model.Talent;
import com.whfrp3.database.entities.model.inventory.IEncumbrance;
import com.whfrp3.database.entities.model.inventory.Inventory;
import com.whfrp3.database.entities.model.skills.Skill;
import com.whfrp3.database.entities.model.skills.Specialization;
import com.whfrp3.database.enums.CharacteristicEnum;
import com.whfrp3.database.enums.Race;
import com.whfrp3.database.enums.SkillType;
import com.whfrp3.helpers.SkillHelper;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(indexes = {
        @Index(value = "name", unique = true)
})
public class Player implements IEncumbrance {

    //region Properties
    @Id
    private Long id;

    @NotNull
    private String name;

    @Convert(converter = RaceConverter.class, columnType = String.class)
    private Race race;
    private int age;
    private int size;
    private String description;

    private String career;
    private int rank;
    private int experience;
    private int maxExperience;
    private int wounds;
    private int maxWounds;
    private int corruption;
    private int maxCorruption;
    private int reckless;
    private int maxReckless;
    private int conservative;
    private int maxConservative;
    private int stress;
    private int tiredness;
    @Convert(converter = MoneyConverter.class, columnType = String.class)
    private Money money;

    @Convert(converter = CharacteristicsMapConverter.class, columnType = String.class)
    private Map<CharacteristicEnum, Characteristic> characteristics;
    @Convert(converter = InventoryConverter.class, columnType = String.class)
    private Inventory inventory;
    @Convert(converter = SkillsListConverter.class, columnType = String.class)
    private List<Skill> skills;
    @Convert(converter = ModelListConverter.class, columnType = String.class)
    private List<Specialization> specializations;
    @Convert(converter = ModelListConverter.class, columnType = String.class)
    private List<Talent> talents;
    //endregion

    @Generated(hash = 823712239)
    public Player(Long id, @NotNull String name, Race race, int age, int size, String description, String career, int rank,
                  int experience, int maxExperience, int wounds, int maxWounds, int corruption, int maxCorruption, int reckless,
                  int maxReckless, int conservative, int maxConservative, int stress, int tiredness, Money money,
                  Map<CharacteristicEnum, Characteristic> characteristics, Inventory inventory, List<Skill> skills,
                  List<Specialization> specializations, List<Talent> talents) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.age = age;
        this.size = size;
        this.description = description;
        this.career = career;
        this.rank = rank;
        this.experience = experience;
        this.maxExperience = maxExperience;
        this.wounds = wounds;
        this.maxWounds = maxWounds;
        this.corruption = corruption;
        this.maxCorruption = maxCorruption;
        this.reckless = reckless;
        this.maxReckless = maxReckless;
        this.conservative = conservative;
        this.maxConservative = maxConservative;
        this.stress = stress;
        this.tiredness = tiredness;
        this.money = money;
        this.characteristics = characteristics;
        this.inventory = inventory;
        this.skills = skills;
        this.specializations = specializations;
        this.talents = talents;
    }

    @Generated(hash = 30709322)
    public Player() {
    }

    public void initCharacteristics() {
        Map<CharacteristicEnum, Characteristic> characteristics = new HashMap<>();

        for (CharacteristicEnum characEnum : CharacteristicEnum.values()) {
            characteristics.put(characEnum, new Characteristic(characEnum, 0, 0));
        }

        this.characteristics = characteristics;
    }

    public Characteristic getCharacteristic(CharacteristicEnum characteristicEnum) {
        return characteristics.get(characteristicEnum);
    }

    public void initSkills() {
        this.skills = SkillHelper.getInstance().getSkillsByType(SkillType.BASIC);
    }

    //region Encumbrance
    public int getEncumbranceOverload() {
        Characteristic characteristic = getCharacteristic(CharacteristicEnum.STRENGTH);

        int encumbrance = (race == Race.DWARF) ? ENCUMBRANCE_BASE_DWARF : ENCUMBRANCE_BASE;
        encumbrance += characteristic.getValue() * ENCUMBRANCE_BY_STRENGTH;
        encumbrance += characteristic.getFortuneValue() * ENCUMBRANCE_BY_STRENGTH_FORTUNE;

        return encumbrance;
    }

    public int getEncumbranceMax() {
        return getEncumbranceOverload() + ENCUMBRANCE_OVERLOAD_TO_MAX;
    }

    public int getCurrentEncumbrance() {
        return getInventory().getCurrentEncumbrance();
    }

    public int getEncumbranceColor() {
        int value = getInventory().getCurrentEncumbrance();

        int color;
        if (value < getEncumbranceOverload()) {
            color = WHFRP3.getResourceColor(R.color.conservative);
        } else if (value < getEncumbranceMax()) {
            color = WHFRP3.getResourceColor(R.color.orange);
        } else {
            color = WHFRP3.getResourceColor(R.color.reckless);
        }

        return color;
    }
    //endregion

    public void addTalent(Talent talent) {
        int indexOfTalent = hasTalent(talent);

        if (indexOfTalent == -1) {
            getTalents().add(talent);
        }
    }

    public int hasTalent(Talent talent) {
        for (int i = 0; i < getTalents().size(); i++) {
            if (getTalents().get(i).equals(talent)) {
                return i;
            }
        }
        return -1;
    }

    public int getMaxStressBeforeComa() {
        return getCharacteristic(CharacteristicEnum.WILLPOWER).getValue() * 2;
    }

    public int getMaxTirednessBeforeComa() {
        return getCharacteristic(CharacteristicEnum.TOUGHNESS).getValue() * 2;
    }

    //region Generated

    /**
     * Can the player be saved in the Database ?
     *
     * @return yes or no
     */
    public boolean isUpdatable() {
        return name != null && !name.isEmpty();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return this.race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCareer() {
        return this.career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getExperience() {
        return this.experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getMaxExperience() {
        return this.maxExperience;
    }

    public void setMaxExperience(int maxExperience) {
        this.maxExperience = maxExperience;
    }

    public int getWounds() {
        return this.wounds;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public int getMaxWounds() {
        return this.maxWounds;
    }

    public void setMaxWounds(int maxWounds) {
        this.maxWounds = maxWounds;
    }

    public int getCorruption() {
        return this.corruption;
    }

    public void setCorruption(int corruption) {
        this.corruption = corruption;
    }

    public int getMaxCorruption() {
        return this.maxCorruption;
    }

    public void setMaxCorruption(int maxCorruption) {
        this.maxCorruption = maxCorruption;
    }

    public int getReckless() {
        return this.reckless;
    }

    public void setReckless(int reckless) {
        this.reckless = reckless;
    }

    public int getMaxReckless() {
        return this.maxReckless;
    }

    public void setMaxReckless(int maxReckless) {
        this.maxReckless = maxReckless;
    }

    public int getConservative() {
        return this.conservative;
    }

    public void setConservative(int conservative) {
        this.conservative = conservative;
    }

    public int getMaxConservative() {
        return this.maxConservative;
    }

    public void setMaxConservative(int maxConservative) {
        this.maxConservative = maxConservative;
    }

    public int getStress() {
        return this.stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public int getTiredness() {
        return this.tiredness;
    }

    public void setTiredness(int tiredness) {
        this.tiredness = tiredness;
    }

    public Money getMoney() {
        return this.money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Map<CharacteristicEnum, Characteristic> getCharacteristics() {
        return this.characteristics;
    }

    public void setCharacteristics(Map<CharacteristicEnum, Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Specialization> getSpecializations() {
        return this.specializations;
    }

    public void setSpecializations(
            List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public List<Talent> getTalents() {
        return this.talents;
    }

    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Player{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", race=").append(race);
        sb.append(", age=").append(age);
        sb.append(", size=").append(size);
        sb.append(", description='").append(description).append('\'');
        sb.append(", career='").append(career).append('\'');
        sb.append(", rank=").append(rank);
        sb.append(", experience=").append(experience);
        sb.append(", maxExperience=").append(maxExperience);
        sb.append(", wounds=").append(wounds);
        sb.append(", maxWounds=").append(maxWounds);
        sb.append(", corruption=").append(corruption);
        sb.append(", maxCorruption=").append(maxCorruption);
        sb.append(", reckless=").append(reckless);
        sb.append(", maxReckless=").append(maxReckless);
        sb.append(", conservative=").append(conservative);
        sb.append(", maxConservative=").append(maxConservative);
        sb.append(", stress=").append(stress);
        sb.append(", tiredness=").append(tiredness);
        sb.append(", money=").append(money);
        sb.append(", characteristics=").append(characteristics);
        sb.append(", inventory=").append(inventory);
        sb.append(", skills=").append(skills);
        sb.append(", specializations=").append(specializations);
        sb.append(", talents=").append(talents);
        sb.append('}');
        return sb.toString();
    }

    //endregion
}
