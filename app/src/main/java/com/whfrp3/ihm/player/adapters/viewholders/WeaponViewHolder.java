package com.whfrp3.ihm.player.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.whfrp3.R;

public class WeaponViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView damage;
    public TextView critical;

    public WeaponViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.weapon_name);
        damage = (TextView) itemView.findViewById(R.id.weapon_damage);
        critical = (TextView) itemView.findViewById(R.id.weapon_critical);
    }
}
