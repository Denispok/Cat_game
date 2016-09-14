package com.example.catgame.locations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.example.catgame.CatGame;
import com.example.catgame.states.PlayState;
import com.example.catgame.states.State;

public class Waypoint {

    private static final int POINT_WIDTH = 200;
    private static final int POINT_HEIGHT = 200;

    private Rectangle point;
    private int id;
    /*          POSSIBLE STATES:   0 - sit&lie  |  1 - sit  |  2 - lie         */
    private int possible_states;
    private boolean isSpawn;
    private int spawn_id;
    private int[] routes;

    public Waypoint(OrthographicCamera camera, int id, int possible_states, boolean isSpawn, int spawn_id, int x, int y, int rt1){
        initWp(id, possible_states, isSpawn, spawn_id);

        routes = new int[1];
        routes[0] = rt1;

        point = new Rectangle(x, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + y, POINT_WIDTH, POINT_HEIGHT);
    }

    public Waypoint(OrthographicCamera camera, int id, int possible_states, boolean isSpawn, int spawn_id, int x, int y, int rt1, int rt2){
        initWp(id, possible_states, isSpawn, spawn_id);

        routes = new int[2];
        routes[0] = rt1;
        routes[1] = rt2;

        point = new Rectangle(x, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + y, POINT_WIDTH, POINT_HEIGHT);
    }

    public Waypoint(OrthographicCamera camera, int id, int possible_states, boolean isSpawn, int spawn_id, int x, int y, int rt1, int rt2, int rt3){
        initWp(id, possible_states, isSpawn, spawn_id);

        routes = new int[3];
        routes[0] = rt1;
        routes[1] = rt2;
        routes[2] = rt3;

        point = new Rectangle(x, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + y, POINT_WIDTH, POINT_HEIGHT);
    }

    public Waypoint(OrthographicCamera camera, int id, int possible_states, boolean isSpawn, int spawn_id, int x, int y, int rt1, int rt2, int rt3, int rt4){
        initWp(id, possible_states, isSpawn, spawn_id);

        routes = new int[4];
        routes[0] = rt1;
        routes[1] = rt2;
        routes[2] = rt3;
        routes[3] = rt4;

        point = new Rectangle(x, (camera.position.y - (CatGame.HEIGHT_PRJ / 2)) + y, POINT_WIDTH, POINT_HEIGHT);
    }

    private void initWp(int id, int possible_states, boolean isSpawn, int spawn_id){
        this.id = id;
        this.possible_states = possible_states;
        this.isSpawn = isSpawn;
        this.spawn_id = spawn_id;
    }

    public boolean isSpawn() {
        return isSpawn;
    }

    public int getSpawn_id() {
        return spawn_id;
    }

    public int getId() {
        return id;
    }

    public int[] getRoutes() {
        return routes;
    }

    public Rectangle getPoint() {
        return point;
    }
}



// Не суди меня.