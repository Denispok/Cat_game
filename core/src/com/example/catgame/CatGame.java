package com.example.catgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.catgame.states.GameStateManager;
import com.example.catgame.states.MenuState;

public class CatGame extends ApplicationAdapter {
	/*public static final float WIDTH = 1920;
	public static final float HEIGHT = 1200;*/

	public static float WIDTH;
	public static float HEIGHT;
	public static float RATIO;
	public static final int WIDTH_PRJ = 1920;
	public static final int HEIGHT_PRJ = 1080;

	public static final String TITLE = "Cat Demo";

	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		RATIO = HEIGHT / WIDTH;

		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}