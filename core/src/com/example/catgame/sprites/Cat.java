package com.example.catgame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.example.catgame.locations.Room;

import java.util.Random;

public class Cat {

    private static final double WAITTIME = 2;
    private static final double VELOCITY = 5;
    private static final double VELOCITY_JUMP = 10;

    private double wait_time;
    private Vector3 position;
    private String state;
    private int current_point_id;
    private int old_id;
    private float current_distance_x;
    private float current_distance_y;
    private double current_hypotenuse;
    private int direction_x; // -1 is LEFT, 1 is RIGHT
    private int direction_y;
    private boolean point_active;
    private CatAnimation catAnim;

    public Cat(Room room) {
        catAnim = new CatAnimation();
        Random random = new Random();

        //RANDOM SPAWN
        current_point_id = room.getWpBySpawnId(random.nextInt(room.spawnCount()) + 1).getId(); //spawn point count
        position = new Vector3(room.getWp(current_point_id).getPoint().x, room.getWp(current_point_id).getPoint().y, 0); // get rectangle coord from wp class
        wait_time = WAITTIME;
        catAnim.currentFrame = catAnim.sitAnimation.getKeyFrame(5);
        state = "begin";
    }

    public void update(float dt, Room room) {
        if (wait_time > 0){
            wait_time -= dt;
            if (point_active) point_active = false;
        } else {
            if (!point_active) findNewPoint(room);

            if (direction_x == 1 && direction_y == 1){
                if (position.x < room.getWp(current_point_id).getPoint().getX() || position.y < room.getWp(current_point_id).getPoint().getY()) catMove(room);
                else catStop(room);
            }

            if (direction_x == 1 && direction_y == -1){
                if (position.x < room.getWp(current_point_id).getPoint().getX() || position.y > room.getWp(current_point_id).getPoint().getY()) catMove(room);
                else catStop(room);
            }

            if (direction_x == -1 && direction_y == 1){
                if (position.x > room.getWp(current_point_id).getPoint().getX() || position.y < room.getWp(current_point_id).getPoint().getY()) catMove(room);
                else catStop(room);
            }

            if (direction_x == -1 && direction_y == -1){
                if (position.x > room.getWp(current_point_id).getPoint().getX() || position.y > room.getWp(current_point_id).getPoint().getY()) catMove(room);
                else catStop(room);
            }
        }


        //Gdx.app.log("D", "wait_time: " + String.valueOf(wait_time));
    }

    public void render(SpriteBatch sb) {
        catAnim.stateTime += Gdx.graphics.getDeltaTime();

                                         /* STATES */
        if (state.equals("walk")) {
            catAnim.currentFrame = catAnim.walkAnimation.getKeyFrame(catAnim.stateTime, true);
            animFlip();
        } else if (state.equals("sit")){
            catAnim.currentFrame = catAnim.sitAnimation.getKeyFrame(catAnim.stateTime, false);
            animFlip();
        } else if (state.equals("lie")){
            catAnim.currentFrame = catAnim.lieAnimation.getKeyFrame(catAnim.stateTime, false);
            animFlip();
        } else if (state.equals("jump")){
            catAnim.currentFrame = catAnim.jumpAnimation.getKeyFrame(catAnim.stateTime, false);
            animFlip();
        }

        //Gdx.app.log("D", "wait_time: " + String.valueOf(catAnim.stateTime));

        if ((state.equals("jump") && direction_x == -1 && direction_y == -1 && catAnim.stateTime >= 4*catAnim.JUMP_SPEED && catAnim.stateTime < 6*catAnim.JUMP_SPEED)) {
            sb.draw(catAnim.currentFrame, position.x, position.y, catAnim.currentFrame.getRegionWidth() / 2, catAnim.currentFrame.getRegionHeight() / 2,
                    catAnim.currentFrame.getRegionWidth(), catAnim.currentFrame.getRegionHeight(), 1, 1, 45);
        } else if ((state.equals("jump") && direction_x == 1 && direction_y == -1 && catAnim.stateTime >= 4*catAnim.JUMP_SPEED && catAnim.stateTime < 6*catAnim.JUMP_SPEED)){
            sb.draw(catAnim.currentFrame, position.x, position.y, catAnim.currentFrame.getRegionWidth() / 2, catAnim.currentFrame.getRegionHeight() / 2,
                    catAnim.currentFrame.getRegionWidth(), catAnim.currentFrame.getRegionHeight(), 1, 1, -45);
        } else sb.draw(catAnim.currentFrame, position.x, position.y);

    }

    private void animFlip() {
        if(!catAnim.currentFrame.isFlipX() && direction_x == 1) catAnim.currentFrame.flip(true, false);
        else if(catAnim.currentFrame.isFlipX() && direction_x == -1) catAnim.currentFrame.flip(true, false);
    }

    private void catMove(Room room) {
        if((room.getWp(current_point_id).jump_map.get(old_id) != 0 &&
                room.getWp(current_point_id).jump_map.get(old_id) >= Math.hypot(Math.abs(room.getWp(current_point_id).getPoint().getX() - position.x),
                        Math.abs(room.getWp(current_point_id).getPoint().getY() - position.y))) || (room.getWp(current_point_id).jump_map.get(old_id) == -1)) {
            catJump();
            return;
        }

        if (current_distance_x != 0 && current_distance_y != 0){
            position.x += direction_x * VELOCITY * (current_distance_x / current_hypotenuse);
            position.y += direction_y * VELOCITY * (current_distance_y / current_hypotenuse);
        } else if (current_distance_x != 0){
            position.x += direction_x * VELOCITY;
        } else if (current_distance_y != 0){
            position.y += direction_y * VELOCITY;
        }
    }

    private void catStop(Room room) {
        position.x = room.getWp(current_point_id).getPoint().getX();
        position.y = room.getWp(current_point_id).getPoint().getY();
        wait_time = WAITTIME;
        catAnim.stateTime = 0;
        catAnim.currentFrame = catAnim.sitAnimation.getKeyFrame(catAnim.stateTime, false);
        state = "sit";
    }

    private void catJump(){
        if (!state.equals("jump")){
            catAnim.stateTime = 0;
            state = "jump";
        }

        if(catAnim.stateTime <= 3*catAnim.JUMP_SPEED) return;

        if (current_distance_x != 0 && current_distance_y != 0){
            position.x += direction_x * VELOCITY_JUMP * (current_distance_x / current_hypotenuse);
            position.y += direction_y * VELOCITY_JUMP * (current_distance_y / current_hypotenuse);
        } else if (current_distance_x != 0){
            position.x += direction_x * VELOCITY_JUMP;
        } else if (current_distance_y != 0){
            position.y += direction_y * VELOCITY_JUMP;
        }
    }

    private void findNewPoint(Room room) {
        Random random = new Random();
        old_id = current_point_id;
        do {
            current_point_id = room.getWp(current_point_id).getRoutes()[random.nextInt((room.getWp(current_point_id).getRoutes().length))];
        } while (current_point_id == old_id);

        if (room.getWp(current_point_id).getPoint().getX() >= room.getWp(old_id).getPoint().getX()) direction_x = 1; else direction_x = -1;
        if (room.getWp(current_point_id).getPoint().getY() >= room.getWp(old_id).getPoint().getY()) direction_y = 1; else direction_y = -1;

        current_distance_x = Math.abs(room.getWp(current_point_id).getPoint().getX() - room.getWp(old_id).getPoint().getX());
        current_distance_y = Math.abs(room.getWp(current_point_id).getPoint().getY() - room.getWp(old_id).getPoint().getY());
        current_hypotenuse = Math.hypot(current_distance_x, current_distance_y);

        point_active = true;
        state = "walk";
    }
}












