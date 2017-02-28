package com.whfrp3.ihm.player.fragments.viewholders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.entities.Player;
import com.whfrp3.database.enums.CharacteristicEnum;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

@SuppressWarnings("WeakerAccess")
public class CharacteristicsViewHolder {
    @BindView(R.id.characteristics_fragment_layout)
    public ViewGroup parentLayout;

    @BindView(R.id.player_name)
    public EditText playerNameEditText;
    @BindView(R.id.player_rank)
    public EditText playerRankEditText;
    @BindView(R.id.player_career)
    public EditText playerCareerEditText;
    @BindView(R.id.player_max_wounds)
    public EditText playerMaxWoundsEditText;
    @BindView(R.id.player_experience)
    public EditText playerExperienceEditText;
    @BindView(R.id.player_max_experience)
    public EditText playerMaxExperienceEditText;
    @BindView(R.id.player_max_corruption)
    public EditText playerMaxCorruptionEditText;

    @BindView(R.id.strength_characteristic)
    public EditText playerStrengthCharacteristicEditText;
    @BindView(R.id.strength_fortune)
    public EditText playerStrengthFortuneEditText;
    @BindView(R.id.toughness_characteristic)
    public EditText playerToughnessCharacteristicEditText;
    @BindView(R.id.toughness_fortune)
    public EditText playerToughnessFortuneEditText;
    @BindView(R.id.agility_characteristic)
    public EditText playerAgilityCharacteristicEditText;
    @BindView(R.id.agility_fortune)
    public EditText playerAgilityFortuneEditText;
    @BindView(R.id.intelligence_characteristic)
    public EditText playerIntelligenceCharacteristicEditText;
    @BindView(R.id.intelligence_fortune)
    public EditText playerIntelligenceFortuneEditText;
    @BindView(R.id.willpower_characteristic)
    public EditText playerWillpowerCharacteristicEditText;
    @BindView(R.id.willpower_fortune)
    public EditText playerWillpowerFortuneEditText;
    @BindView(R.id.fellowship_characteristic)
    public EditText playerFellowshipCharacteristicEditText;
    @BindView(R.id.fellowship_fortune)
    public EditText playerFellowshipFortuneEditText;

    @BindView(R.id.player_max_conservative)
    public EditText playerMaxConservativeEditText;
    @BindView(R.id.player_max_reckless)
    public EditText playerMaxRecklessEditText;
    @BindView(R.id.player_race)
    public Spinner playerRaceSpinner;
    @BindView(R.id.player_age)
    public EditText playerAgeEditText;
    @BindView(R.id.player_size)
    public EditText playerSizeEditText;
    @BindView(R.id.player_description)
    public EditText playerDescriptionEditText;

    public CharacteristicsViewHolder(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    public void initFields() {
        Player player = WHFRP3.getPlayer();

        playerNameEditText.setText(player.getName());
        playerRankEditText.setText(String.valueOf(player.getRank()));
        playerCareerEditText.setText(player.getCareer());
        playerMaxWoundsEditText.setText(String.valueOf(player.getMaxWounds()));
        playerExperienceEditText.setText(String.valueOf(player.getExperience()));
        playerMaxExperienceEditText.setText(String.valueOf(player.getMaxExperience()));
        playerMaxCorruptionEditText.setText(String.valueOf(player.getMaxCorruption()));

        playerStrengthCharacteristicEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.STRENGTH).getValue()));
        playerStrengthFortuneEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.STRENGTH).getFortuneValue()));
        playerToughnessCharacteristicEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.TOUGHNESS).getValue()));
        playerToughnessFortuneEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.TOUGHNESS).getFortuneValue()));
        playerAgilityCharacteristicEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.AGILITY).getValue()));
        playerAgilityFortuneEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.AGILITY).getFortuneValue()));

        playerIntelligenceCharacteristicEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.INTELLIGENCE).getValue()));
        playerIntelligenceFortuneEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.INTELLIGENCE).getFortuneValue()));
        playerWillpowerCharacteristicEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.WILLPOWER).getValue()));
        playerWillpowerFortuneEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.WILLPOWER).getFortuneValue()));
        playerFellowshipCharacteristicEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.FELLOWSHIP).getValue()));
        playerFellowshipFortuneEditText.setText(String.valueOf(player.getCharacteristic(CharacteristicEnum.FELLOWSHIP).getFortuneValue()));

        playerMaxConservativeEditText.setText(String.valueOf(player.getMaxConservative()));
        playerMaxRecklessEditText.setText(String.valueOf(player.getMaxReckless()));
//        playerRaceSpinner.setText(player.getRace());
        playerAgeEditText.setText(String.valueOf(player.getAge()));
        playerSizeEditText.setText(String.valueOf(player.getSize()));
        playerDescriptionEditText.setText(player.getDescription());
    }

    public void makeEditable(boolean inEdition) {
        enableViews(parentLayout, inEdition);
    }

    private void enableViews(ViewGroup layout, boolean inEdition) {
        layout.setEnabled(inEdition);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                enableViews((ViewGroup) child, inEdition);
            } else {
                child.setEnabled(inEdition);
            }
        }
    }

    //region OnTextChanged
    @OnTextChanged(R.id.player_name)
    public void setPlayerName(CharSequence text) {
        WHFRP3.getPlayer().setName(text.toString());
    }

    @OnTextChanged(R.id.player_rank)
    public void setPlayerRank(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().setRank(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.player_career)
    public void setPlayerCareer(CharSequence text) {
        WHFRP3.getPlayer().setCareer(text.toString());
    }

    @OnTextChanged(R.id.player_max_wounds)
    public void setPlayerMaxWounds(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().setMaxWounds(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.player_experience)
    public void setPlayerExperience(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().setExperience(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.player_max_experience)
    public void setPlayerMaxExperience(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().setMaxExperience(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.player_max_corruption)
    public void setPlayerMaxCorruption(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().setMaxCorruption(value);
        } catch (NumberFormatException ignored) {
        }
    }

    //region Characteristics
    @OnTextChanged(R.id.strength_characteristic)
    public void setPlayerStrength(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.STRENGTH).setValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.strength_fortune)
    public void setPlayerStrengthFortune(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.STRENGTH).setFortuneValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.toughness_characteristic)
    public void setPlayerToughness(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.TOUGHNESS).setValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.toughness_fortune)
    public void setPlayerToughnessFortune(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.TOUGHNESS).setFortuneValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.agility_characteristic)
    public void setPlayerAgility(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.AGILITY).setValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.agility_fortune)
    public void setPlayerAgilityFortune(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.AGILITY).setFortuneValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.intelligence_characteristic)
    public void setPlayerIntelligence(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.INTELLIGENCE).setValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.intelligence_fortune)
    public void setPlayerIntelligenceFortune(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.INTELLIGENCE).setFortuneValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.willpower_characteristic)
    public void setPlayerWillpower(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.WILLPOWER).setValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.willpower_fortune)
    public void setPlayerWillpowerFortune(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.WILLPOWER).setFortuneValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.fellowship_characteristic)
    public void setPlayerFellowship(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.FELLOWSHIP).setValue(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.fellowship_fortune)
    public void setPlayerFellowshipFortune(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().getCharacteristic(CharacteristicEnum.FELLOWSHIP).setFortuneValue(value);
        } catch (NumberFormatException ignored) {
        }
    }
    //endregion

    @OnTextChanged(R.id.player_max_conservative)
    public void setPlayerMaxConservative(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().setMaxConservative(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.player_max_reckless)
    public void setPlayerMaxReckless(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().setMaxReckless(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.player_age)
    public void setPlayerAge(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().setAge(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.player_size)
    public void setPlayerSize(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().setSize(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnTextChanged(R.id.player_description)
    public void setPlayerDescription(CharSequence text) {
        WHFRP3.getPlayer().setDescription(text.toString());
    }
    //endregion

    //    @OnTextChanged(R.id.player_race)
//    public void setPlayerRace(CharSequence text) {
//        WHFRP3.getPlayer().setRace(Race.valueOf(text.toString());
//    }
//    @OnItemSelected(R.id.player_race)
//    public void spinnerItemSelected(Spinner spinner, int position) {
//        WHFRP3.getPlayer().setRace(Race.valueOf(text.toString());
//    }

}
