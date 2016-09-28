package com.nuclearthinking.myheroagency.controller.button.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.controller.observer.ObserverCon;
import com.nuclearthinking.myheroagency.controller.observer.ObserverMen;
import com.nuclearthinking.myheroagency.model.entity.systems.PlayerSystem;
import lombok.NonNull;
import lombok.val;

/**
 * Created by Izonami on 10.08.2016.
 */
public class RemoveStatsListener extends AbstractButtonListener {

    private final PlayerSystem player;

    public RemoveStatsListener(@NonNull final TextButton button, @NonNull final PlayerSystem player) {
        super(button);
        this.player = player;

    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(event.getListenerActor() == button){
            if(player.getBaseCON() >= 1){
                player.setCon(player.getBaseCON() - 1);
                notifyObservers();
            }
        }
    }

    @Override
    public void notifyObservers() {
        for(val observerStats : observers){
            if(observerStats instanceof ObserverCon)
                ((ObserverCon) observerStats).updateHp();
            if(observerStats instanceof ObserverMen)
                ((ObserverMen)observerStats).updateMp();
            else
                observerStats.update();
        }
    }
}
