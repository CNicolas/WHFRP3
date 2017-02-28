package com.whfrp3.ihm.main.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.ihm.main.adapters.PlayersListAdapter;

public class PlayerViewHolder extends RecyclerView.ViewHolder {

    public TextView name;

    public PlayerViewHolder(View itemView, final PlayersListAdapter.PlayerClickListener clickListener) {
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
