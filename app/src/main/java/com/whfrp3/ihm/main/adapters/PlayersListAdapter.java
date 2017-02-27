package com.whfrp3.ihm.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.database.entities.Player;
import com.whfrp3.ihm.main.adapters.viewholders.PlayerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PlayersListAdapter extends RecyclerView.Adapter<PlayerViewHolder> {
    private PlayerClickListener clickListener;
    private List<Player> dataset;

    public PlayersListAdapter(PlayersListAdapter.PlayerClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<>();
    }

    public Player getPlayer(int position) {
        return dataset.get(position);
    }

    public void setPlayers(List<Player> players) {
        dataset = players;

        notifyDataSetChanged();
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_list_player, parent, false);
        return new PlayerViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Player player = dataset.get(position);
        holder.name.setText(player.getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public interface PlayerClickListener {
        void onPlayerClick(int position);
    }
}
