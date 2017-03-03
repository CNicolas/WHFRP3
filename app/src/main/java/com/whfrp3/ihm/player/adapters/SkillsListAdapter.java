package com.whfrp3.ihm.player.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.entities.model.skills.Skill;
import com.whfrp3.ihm.player.adapters.viewholders.SkillViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SkillsListAdapter extends RecyclerView.Adapter<SkillViewHolder> {
    private List<Skill> dataset;

    public SkillsListAdapter() {
        this.dataset = new ArrayList<>();
    }

    public Skill getSkill(int position) {
        return dataset.get(position);
    }

    public void setSkills(List<Skill> skills) {
        dataset = skills;

        notifyDataSetChanged();
    }

    @Override
    public SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_list_player_skill, parent, false);

        return new SkillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SkillViewHolder holder, int position) {
        Skill skill = dataset.get(position);

        holder.name.setText(skill.getName());

        switch (skill.getLevel()) {
            case 1:
                holder.level1.setChecked(true);
                holder.level2.setChecked(false);
                holder.level3.setChecked(false);
                break;
            case 2:
                holder.level1.setChecked(true);
                holder.level2.setChecked(true);
                holder.level3.setChecked(false);
                break;
            case 3:
                holder.level1.setChecked(true);
                holder.level2.setChecked(true);
                holder.level3.setChecked(true);
                break;
            default:
                break;
        }
        if (skill.isSpecialized()) {
            holder.specialization.setTextColor(WHFRP3.getResourceColor(R.color.dark_red));
        } else {
            holder.specialization.setTextColor(WHFRP3.getResourceColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
