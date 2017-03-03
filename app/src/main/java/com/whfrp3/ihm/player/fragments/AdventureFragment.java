package com.whfrp3.ihm.player.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.entities.Player;
import com.whfrp3.database.services.PlayerService;
import com.whfrp3.ihm.player.constants.IPlayerNotificationConstants;
import com.whfrp3.ihm.player.fragments.viewholders.AdventureFragmentViewHolder;

public class AdventureFragment extends Fragment {
    private PlayerService playerService;
    private AdventureFragmentViewHolder viewHolder;

    public AdventureFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_adventure, container, false);

        viewHolder = new AdventureFragmentViewHolder(rootView);

        playerService = new PlayerService();

        setupBroadcastReceiver();

        getActivity().setTitle(getString(R.string.page_adventure));
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewHolder.initFields(getLayoutInflater(savedInstanceState));
    }

    private void setupBroadcastReceiver() {
        BroadcastReceiver messageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Player player = WHFRP3.getPlayer();

                playerService.updatePlayer(player);

                viewHolder.updateMoney(player);
                viewHolder.updateStance(player);
            }
        };
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(messageReceiver, new IntentFilter(IPlayerNotificationConstants.MONEY_UPDATE));
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(messageReceiver, new IntentFilter(IPlayerNotificationConstants.STANCE_UPDATE));
    }
}
