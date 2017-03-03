package com.whfrp3.ihm.player.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.whfrp3.R;
import com.whfrp3.database.services.PlayerService;
import com.whfrp3.ihm.player.fragments.viewholders.CharacteristicsFragmentViewHolder;

public class CharacteristicsFragment extends Fragment {
    private CharacteristicsFragmentViewHolder viewHolder;
    private Menu menu;
    private PlayerService playerService;

    public CharacteristicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_characteristics, container, false);

        setupViews(rootView);

        playerService = new PlayerService();

        getActivity().setTitle(getString(R.string.page_characteristics));
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewHolder.initFields(getLayoutInflater(savedInstanceState));
        viewHolder.makeEditable(false);
    }

    private void setupViews(View rootView) {
        viewHolder = new CharacteristicsFragmentViewHolder(rootView);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;
        inflater.inflate(R.menu.characteristics, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_in_edition_false) {
            viewHolder.makeEditable(true);

            menu.findItem(R.id.action_in_edition_false).setVisible(false);
            menu.findItem(R.id.action_in_edition_true).setVisible(true);
        } else if (itemId == R.id.action_in_edition_true) {
            viewHolder.makeEditable(false);

            menu.findItem(R.id.action_in_edition_false).setVisible(true);
            menu.findItem(R.id.action_in_edition_true).setVisible(false);
        }

        return super.onOptionsItemSelected(item);
    }

}
