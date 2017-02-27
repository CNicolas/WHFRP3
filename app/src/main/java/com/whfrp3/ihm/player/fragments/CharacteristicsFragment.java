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

import butterknife.ButterKnife;

public class CharacteristicsFragment extends Fragment {

//    @BindView(R.id.player_name)
//    private EditText playerNameEditText;
//    @BindView(R.id.player_rank)
//    private EditText playerRankEditText;
//    @BindView(R.id.player_career)
//    private EditText playerCareerEditText;
//    @BindView(R.id.player_max_wounds)
//    private EditText playerMaxWoundsEditText;
//    @BindView(R.id.player_experience)
//    private EditText playerExperienceEditText;
//    @BindView(R.id.player_max_experience)
//    private EditText playerMaxExperienceEditText;
//    @BindView(R.id.player_max_corruption)
//    private EditText playerMaxCorruptionEditText;
//
//    @BindView(R.id.strength_characteristic)
//    private EditText playerStrengthCharacteristicEditText;
//    @BindView(R.id.strength_fortune)
//    private EditText playerStrengthFortuneEditText;
//    @BindView(R.id.toughness_characteristic)
//    private EditText playerToughnessCharacteristicEditText;
//    @BindView(R.id.toughness_fortune)
//    private EditText playerToughnessFortuneEditText;
//    @BindView(R.id.agility_characteristic)
//    private EditText playerAgilityCharacteristicEditText;
//    @BindView(R.id.agility_fortune)
//    private EditText playerAgilityFortuneEditText;
//    @BindView(R.id.intelligence_characteristic)
//    private EditText playerIntelligenceCharacteristicEditText;
//    @BindView(R.id.intelligence_fortune)
//    private EditText playerIntelligenceFortuneEditText;
//    @BindView(R.id.willpower_characteristic)
//    private EditText playerWillpowerCharacteristicEditText;
//    @BindView(R.id.willpower_fortune)
//    private EditText playerWillpowerFortuneEditText;
//    @BindView(R.id.fellowship_characteristic)
//    private EditText playerFellowshipCharacteristicEditText;
//    @BindView(R.id.fellowship_fortune)
//    private EditText playerFellowshipFortuneEditText;

    private PlayerService playerService;

    public CharacteristicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_characteristics, container, false);

        getActivity().setTitle(getString(R.string.page_players));

        ButterKnife.bind(this, rootView);

        playerService = new PlayerService();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return rootView;
    }

//    @OnTextChanged(R.id.)
}
