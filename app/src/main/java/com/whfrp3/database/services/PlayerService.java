package com.whfrp3.database.services;

import com.whfrp3.WHFRP3;
import com.whfrp3.database.entities.Player;
import com.whfrp3.database.entities.PlayerDao;
import com.whfrp3.database.entities.model.Money;
import com.whfrp3.database.entities.model.inventory.Inventory;

import org.greenrobot.greendao.query.QueryBuilder;

import rx.Observable;

public class PlayerService {

    public QueryBuilder<Player> queryAllPlayersOrderedByName() {
        return WHFRP3.getDaoSession().getPlayerDao().queryBuilder().orderAsc(PlayerDao.Properties.Name);
    }

    public long insertPlayer(Player player) {
        return WHFRP3.getDaoSession().getPlayerDao().insert(player);
    }

    public Observable<Player> insertPlayerRx(Player player) {
        return WHFRP3.getDaoSession().getPlayerDao().rx().insert(player);
    }

    public Player getPlayerById(long id) {
        return WHFRP3.getDaoSession().getPlayerDao().queryBuilder().where(PlayerDao.Properties.Id.eq(id)).unique();
    }

    public void updatePlayer(Player player) {
        WHFRP3.getDaoSession().getPlayerDao().update(player);
    }

    public Player initEmptyPlayer() {
        Player player = new Player();
        player.setInventory(new Inventory());
        player.setMoney(new Money(0, 0, 0));
        player.initCharacteristics();
        player.initSkills();

        return player;
    }
}
