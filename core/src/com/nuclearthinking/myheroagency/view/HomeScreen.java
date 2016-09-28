package com.nuclearthinking.myheroagency.view;

import com.badlogic.ashley.core.PooledEngine;
import com.nuclearthinking.myheroagency.controller.PlayerController;
import com.nuclearthinking.myheroagency.model.entity.GameWorld;
import com.nuclearthinking.myheroagency.model.entity.systems.*;

/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class HomeScreen extends AbstractScreen {

    private final GameWorld gameWorld;
    private final PlayerController pc;
    private final PooledEngine engine;

    public HomeScreen() {
        engine = new PooledEngine();
        gameWorld = new GameWorld(engine);
        pc = new PlayerController(engine);
    }

    @Override
    public void buildStage() {
        engine.addSystem(new MapSystem(gameWorld.getWorld()));
        engine.addSystem(new LightSystem(gameWorld.getWorld()));
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new NpcSystem());
        engine.addSystem(new CameraSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new StateSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderingSystem(engine.getSystem(MapSystem.class).getBatch(), gameWorld.getWorld()));

        engine.getSystem(MapSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());
        engine.getSystem(LightSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());

        gameWorld.create();

        multi.addProcessor(pc);
    }

    public void update (float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;

        engine.update(deltaTime);
        pc.update();
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

}
