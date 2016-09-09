package com.example.catgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.catgame.CatGame;
import com.example.catgame.locations.Room;
import com.example.catgame.sprites.Cat;

public class PlayState extends State {

    private Room room;
    private Cat cat;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        room = new Room();
        cat = new Cat(room);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        cat.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(room.getRoomBg(), camera.position.x - camera.viewportWidth / 2, camera.position.y - room.getRoomBg().getHeight() / 2);
        cat.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
