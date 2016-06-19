package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.Gdx;

/**
 * Created by Izonami on 19.06.2016.
 */
public class ScreenController {

    public void splashUpdate(){
        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(66) || Gdx.input.isKeyPressed(131)) {
            ScreenManager.getInstance().showScreen( ScreenEnum.MAIN_MENU_SCREEN );
        }
    }
}