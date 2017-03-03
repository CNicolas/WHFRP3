package com.whfrp3.ihm.player.fragments.viewholders;

import android.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.entities.Player;
import com.whfrp3.ihm.components.BindableDiscreteSeekBar;
import com.whfrp3.ihm.player.adapters.WeaponsListAdapter;
import com.whfrp3.ihm.player.dialog.ChangeMoneyDialogManager;
import com.whfrp3.ihm.player.enums.MoneyOperation;
import com.whfrp3.ihm.player.listeners.StanceChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

@SuppressWarnings("WeakerAccess")
public class AdventureFragmentViewHolder extends BaseFragmentViewHolder {
    private LayoutInflater layoutInflater;

    //region Views

    //region Wounds
    @BindView(R.id.player_wounds)
    public EditText playerWoundsEditText;
    @BindView(R.id.player_max_wounds)
    public TextView playerMaxWoundsTextView;
    //endregion

    //region Stress and Tiredness
    @BindView(R.id.player_stress)
    public TextView playerStressTextView;
    @BindView(R.id.player_tiredness)
    public TextView playerTirednessTextView;
    //endregion

    //region Stance
    @BindView(R.id.player_current_stance)
    public TextView playerCurrentStanceTextView;
    @BindView(R.id.player_stance_bar)
    public BindableDiscreteSeekBar playerStanceBar;
    //endregion

    //region Defense and Soak
    @BindView(R.id.player_full_defense)
    public TextView playerFullDefenseTextView;
    @BindView(R.id.player_full_soak)
    public TextView playerFullSoakTextView;
    //endregion

    @BindView(R.id.equipped_weapons)
    public RecyclerView playerEquippedWeaponsListView;

    //region Encumbrance
    @BindView(R.id.player_encumbrance_bar)
    public BindableDiscreteSeekBar playerEncumbranceBar;
    @BindView(R.id.encumbrance_text)
    public TextView playerEncumbranceTextView;
    //endregion

    //region Money
    @BindView(R.id.money_gold)
    public TextView moneyGoldTextView;
    @BindView(R.id.money_silver)
    public TextView moneySilverTextView;
    @BindView(R.id.money_brass)
    public TextView moneyBrassTextView;
    //endregion

    //endregion

    public AdventureFragmentViewHolder(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    public void initFields(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;

        Player player = WHFRP3.getPlayer();

        playerWoundsEditText.setText(getTextValueFromInt(player.getWounds()));
        playerMaxWoundsTextView.setText(getTextValueFromInt(player.getMaxWounds()));

        playerStressTextView.setText(getTextValueFromInt(player.getStress()));
        playerTirednessTextView.setText(getTextValueFromInt(player.getTiredness()));

        playerStanceBar.setOnProgressChangeListener(new StanceChangeListener(playerCurrentStanceTextView));
        updateStance(player);

        playerFullDefenseTextView.setText(getTextValueFromInt(player.getInventory().getFullDefenseAmount()));
        playerFullSoakTextView.setText(getTextValueFromInt(player.getInventory().getFullSoakAmount()));

        WeaponsListAdapter adapter = new WeaponsListAdapter();
        playerEquippedWeaponsListView.setHasFixedSize(true);
        playerEquippedWeaponsListView.setLayoutManager(new LinearLayoutManager(WHFRP3.getActivity()));
        playerEquippedWeaponsListView.setAdapter(adapter);
        adapter.setWeapons(player.getInventory().getEquippedWeapons());

        playerEncumbranceBar.setMax(player.getEncumbranceMax());
        playerEncumbranceBar.setDsb_progressColor(player.getEncumbranceColor());
        playerEncumbranceBar.setDsb_value(player.getCurrentEncumbrance());
        playerEncumbranceTextView.setText(formatEncumbranceText(player));

        updateMoney(player);
    }

    public void updateStressAndTiredness(Player player) {
        playerStressTextView.setText(getTextValueFromInt(player.getStress()));
        playerTirednessTextView.setText(getTextValueFromInt(player.getTiredness()));
    }

    public void updateStance(Player player) {
        playerStanceBar.setMin(-1 * player.getMaxConservative());
        playerStanceBar.setMax(player.getMaxReckless());
        playerStanceBar.setProgress(0);
    }

    public void updateMoney(Player player) {
        moneyGoldTextView.setText(getTextValueFromInt(player.getMoney().getGold()));
        moneySilverTextView.setText(getTextValueFromInt(player.getMoney().getSilver()));
        moneyBrassTextView.setText(getTextValueFromInt(player.getMoney().getBrass()));
    }

    private String formatEncumbranceText(Player player) {
        return String.format(WHFRP3.getResourceString(R.string.encumbrance_format),
                player.getCurrentEncumbrance(),
                player.getEncumbranceOverload(),
                player.getEncumbranceMax());
    }

    public void changeStress(Player player, int change) {
        int newStressValue = player.getStress() + change;

        if (newStressValue >= 0) {
            if (newStressValue <= player.getMaxStressBeforeComa()) {
                player.setStress(newStressValue);
            } else {
                Toast.makeText(WHFRP3.getActivity(), "You're in coma !", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(WHFRP3.getActivity(), "You can't !", Toast.LENGTH_SHORT).show();
        }

        playerStressTextView.setText(getTextValueFromInt(player.getStress()));
    }

    public void changeTiredness(Player player, int change) {
        int newTirednessValue = player.getTiredness() + change;

        if (newTirednessValue >= 0) {
            if (newTirednessValue <= player.getMaxTirednessBeforeComa()) {
                player.setTiredness(newTirednessValue);
            } else {
                Toast.makeText(WHFRP3.getActivity(), "You're in coma !", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(WHFRP3.getActivity(), "You can't !", Toast.LENGTH_SHORT).show();
        }

        playerTirednessTextView.setText(getTextValueFromInt(player.getTiredness()));
    }

    public void changeMoney(MoneyOperation operation) {
        ChangeMoneyDialogManager dialogManager = new ChangeMoneyDialogManager(layoutInflater);
        AlertDialog dialog = dialogManager.createDialog(operation);
        dialog.show();
    }

    //region Event handling
    @OnTextChanged(R.id.player_wounds)
    public void setPlayerWounds(CharSequence text) {
        try {
            int value = Integer.parseInt(text.toString());
            WHFRP3.getPlayer().setWounds(value);
        } catch (NumberFormatException ignored) {
        }
    }

    @OnClick(R.id.btn_minus_stress)
    public void decreaseStress() {
        changeStress(WHFRP3.getPlayer(), -1);
    }

    @OnClick(R.id.btn_plus_stress)
    public void increaseStress() {
        changeStress(WHFRP3.getPlayer(), 1);
    }

    @OnClick(R.id.btn_minus_tiredness)
    public void decreaseTiredness() {
        changeTiredness(WHFRP3.getPlayer(), -1);
    }

    @OnClick(R.id.btn_plus_tiredness)
    public void increaseTiredness() {
        changeTiredness(WHFRP3.getPlayer(), 1);
    }

    @OnClick(R.id.btn_add_money)
    public void addMoney() {
        changeMoney(MoneyOperation.ADD_MONEY);
    }

    @OnClick(R.id.btn_remove_money)
    public void removeMoney() {
        changeMoney(MoneyOperation.REMOVE_MONEY);
    }
    //endregion
}
