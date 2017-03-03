package com.whfrp3.ihm.player.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.whfrp3.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeaponViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.weapon_name)
    public TextView weaponName;

    @BindView(R.id.weapon_damage)
    public TextView weaponDamage;

    @BindView(R.id.weapon_critical)
    public TextView weaponCritical;

    public WeaponViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(itemView);
    }
}
