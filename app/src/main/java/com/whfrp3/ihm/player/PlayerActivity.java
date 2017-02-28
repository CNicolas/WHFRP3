package com.whfrp3.ihm.player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.database.services.PlayerService;
import com.whfrp3.ihm.player.adapters.PlayerPagerAdapter;
import com.whfrp3.ihm.player.constants.IPlayerActivityConstants;

public class PlayerActivity extends AppCompatActivity implements IPlayerActivityConstants {
    private PlayerService playerService;
    private PlayerPagerAdapter playerPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playerPagerAdapter = new PlayerPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(playerPagerAdapter);

        playerService = new PlayerService();
    }

    @Override
    protected void onPause() {
        playerService.updatePlayer(WHFRP3.getPlayer());
        super.onPause();
    }

    @Override
    protected void onStop() {
        playerService.updatePlayer(WHFRP3.getPlayer());
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        playerService.updatePlayer(WHFRP3.getPlayer());
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_CANCELED) {
            if (requestCode == LAUNCH_REQUEST) {
                playerPagerAdapter.getItem(intent.getIntExtra(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, CHARACTERISTICS_FRAGMENT_POSITION));
            } /*else if (requestCode == InventoryFragment.ADD_ITEM_REQUEST || requestCode == EDIT_ITEM_REQUEST) {
                playerPagerAdapter.getItem(intent.getIntExtra(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, INVENTORY_FRAGMENT_POSITION));
            }*/
        }
    }
}
