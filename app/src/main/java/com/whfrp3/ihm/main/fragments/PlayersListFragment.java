package com.whfrp3.ihm.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.entities.Player;
import com.whfrp3.database.services.PlayerService;
import com.whfrp3.ihm.main.adapters.PlayersListAdapter;

import java.util.List;

public class PlayersListFragment extends Fragment {

    private PlayerService playerService;

    private PlayersListAdapter playersAdapter;
    private EditText newPlayerNameEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_players_list, container, false);

        getActivity().setTitle(getString(R.string.page_players));

        playersAdapter = new PlayersListAdapter(playerClickListener);

        newPlayerNameEditText = (EditText) rootView.findViewById(R.id.new_player_name);

        Button newPlayerButton = (Button) rootView.findViewById(R.id.new_player_button);
        newPlayerButton.setOnClickListener(new NewPlayerButtonClickListener());

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list_players);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(playersAdapter);

        playerService = new PlayerService();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updatePlayers();
    }

    private void updatePlayers() {
        List<Player> players = playerService.queryAllPlayersOrderedByName().list();
        playersAdapter.setPlayers(players);
    }

    PlayersListAdapter.PlayerClickListener playerClickListener = new PlayersListAdapter.PlayerClickListener() {
        @Override
        public void onPlayerClick(int position) {
            final Player player = playersAdapter.getPlayer(position);
            Toast.makeText(getContext(), player.toString(), Toast.LENGTH_LONG).show();
        }
    };

    private class NewPlayerButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String playerName = newPlayerNameEditText.getText().toString();

            for (int i = 0; i < playersAdapter.getItemCount(); i++) {
                if (playersAdapter.getPlayer(i).getName() == playerName) {
                    Toast.makeText(getContext(), "Already taken", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            newPlayerNameEditText.setText("");

            Player player = new Player();
            player.setName(playerName);

            long id = playerService.insertPlayer(player);
            WHFRP3.setPlayer(playerService.getPlayerById(id));

            Log.d("INSERT", WHFRP3.getPlayer().toString());
            Toast.makeText(getContext(), WHFRP3.getPlayer().toString(), Toast.LENGTH_LONG).show();

            updatePlayers();
        }
    }
}
