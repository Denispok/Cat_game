package com.example.catgame.locations;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.example.catgame.CatGame;

import java.util.HashMap;

public class Waypoint {

    private static final int POINT_WIDTH = 175;
    private static final int POINT_HEIGHT = 145;

    private int id;
    private int spawn_id;
    /*          POSSIBLE STATES:   0 - sit&lie  |  1 - sit  |  2 - lie         */
    private int possible_states;
    //private int jump_distance;
    private int[] routes;
    public HashMap<Integer, Integer> jump_map;
    private Rectangle point;

    public Waypoint(OrthographicCamera camera, int id, int spawn_id, int possible_states, int x, int y, int rt1){
        routes = new int[1];
        routes[0] = rt1;

        initWp(id, spawn_id, possible_states);

        point = new Rectangle(x, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + y, POINT_WIDTH, POINT_HEIGHT);
    }

    public Waypoint(OrthographicCamera camera, int id, int spawn_id, int possible_states, int x, int y, int rt1, int rt2){
        routes = new int[2];
        routes[0] = rt1;
        routes[1] = rt2;

        initWp(id, spawn_id, possible_states);

        point = new Rectangle(x, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + y, POINT_WIDTH, POINT_HEIGHT);
    }

    public Waypoint(OrthographicCamera camera, int id, int spawn_id, int possible_states, int x, int y, int rt1, int rt2, int rt3){
        routes = new int[3];
        routes[0] = rt1;
        routes[1] = rt2;
        routes[2] = rt3;

        initWp(id, spawn_id, possible_states);

        point = new Rectangle(x, (camera.position.y - CatGame.HEIGHT_PRJ / 2) + y, POINT_WIDTH, POINT_HEIGHT);
    }

    public Waypoint(OrthographicCamera camera, int id, int spawn_id, int possible_states, int x, int y, int rt1, int rt2, int rt3, int rt4){
        routes = new int[4];
        routes[0] = rt1;
        routes[1] = rt2;
        routes[2] = rt3;
        routes[3] = rt4;

        initWp(id, spawn_id, possible_states);

        point = new Rectangle(x, (camera.position.y - (CatGame.HEIGHT_PRJ / 2)) + y, POINT_WIDTH, POINT_HEIGHT);
    }

    private void initWp(int id, int spawn_id, int possible_states){
        this.id = id;
        this.possible_states = possible_states;
        this.spawn_id = spawn_id;

        jump_map = new HashMap<Integer, Integer>();

        for(int i = 0; i < routes.length; i++){
            jump_map.put(routes[i], 0);
        }
    }

    public int getPossible_states() {
        return possible_states;
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