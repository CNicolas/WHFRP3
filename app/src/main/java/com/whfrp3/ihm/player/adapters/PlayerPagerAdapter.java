package com.whfrp3.ihm.player.adapters;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.ihm.player.constants.IPlayerActivityConstants;
import com.whfrp3.ihm.player.fragments.AdventureFragment;
import com.whfrp3.ihm.player.fragments.CharacteristicsFragment;
import com.whfrp3.ihm.player.fragments.SkillsFragment;

/**
 * The PlayerPagerAdapter creates and manages the Fragments from the PlayerActivity.
 */
public class PlayerPagerAdapter extends FragmentPagerAdapter {

    private int mCurrentPosition;

    private CharacteristicsFragment characteristicsFragment;
    private AdventureFragment adventureFragment;
    private SkillsFragment skillsFragment;
//    private InventoryFragment mInventoryFragment;
//    private PlayerActionsFragment mPlayerActionsFragment;
//    private PlayerTalentsFragment mPlayerTalentsFragment;

    public PlayerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        mCurrentPosition = position;

        switch (position) {
            case IPlayerActivityConstants.CHARACTERISTICS_FRAGMENT_POSITION:
                if (characteristicsFragment == null) {
                    characteristicsFragment = new CharacteristicsFragment();
                }
                return characteristicsFragment;

            case IPlayerActivityConstants.ADVENTURE_FRAGMENT_POSITION:
                if (adventureFragment == null) {
                    adventureFragment = new AdventureFragment();
                }
                return adventureFragment;
            case IPlayerActivityConstants.SKILLS_FRAGMENT_POSITION:
                if (skillsFragment == null) {
                    skillsFragment = new SkillsFragment();
                }
                return skillsFragment;
//            case IPlayerActivityConstants.INVENTORY_FRAGMENT_POSITION:
//                if (mInventoryFragment == null) {
//                    mInventoryFragment = new InventoryFragment();
//                }
//                return mInventoryFragment;
//            case IPlayerActivityConstants.PLAYER_ACTIONS_FRAGMENT_POSITION:
//                if (mPlayerActionsFragment == null) {
//                    mPlayerActionsFragment = new PlayerActionsFragment();
//                }
//                return mPlayerActionsFragment;
//            case IPlayerActivityConstants.PLAYER_TALENTS_FRAGMENT_POSITION:
//                if (mPlayerTalentsFragment == null) {
//                    mPlayerTalentsFragment = new PlayerTalentsFragment();
//                }
//                return mPlayerTalentsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case IPlayerActivityConstants.CHARACTERISTICS_FRAGMENT_POSITION:
                Drawable image = WHFRP3.getResourceDrawable(R.drawable.ic_person_black);
                return setTabIcon(image);
            case IPlayerActivityConstants.ADVENTURE_FRAGMENT_POSITION:
                image = WHFRP3.getResourceDrawable(R.drawable.ic_explore_black);
                return setTabIcon(image);
            case IPlayerActivityConstants.SKILLS_FRAGMENT_POSITION:
                image = WHFRP3.getResourceDrawable(R.drawable.ic_skills_black);
                return setTabIcon(image);
//            case IPlayerActivityConstants.INVENTORY_FRAGMENT_POSITION:
//                image = ContextCompat.getDrawable(context, R.drawable.ic_bag_black);
//                return setTabIcon(image);
//            case IPlayerActivityConstants.PLAYER_ACTIONS_FRAGMENT_POSITION:
//                image = ContextCompat.getDrawable(context, R.drawable.ic_action_black);
//                return setTabIcon(image);
//            case IPlayerActivityConstants.PLAYER_TALENTS_FRAGMENT_POSITION:
//                image = ContextCompat.getDrawable(context, R.drawable.ic_talents_black);
//                return setTabIcon(image);
        }
        return null;
    }

    /**
     * Sets the icon in the tab.
     *
     * @param image the image.
     * @return the page title to show in the tablayout.
     */
    private CharSequence setTabIcon(Drawable image) {
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());

        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);

        SpannableString sb = new SpannableString(" ");
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
