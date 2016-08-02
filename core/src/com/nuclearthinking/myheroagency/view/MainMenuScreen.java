package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.button.ExitListener;
import com.nuclearthinking.myheroagency.controller.button.LoadListener;
import com.nuclearthinking.myheroagency.controller.button.PlayListener;
import com.nuclearthinking.myheroagency.controller.button.SettingListener;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Izonami on 13.05.2016.
 */
public class MainMenuScreen extends AbstractScreen {

    private static Table table;
    private static TextButton play, load, settings, exit;
    private static UiFactory uiFactory;

    @Override
    public void buildStage() {
        uiFactory = new UiFactory();
        table = new Table(); // Создаем таблицу
        table.setDebug(Constants.DEBUG); // Включаем дебаг режим (Разные прямоугольнико вокруг кнопок это оно самое)
        table.setFillParent(true);

        initButton(); // Создаем кнопки

        // Таблица рулит размером кнопок, отступами и прочей хренотой
        table.add(play).left().expandX().width(100).height(40).pad(10);
        table.row(); // Перенос строки
        table.add(load).left().expandX().width(100).height(40).pad(10);
        table.row();
        table.add(settings).left().expandX().width(100).height(40).pad(10);
        table.row();
        table.add(exit).left().expandX().width(100).height(40).pad(10);

        stage.addActor(table); // Добавляем таблицу на Stage
        // Этот экшен "выплывает" меню
        stage.addAction(sequence(moveTo(stage.getWidth(), 0), moveTo(0, 0, .5f))); // Это чисто попробовать возможности. Акшены очень мощьная штука.
    }

    private void initButton() {
        // При создании нужно передавать название кнопки(лейбл), скин и соответсвующий скину шрифт. Default не поддерживает русский
        play = uiFactory.getTextButton(locale.get("buttonPlay"));
        play.getLabel().setFontScale(.9f);
        play.getLabel().setColor(Color.FOREST);
        play.addListener(new PlayListener(play)); //Добавляет листнер кнопке

        load = uiFactory.getTextButton(locale.get("buttonLoad"));
        load.getLabel().setFontScale(.7f);
        load.addListener(new LoadListener(load));

        settings = uiFactory.getTextButton(locale.get("buttonSettings"));
        settings.getLabel().setFontScale(.7f);
        settings.addListener(new SettingListener(settings));

        exit = uiFactory.getTextButton(locale.get("buttonExit"));
        exit.getLabel().setFontScale(.7f);
        exit.addListener(new ExitListener(exit));
    }

    @Override
    public void dispose() {
        super.dispose();

        play.clearListeners();
        load.clearListeners();
        settings.clearListeners();
        exit.clearListeners();
    }

}
