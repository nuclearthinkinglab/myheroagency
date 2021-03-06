package com.nuclearthinking.myheroagency.model.ui.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.listener.button.player.AddStatsListener;
import com.nuclearthinking.myheroagency.controller.listener.button.player.RemoveStatsListener;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 10.10.2016.
 */
public final class StatHudComponent implements Component {

    public static final Table table = new Table();

    private @Getter @Setter TextButton plus;
    private @Getter @Setter TextButton minus;
    private @Getter @Setter Label con;
    private @Getter @Setter AddStatsListener addStatsListener;
    private @Getter @Setter RemoveStatsListener removeStatsListener;
}
