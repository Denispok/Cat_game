package com.example.catgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.example.catgame.CatGame;

public abstract class State {

    protected OrthographicCamera camera;
    protected GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;

        //camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera = new OrthographicCamera(CatGame.WIDTH_PRJ, CatGame.WIDTH_PRJ * CatGame.RATIO);
        camera.update();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

    public OrthographicCamera getCamera() {
        return camera;
    }
}