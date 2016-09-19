package com.example.catgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
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
    private int cat_pose; // 0 - sit, 1 - lie
    private int touch_count;
    private Rectangle menuBtn;
    private Rectangle againBtn;
    private BitmapFont font;
    private Preferences records;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        Gdx.input.setCatchBackKey(true);
        records = Gdx.app.getPreferences("records");

        touchPoint = new Vector3();
        font = new BitmapFont(Gdx.files.internal("sitka.fnt"), Gdx.files.internal("sitka.png"), false);
        font.setColor(Color.BLACK);
        //font.getData().setScale(4);


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

        if (Gdx.input.justTouched() && cur_state.equals("caught")) {
            boolean is_recorded = false;
            for (int i = 1; i <= 5 ; i++){
                if (records.getInteger(String.valueOf(i)) > touch_count || records.getInteger(String.valueOf(i)) == 0){
                    records.putInteger(String.valueOf(i), touch_count);
                    records.flush();
                    is_recorded = true;
                }
                if (is_recorded) i = 6;//return;
            }
            cur_state = "over";
        }

        if (Gdx.input.justTouched() && cur_state.equals("finding")) {
            touch_count++;
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (room.getWp(cat_position_id).getPoint().contains(touchPoint.x, touchPoint.y)){
                Random random = new Random(); // random cat pose
                if (room.getWp(cat_position_id).getPossible_states() == 0) {
                    if (random.nextInt(2) == 0) cat_pose = 0;
                    else cat_pose = 1;
                } else if (room.getWp(cat_position_id).getPossible_states() == 1) cat_pose = 0;
                else cat_pose = 1;

                Gdx.input.vibrate(50);
                cur_state = "caught";
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
        sb.begin();
        if (cur_state.equals("watching") || cur_state.equals("caught") || cur_state.equals("over")) sb.draw(room.getRoomBg(),
                camera.position.x - camera.viewportWidth / 2, camera.position.y - room.getRoomBg().getHeight() / 2);
        if (cur_state.equals("watching")) cat.render(sb);
        if (cur_state.equals("caught") && cat_pose == 0) sb.draw(cat.catAnim.sitAnimation.getKeyFrame(5), room.getWp(cat_position_id).getPoint().x, room.getWp(cat_position_id).getPoint().y);
        if (cur_state.equals("caught") && cat_pose == 1) sb.draw(cat.catAnim.lieAnimation.getKeyFrame(5), room.getWp(cat_position_id).getPoint().x, room.getWp(cat_position_id).getPoint().y);
        if (cur_state.equals("over")) sb.draw(gameover, camera.position.x - (gameover.getWidth() / 2), camera.position.y - (gameover.getHeight() / 2));
        if (cur_state.equals("over")) font.draw(sb, String.valueOf(touch_count), 1414, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + 595);
        sb.end();
    }

    @Override
    public void dispose() {
        gameover.dispose();
        font.dispose();
        cat.dispose();
        room.dispose();
    }
}
