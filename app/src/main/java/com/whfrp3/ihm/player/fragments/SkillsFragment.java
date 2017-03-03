package com.whfrp3.ihm.player.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.services.PlayerService;
import com.whfrp3.ihm.player.adapters.SkillsListAdapter;

public class SkillsFragment extends Fragment {
    private SkillsListAdapter skillsAdapter;

    private PlayerService playerService;

    public SkillsFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_skills, container, false);

        skillsAdapter = new SkillsListAdapter();

        RecyclerView skillsList = (RecyclerView) rootView.findViewById(R.id.skills_list);
        skillsList.setHasFixedSize(true);
        skillsList.setLayoutManager(new LinearLayoutManager(getContext()));
        skillsList.setAdapter(skillsAdapter);

        playerService = new PlayerService();

        getActivity().setTitle(getString(R.string.page_skills));
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        skillsAdapter.setSkills(WHFRP3.getPlayer().getSkills());
    }
}
