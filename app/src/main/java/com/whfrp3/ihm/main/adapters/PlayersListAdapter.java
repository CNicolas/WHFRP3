package com.whfrp3.ihm.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersListAdapter extends RecyclerView.Adapter<PlayersListAdapter.PlayerViewHolder> {
    private PlayerClickListener clickListener;
    private List<Player> dataset;

    public PlayersListAdapter(PlayersListAdapter.PlayerClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<>();
    }

    //region SubClasses
    public interface PlayerClickListener {
        void onPlayerClick(int position);
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public PlayerViewHolder(View itemView, final PlayerClickListener clickListener) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.player_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onPlayerClick(getAdapterPosition());
                    }
                }
            });
        }
    }
    //endregion

    public Player getPlayer(int position) {
        return dataset.get(position);
    }

    public void setPlayers(List<Player> players) {
        dataset = players;

        Player emptyPlayer = new Player();
        emptyPlayer.setName(WHFRP3.getResourceString(R.string.home_create_player));
        dataset.add(0, emptyPlayer);

        notifyDataSetChanged();
    }

    @Override
    public PlayersListAdapter.PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_list_player, parent, false);
        return new PlayerViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(PlayersListAdapter.PlayerViewHolder holder, int position) {
        Player player = dataset.get(position);
        holder.name.setText("Name : " + player.getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
