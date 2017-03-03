package com.whfrp3.ihm.player.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.whfrp3.R;

public class SkillViewHolder extends RecyclerView.ViewHolder {

    public TextView name;

    public CheckBox level1;
    public CheckBox level2;
    public CheckBox level3;

    public TextView specialization;

    public SkillViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.skill_name);

        level1 = (CheckBox) itemView.findViewById(R.id.skill_level1);
        level2 = (CheckBox) itemView.findViewById(R.id.skill_level2);
        level3 = (CheckBox) itemView.findViewById(R.id.skill_level3);

        specialization = (TextView) itemView.findViewById(R.id.skill_specialization);
    }
}
