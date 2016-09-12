package com.example.catgame.locations;

import com.badlogic.gdx.math.Rectangle;

public class Waypoint {

    private static final int POINT_WIDTH = 200;
    private static final int POINT_HEIGHT = 200;

    private Rectangle point;
    private int id;
    private int spawn_id;
    private int[] routes;
    private boolean isSpawn;

    public Waypoint(int id, boolean isSpawn, int spawn_id, int x, int y, int rt1){
        this.id = id;
        this.spawn_id = spawn_id;
        this.isSpawn = isSpawn;

        routes = new int[1];
        routes[0] = rt1;

        point = new Rectangle(x, y, POINT_WIDTH, POINT_HEIGHT);
    }

    public Waypoint(int id, boolean isSpawn, int spawn_id, int x, int y, int rt1, int rt2){
        this.id = id;
        this.spawn_id = spawn_id;
        this.isSpawn = isSpawn;

        routes = new int[2];
        routes[0] = rt1;
        routes[1] = rt2;

        point = new Rectangle(x, y, POINT_WIDTH, POINT_HEIGHT);
    }

    public Waypoint(int id, boolean isSpawn, int spawn_id, int x, int y, int rt1, int rt2, int rt3){
        this.id = id;
        this.spawn_id = spawn_id;
        this.isSpawn = isSpawn;

        routes = new int[3];
        routes[0] = rt1;
        routes[1] = rt2;
        routes[2] = rt3;

        point = new Rectangle(x, y, POINT_WIDTH, POINT_HEIGHT);
    }

    public Waypoint(int id, boolean isSpawn, int spawn_id, int x, int y, int rt1, int rt2, int rt3, int rt4){
        this.id = id;
        this.spawn_id = spawn_id;
        this.isSpawn = isSpawn;

        routes = new int[4];
        routes[0] = rt1;
        routes[1] = rt2;
        routes[2] = rt3;
        routes[3] = rt4;

        point = new Rectangle(x, y, POINT_WIDTH, POINT_HEIGHT);
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