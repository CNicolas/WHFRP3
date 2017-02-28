package com.whfrp3.ihm.player.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.enums.IEnumSpinner;

import java.util.Arrays;
import java.util.List;

public class EnumSpinnerAdapter extends ArrayAdapter<IEnumSpinner> {
    private final LayoutInflater layoutInflater;
    private List<IEnumSpinner> dataset;

    public EnumSpinnerAdapter(LayoutInflater inflater, IEnumSpinner[] values) {
        super(inflater.getContext(), R.layout.element_spinner, values);

        layoutInflater = inflater;
        dataset = Arrays.asList(values);
    }

    public EnumSpinnerAdapter(LayoutInflater inflater, List<IEnumSpinner> values) {
        super(inflater.getContext(), R.layout.element_spinner, values);

        layoutInflater = inflater;
        dataset = values;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public IEnumSpinner getItem(int position) {
        return dataset.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        String text = WHFRP3.getResourceString(dataset.get(position).getLabelId());

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.element_spinner, null);
        }

        TextView elementView = (TextView) convertView.findViewById(R.id.enum_spinner_element);
        elementView.setText(text);

        return convertView;
    }
}
