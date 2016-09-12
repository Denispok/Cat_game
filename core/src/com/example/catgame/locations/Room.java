package com.example.catgame.locations;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int spawn_count;
    private Texture room_bg;
    private List<Waypoint> wp = new ArrayList<Waypoint>();

    public Room (){
        room_bg = new Texture("room1920w.jpg");
        init_wp();
    }

    private void init_wp() {
        wp.add(new Waypoint(0, true, 1, 0, 0, 1, 3));
        wp.add(new Waypoint(1, false, 0, 1660, 1025, 2, 0));
        wp.add(new Waypoint(2, false, 0, 0, 1025, 3, 1));
        wp.add(new Waypoint(3, false, 0, 1660, 0, 0, 2));
        wp.add(new Waypoint(4, true, 2, 600, 500, 0, 2, 1));
    }

    public Waypoint getWp (int id){
        if (wp.get(id) != null) return wp.get(id);
        else return null;
    }

    public Waypoint getWpBySpawnId (int spawn_id){
        for (int i = 0; i <= wp.size() - 1; i++){
            if (wp.get(i).getSpawn_id() == spawn_id) return wp.get(i);
        }
        return null;
    }

    public int spawnCount(){
        spawn_count = 0;
        for(int i = 0; i <= wp.size() - 1; i++){
            if (wp.get(i).isSpawn()) spawn_count++;
        }
        return spawn_count;
    }

    public Texture getRoomBg() {
        return room_bg;
    }
}