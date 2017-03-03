package com.whfrp3.helpers;

import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.LongSparseArray;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.entities.model.skills.Skill;
import com.whfrp3.database.enums.CharacteristicEnum;
import com.whfrp3.database.enums.SkillType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Skill helper used to manage skills.
 */
public class SkillHelper {

    /**
     * Unique instance of the helper.
     */
    private static SkillHelper instance;

    /**
     * Loaded skills.
     */
    private final List<Skill> skills;

    /**
     * Loaded skills by id.
     */
    private final LongSparseArray<Skill> skillsById;

    /**
     * Loaded skills by characteristic.
     */
    private final Map<CharacteristicEnum, List<Skill>> skillsByCharacteristic;

    /**
     * Loaded skills by type.
     */
    private final Map<SkillType, List<Skill>> skillsByType;

    /**
     * Private constructor.
     */
    private SkillHelper() {
        skills = new ArrayList<>();
        skillsById = new LongSparseArray<>();
        skillsByCharacteristic = new HashMap<>();
        for (CharacteristicEnum characteristic : CharacteristicEnum.values()) {
            skillsByCharacteristic.put(characteristic, new ArrayList<Skill>());
        }
        skillsByType = new HashMap<>();
        for (SkillType type : SkillType.values()) {
            skillsByType.put(type, new ArrayList<Skill>());
        }
    }

    /**
     * Getter of the unique instance of the skill helper.
     *
     * @return Unique instance of the skill helper.
     */
    public static SkillHelper getInstance() {
        if (instance == null) {
            instance = new SkillHelper();
        }

        return instance;
    }

    /**
     * Loads skills stored in skills.xml file.
     */
    public void loadSkills() {
        try {
            XmlResourceParser xmlParser = WHFRP3.getResourceXml(R.xml.skills);

            Long skillId = null;
            String skillName = null;
            CharacteristicEnum skillCharacteristic = null;
            SkillType skillType = null;

            int eventType = xmlParser.getEventType();
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG) {
                    if (xmlParser.getName().equals(Skill.class.getSimpleName())) {
                        skillId = Long.valueOf(xmlParser.getAttributeValue(0));
                        skillCharacteristic = CharacteristicEnum.valueOf(xmlParser.getAttributeValue(1));
                        skillType = SkillType.valueOf(xmlParser.getAttributeValue(2));
                    } else if ("Name".equals(xmlParser.getName())) {
                        skillName = xmlParser.nextText();
                    }
                } else if (eventType == XmlResourceParser.END_TAG) {
                    if (xmlParser.getName().equals(Skill.class.getSimpleName()) && skillId != null && skillName != null) {
                        Skill skill = new Skill(skillId, skillName, skillCharacteristic, skillType);

                        skills.add(skill);
                        skillsById.put(skill.getId(), skill);
                        skillsByCharacteristic.get(skill.getCharacteristic()).add(skill);
                        skillsByType.get(skill.getType()).add(skill);

                        skillId = null;
                        skillName = null;
                        skillCharacteristic = null;
                        skillType = null;
                    }
                }

                eventType = xmlParser.next();
            }

            skillsById.size();
        } catch (Exception e) {
            Log.e("SKILL_LOAD", "Erreur de chargement des compétences.", e);
        }
    }

    /**
     * Return all the skills.
     *
     * @return All the skills.
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * Return the skill with de given id.
     *
     * @param id Id of the skill.
     * @return Skill with the given id.
     */
    public Skill getSkill(long id) {
        return skillsById.get(id);
    }

    /**
     * Return the skills of the given characteristic.
     *
     * @param characteristic Characteristic of the skills.
     * @return Skills of the given characteristic.
     */
    public List<Skill> getSkillsByCharacteristic(CharacteristicEnum characteristic) {
        return skillsByCharacteristic.get(characteristic);
    }

    /**
     * Return the skills with the given type.
     *
     * @param type Type of the skills.
     * @return Skills with the given type.
     */
    public List<Skill> getSkillsByType(SkillType type) {
        return skillsByType.get(type);
    }
}
