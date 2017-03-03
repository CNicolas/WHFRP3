package com.whfrp3.ihm.player.fragments.viewholders;

import android.view.LayoutInflater;

abstract class BaseFragmentViewHolder {
    abstract void initFields(LayoutInflater layoutInflater);

    String getTextValueFromInt(int value) {
        return value != 0 ? String.valueOf(value) : "";
    }
}
