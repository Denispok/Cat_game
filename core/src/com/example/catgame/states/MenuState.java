package com.example.catgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.example.catgame.CatGame;

public class MenuState extends State {

    private Texture background;

    private Texture playBtn;
    private Rectangle playBounds;
    private Texture recordsBtn;
    private Rectangle recordsBounds;
    private Texture exitBtn;
    private Rectangle exitBounds;

    Vector3 touchPoint;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        background = new Texture("menu_bg 16x10.png");

        playBtn = new Texture("play.png");
        playBtn.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        playBounds = new Rectangle(camera.position.x - (playBtn.getWidth() / 2), (camera.position.y / 2) * 3 - (playBtn.getHeight() / 2), playBtn.getWidth(), playBtn.getHeight());
        recordsBtn = new Texture("records.png");
        recordsBtn.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        recordsBounds = new Rectangle(camera.position.x - (recordsBtn.getWidth() / 2), camera.position.y - (recordsBtn.getHeight() / 2), recordsBtn.getWidth(), recordsBtn.getHeight());
        exitBtn = new Texture("exit.png");
        exitBtn.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        exitBounds = new Rectangle(camera.position.x - (exitBtn.getWidth() / 2), (camera.position.y / 2) * 1 - (exitBtn.getHeight() / 2), exitBtn.getWidth(), exitBtn.getHeight());

        touchPoint = new Vector3();
    }

    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                gsm.set(new PlayState(gsm));
            }
            if (recordsBounds.contains(touchPoint.x, touchPoint.y)) {
                Gdx.app.log("D", "success bich2");
            }
            if (exitBounds.contains(touchPoint.x, touchPoint.y)) {
                gsm.pop();
            }
        }
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