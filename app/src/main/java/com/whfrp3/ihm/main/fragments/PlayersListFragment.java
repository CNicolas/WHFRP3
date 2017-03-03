package com.whfrp3.ihm.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.entities.Player;
import com.whfrp3.database.entities.model.Money;
import com.whfrp3.database.entities.model.inventory.Inventory;
import com.whfrp3.database.services.PlayerService;
import com.whfrp3.ihm.main.adapters.PlayersListAdapter;
import com.whfrp3.ihm.main.constants.IMainConstants;
import com.whfrp3.ihm.player.PlayerActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayersListFragment extends Fragment {

    private PlayerService playerService;

    private PlayersListAdapter playersAdapter;

    @BindView(R.id.new_player_name)
    public EditText newPlayerNameEditText;

    @BindView(R.id.list_players)
    public RecyclerView playersList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_players_list, container, false);

        setupViews(rootView);

        playerService = new PlayerService();

        getActivity().setTitle(getString(R.string.page_players));
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updatePlayers();
    }

    private void setupViews(View rootView) {
        ButterKnife.bind(this, rootView);

        PlayersListAdapter.PlayerClickListener playerClickListener = new PlayersListAdapter.PlayerClickListener() {
            @Override
            public void onPlayerClick(int position) {
                final Player player = playersAdapter.getPlayer(position);
                WHFRP3.setPlayer(player);

                goToPlayerActivity();
            }
        };

        playersAdapter = new PlayersListAdapter(playerClickListener);

        playersList.setHasFixedSize(true);
        playersList.setLayoutManager(new LinearLayoutManager(getContext()));
        playersList.setAdapter(playersAdapter);
    }

    private void updatePlayers() {
        List<Player> players = playerService.queryAllPlayersOrderedByName().list();
        playersAdapter.setPlayers(players);
    }

    @OnClick(R.id.new_player_button)
    public void addPlayer() {
        String playerName = newPlayerNameEditText.getText().toString();

        if (!playerName.isEmpty()) {

            for (int i = 0; i < playersAdapter.getItemCount(); i++) {
                if (Objects.equals(playersAdapter.getPlayer(i).getName(), playerName)) {
                    Toast.makeText(getContext(), "Already taken", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            newPlayerNameEditText.setText("");

            Player player = new Player();
            player.setName(playerName);
            player.setInventory(new Inventory());
            player.setMoney(new Money(0, 0, 0));
            player.initCharacteristics();

            long id = playerService.insertPlayer(player);
            WHFRP3.setPlayer(playerService.getPlayerById(id));

            Log.d("INSERT", WHFRP3.getPlayer().toString());

            updatePlayers();
            goToPlayerActivity();
        } else {
            Toast.makeText(getContext(), R.string.empty_hand_name, Toast.LENGTH_SHORT).show();
        }
    }

    private void goToPlayerActivity() {
        Intent intent = new Intent(getActivity(), PlayerActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
        stackBuilder.addParentStack(PlayerActivity.class);
        stackBuilder.addNextIntent(intent);

        startActivityForResult(intent, IMainConstants.PLAYER_REQUEST);
    }
}
