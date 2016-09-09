package com.example.catgame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.example.catgame.locations.Room;

import java.util.Random;

public class Cat {

    private static final float WAITTIME = 3;

    //private Random random;
    private float wait_time;
    private Texture cat;
    private Vector3 position;
    private int current_point_id;
    private boolean direction; // 0 is LEFT, 1 is RIGHT

    public Cat(Room room) {
        cat = new Texture("test cat.png");
        Random random = new Random();

        current_point_id = random.nextInt(room.spawnCount()) + 1; //spawn point count

        position = new Vector3(room.getWp(current_point_id).getPoint().x, room.getWp(current_point_id).getPoint().y, 0); // get rectangle coord from wp class
    }

    public void update(float dt) {
        if (wait_time <= 0){
            wait_time = WAITTIME;
        } else wait_time -= dt;

        Gdx.app.log("D", String.valueOf(wait_time));
    }

    public void catWait(){
    }

    public void render(SpriteBatch sb) {
        sb.draw(cat, position.x, position.y);
    }
}