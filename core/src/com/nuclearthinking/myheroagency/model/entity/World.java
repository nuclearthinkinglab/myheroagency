package com.nuclearthinking.myheroagency.model.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import com.nuclearthinking.myheroagency.model.entity.systems.RenderingSystem;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class World {
    public static final float WORLD_WIDTH = 10;

    private final TextureAtlas playerAtlas = Asset.getInstance().get("player/player.pack");
    private final Animation idle = new Animation(1 / 2f, playerAtlas.findRegions("still"), Animation.PlayMode.LOOP);
    private final Animation left = new Animation(1 / 6f, playerAtlas.findRegions("left"), Animation.PlayMode.LOOP);
    private final Animation right = new Animation(1 / 6f, playerAtlas.findRegions("right"), Animation.PlayMode.LOOP);

    private PooledEngine engine;

    public World (@NonNull final PooledEngine engine) {
        this.engine = engine;
    }

    public void create(){
        val player = createPlayer();
        createCamera(player);
    }

    private Entity createPlayer(){
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val player = engine.createComponent(PlayerComponent.class);
        val movement = engine.createComponent(MovementComponent.class);
        val position = engine.createComponent(TransformComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val texture = engine.createComponent(TextureComponent.class);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), idle);
        animation.getAnimations().put(AnimationState.RIGHT.getValue(), right);
        animation.getAnimations().put(AnimationState.LEFT.getValue(), left);

        position.getPos().set(5.0f, 1.0f, 0.0f);

        state.set(AnimationState.IDLE.getValue());

        entity.add(animation);
        entity.add(player);
        entity.add(movement);
        entity.add(position);
        entity.add(state);
        entity.add(texture);

        engine.addEntity(entity);

        return entity;
    }

    private void createCamera(Entity target) {
        val entity = engine.createEntity();

        val camera = new CameraComponent();
        camera.setCamera(engine.getSystem(RenderingSystem.class).getCamera());
        camera.setTarget(target);

        entity.add(camera);

        engine.addEntity(entity);
    }

}
