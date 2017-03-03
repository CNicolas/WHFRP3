package com.whfrp3;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.whfrp3.database.entities.DaoMaster;
import com.whfrp3.database.entities.DaoSession;
import com.whfrp3.database.entities.Player;

import org.greenrobot.greendao.database.Database;

public class WHFRP3 extends Application {
    private static Context context;
    private static Activity activity;

    private static DaoSession daoSession;
    private static Player player;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "whfrp3-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        WHFRP3.player = player;
        Log.d("Player Context SET", WHFRP3.player.toString());
    }

/*
    public static Context getContext() {
        return context;
    }
*/

    public static Activity getActivity() {
        return activity;
    }

    public static void setActivity(Activity activity) {
        WHFRP3.activity = activity;
    }

    //region Resources
    public static String getResourceString(int resId) {
        return context.getString(resId);
    }

    public static Drawable getResourceDrawable(int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

    public static int getResourceColor(int colorId) {
        return ContextCompat.getColor(context, colorId);
    }
    //endregion
}
