package com.nuclearthinking.myheroagency.controller.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;

/**
 * Created by Izonami on 19.06.2016.
 */
public class BackListener extends AbstractButtonListener {

    public BackListener(final TextButton button){
        super(button);
    }

    @Override
    public void clicked (InputEvent event, float x, float y) {
        ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU_SCREEN );
    }

}