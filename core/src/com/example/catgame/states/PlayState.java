package com.example.catgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.example.catgame.CatGame;
import com.example.catgame.locations.Room;
import com.example.catgame.sprites.Cat;

import java.util.Random;

public class PlayState extends State {

    private Room room;
    private Cat cat;
    private String cur_state;
    private Vector3 touchPoint;
    private Texture gameover;
    private int cat_position_id;
    private int touch_count;
    private Rectangle menuBtn;
    private Rectangle againBtn;
    private BitmapFont font;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        Gdx.input.setCatchBackKey(true);
        touchPoint = new Vector3();
        font = new BitmapFont();
        font.setColor(0, 0, 0, 1);
        font.getData().setScale(4);


        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        room = new Room(camera);
        cat = new Cat(room);
        cur_state = "watching";
        gameover = new Texture("gameover1920w.png");
        gameover.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        menuBtn = new Rectangle(420, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + 250, 480, 180);
        againBtn = new Rectangle(1020, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + 250, 480, 180);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched() && cur_state.equals("over")) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (menuBtn.contains(touchPoint.x, touchPoint.y)){
                gsm.set(new MenuState(gsm));
            }

            if(againBtn.contains(touchPoint.x, touchPoint.y)){
                gsm.set(new PlayState(gsm));
            }
        }

        if (Gdx.input.justTouched() && cur_state.equals("finding")) {
            touch_count++;
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (room.getWp(cat_position_id).getPoint().contains(touchPoint.x, touchPoint.y)){
                cur_state = "over";
                Gdx.input.vibrate(50);
            }
        }

        if (Gdx.input.justTouched() && cur_state.equals("watching")) {
            cur_state = "finding";
            Random random = new Random();
            cat_position_id = random.nextInt(room.getWpList().size());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) gsm.set(new MenuState(gsm));
    }

    @Override
    public void update(float dt) {
        handleInput();
        if (cur_state.equals("watching")) cat.update(dt, room);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        if (cur_state.equals("watching") || cur_state.equals("over")) sb.draw(room.getRoomBg(),
                camera.position.x - camera.viewportWidth / 2, camera.position.y - room.getRoomBg().getHeight() / 2);
        if (cur_state.equals("watching")) cat.render(sb);
        if (cur_state.equals("over")) sb.draw(gameover, camera.position.x - (gameover.getWidth() / 2), camera.position.y - (gameover.getHeight() / 2));
        if (cur_state.equals("over")) font.draw(sb, String.valueOf(touch_count), 1414, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + 585);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
