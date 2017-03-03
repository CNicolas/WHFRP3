package com.whfrp3.ihm.player.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.database.entities.model.inventory.Weapon;
import com.whfrp3.ihm.player.adapters.viewholders.WeaponViewHolder;

import java.util.ArrayList;
import java.util.List;

public class WeaponsListAdapter extends RecyclerView.Adapter<WeaponViewHolder> {
    private List<Weapon> dataset;

    public WeaponsListAdapter() {
        this.dataset = new ArrayList<>();
    }

    public Weapon getWeapon(int position) {
        return dataset.get(position);
    }

    public void setWeapons(List<Weapon> weapons) {
        dataset = weapons;

        notifyDataSetChanged();
    }

    @Override
    public WeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_list_weapon, parent, false);

        return new WeaponViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeaponViewHolder holder, int position) {
        Weapon weapon = dataset.get(position);

        holder.weaponName.setText(weapon.getName());
        holder.weaponDamage.setText(getTextValueFromInt(weapon.getDamage()));
        holder.weaponCritical.setText(getTextValueFromInt(weapon.getCriticalLevel()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    private String getTextValueFromInt(int value) {
        return value != 0 ? String.valueOf(value) : "";
    }
}
