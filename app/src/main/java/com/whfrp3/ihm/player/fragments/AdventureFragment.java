package com.whfrp3.ihm.player.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.whfrp3.R;
import com.whfrp3.database.services.PlayerService;
import com.whfrp3.ihm.player.fragments.viewholders.AdventureFragmentViewHolder;

public class AdventureFragment extends Fragment {
    private PlayerService playerService;
    private AdventureFragmentViewHolder viewHolder;

    public AdventureFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_adventure, container, false);

        viewHolder = new AdventureFragmentViewHolder(rootView);

        playerService = new PlayerService();

        getActivity().setTitle(getString(R.string.page_adventure));
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewHolder.initFields(getLayoutInflater(savedInstanceState));
    }
}
