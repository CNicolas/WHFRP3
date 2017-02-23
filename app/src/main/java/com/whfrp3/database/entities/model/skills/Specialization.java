package com.whfrp3.database.entities.model.skills;

/**
 * The specialization model.
 */
public class Specialization {
    private Long id;
    /**
     * Specialization name.
     */
    private String name;

    /**
     * Associated skill.
     */
    private Skill skill;


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

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Specialization{" + "name='" + name + '\'' + ", skill=" + skill + '}';
    }
}
