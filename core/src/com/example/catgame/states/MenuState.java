package com.example.catgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.example.catgame.CatGame;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private Texture recordsBtn;
    private Texture exitBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("menu_bg 16x10.png");
        playBtn = new Texture("play.png");
        recordsBtn = new Texture("records.png");
        exitBtn = new Texture("exit.png");
        camera.setToOrtho(false, CatGame.WIDTH, CatGame.HEIGHT);
    }

    @Override
    protected void handleInput() {
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (screenX >= camera.position.x - (playBtn.getWidth() / 2)) /* && screenX <= camera.position.x + (playBtn.getWidth() / 2) &&
                        screenY >= (camera.position.y / 2) * 3 - (playBtn.getHeight() / 2) && screenY <= (camera.position.y / 2) * 3 + (playBtn.getHeight() / 2))*/ {
                    Gdx.app.log("D2", String.valueOf(playBtn.getWidth()));

                    gsm.set(new PlayState(gsm));
                }

                return super.touchDown(screenX, screenY, pointer, button);
            }
        });
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, camera.position.x - (playBtn.getWidth() / 2), (camera.position.y / 2) * 3 - (playBtn.getHeight() / 2));
        sb.draw(recordsBtn, camera.position.x - (recordsBtn.getWidth() / 2), camera.position.y - (playBtn.getHeight() / 2));
        sb.draw(exitBtn, camera.position.x - (exitBtn.getWidth() / 2), (camera.position.y / 2) * 1 - (exitBtn.getHeight() / 2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        recordsBtn.dispose();
        exitBtn.dispose();
    }
}
