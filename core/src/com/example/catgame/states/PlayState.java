package com.example.catgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.example.catgame.CatGame;

public class PlayState extends State {

    private Texture cat;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, CatGame.WIDTH, CatGame.HEIGHT);
        cat = new Texture("cat.png");
    }

    @Override
    protected void handleInput() {
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (screenX >= 18 * (camera.position.x / 10)) {
                    gsm.set(new MenuState(gsm));
                    Gdx.app.log("D", String.valueOf(camera.position.x));
                    Gdx.app.log("D", String.valueOf(screenX));
                }

                return super.touchDown(screenX, screenY, pointer, button);
            }
        });
    }

    @Override
    public void update(float dt) {
        handleInput();
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Gdx.app.log("D1", String.valueOf(touchPos));
            Gdx.app.log("D1", String.valueOf(camera.position));
            camera.unproject(touchPos);
            Gdx.app.log("D2", String.valueOf(touchPos));
            Gdx.app.log("D2", String.valueOf(camera.unproject(camera.position)));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(cat, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
