package com.example.catgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.catgame.CatGame;

public class RecordsState extends State {

    private static final int INDENT = 134;

    private Texture records_screen;
    private Texture background;
    private Preferences records;
    private BitmapFont font;

    public RecordsState(GameStateManager gsm) {
        super(gsm);
        Gdx.input.setCatchBackKey(true);
        records = Gdx.app.getPreferences("records");

        records_screen = new Texture("records_screen.png");
        records_screen.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        background = new Texture("menu_bg 16x10.png");

        font = new BitmapFont(Gdx.files.internal("sitka_ru.fnt"), Gdx.files.internal("sitka_ru.png"), false);
        font.setColor(Color.BLACK);
        //font.getData().setScale(4);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.justTouched()) gsm.set(new MenuState(gsm));
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
        sb.draw(records_screen, camera.position.x - (records_screen.getWidth() / 2), camera.position.y - (records_screen.getHeight() / 2));
        draw_records(sb);
        sb.end();
    }

    private void draw_records(SpriteBatch sb) {
        for (int i = 1; i <= 5; i++) {
            String touches;
            if (records.getInteger(String.valueOf(i)) != 0) {
                if (records.getInteger(String.valueOf(i)) % 10 == 1) touches = "касание";
                else if(records.getInteger(String.valueOf(i)) % 10 == 2 || records.getInteger(String.valueOf(i)) % 10 == 3 ||
                        records.getInteger(String.valueOf(i)) % 10 == 4) touches = "касания";
                else touches = "касаний";
                font.draw(sb, String.valueOf(records.getInteger(String.valueOf(i))) + " " + touches, 750, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + 940  - (i * INDENT));
            }
        }
    }

    @Override
    public void dispose() {

    }
}
