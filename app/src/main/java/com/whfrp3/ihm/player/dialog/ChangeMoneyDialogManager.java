package com.whfrp3.ihm.player.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.entities.model.Money;
import com.whfrp3.database.enums.MoneyType;
import com.whfrp3.ihm.player.enums.MoneyOperation;

public class ChangeMoneyDialogManager {
    private LayoutInflater inflater;

    public ChangeMoneyDialogManager(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public AlertDialog createDialog(final MoneyOperation operation) {
        final View dialogView = inflater.inflate(R.layout.dialog_money, null);

        int title = (operation == MoneyOperation.ADD_MONEY) ? R.string.btn_add : R.string.btn_remove;

        AlertDialog.Builder builder = new AlertDialog.Builder(WHFRP3.getActivity());

        builder.setView(dialogView);
        builder.setTitle(title);
        builder.setPositiveButton(title, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Money money = WHFRP3.getPlayer().getMoney();

                if (operation == MoneyOperation.ADD_MONEY) {
                    money.addMoney(getGoldAmount(dialogView), MoneyType.GOLD);
                    money.addMoney(getSilverAmount(dialogView), MoneyType.SILVER);
                    money.addMoney(getBrassAmount(dialogView), MoneyType.BRASS);
                } else if (operation == MoneyOperation.REMOVE_MONEY) {
                    try {
                        money.removeMoney(getGoldAmount(dialogView), MoneyType.GOLD);
                        money.removeMoney(getSilverAmount(dialogView), MoneyType.SILVER);
                        money.removeMoney(getBrassAmount(dialogView), MoneyType.BRASS);
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(WHFRP3.getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return builder.create();
    }

    private int getGoldAmount(View dialogView) {
        int value = WHFRP3.getPlayer().getMoney().getGold();

        EditText goldAmountEditText = (EditText) dialogView.findViewById(R.id.dialog_money_gold);
        try {
            value = Integer.parseInt(goldAmountEditText.getText().toString());
        } catch (NumberFormatException ignored) {
            Log.e("GOLD", "" + value + ", editText = " + goldAmountEditText);
        }

        return value;
    }

    private int getSilverAmount(View dialogView) {
        int value = WHFRP3.getPlayer().getMoney().getSilver();

        EditText silverAmountEditText = (EditText) dialogView.findViewById(R.id.dialog_money_silver);
        try {
            value = Integer.parseInt(silverAmountEditText.getText().toString());
        } catch (NumberFormatException ignored) {
            Log.e("SILVER", "" + value + ", editText = " + silverAmountEditText);
        }

        return value;
    }

    private int getBrassAmount(View dialogView) {
        int value = WHFRP3.getPlayer().getMoney().getBrass();

        EditText brassAmountEditText = (EditText) dialogView.findViewById(R.id.dialog_money_brass);
        try {
            value = Integer.parseInt(brassAmountEditText.getText().toString());
        } catch (NumberFormatException ignored) {
            Log.e("BRASS", "" + value + ", editText = " + brassAmountEditText);
        }

        return value;
    }
}
