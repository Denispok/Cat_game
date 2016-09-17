package com.example.catgame.locations;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private Texture room_bg;
    private List<Waypoint> wp = new ArrayList<Waypoint>();

    public Room (OrthographicCamera camera){
        room_bg = new Texture("room1920w.jpg");
        init_wp(camera);
    }

    private void init_wp(OrthographicCamera cam) { // GAME FIELD 16:9 !!!
        /*wp.add(new Waypoint(cam, 0, 0, 0, 90, 180, 1));
        wp.get(0).jump_map.put(1, 350);                         //JUMP TEST
        wp.add(new Waypoint(cam, 1, 1, 0, 554, 35, 0));
        wp.get(1).jump_map.put(0, -350);*/
        wp.add(new Waypoint(cam, 0, 0, 0, 90, 180, 1, 2, 3));
        wp.get(0).jump_map.put(1, -1);
        wp.get(0).jump_map.put(2, -1);
        wp.get(0).jump_map.put(3, 350);
        wp.add(new Waypoint(cam, 1, 0, 0, 250, 0, 0, 3));
        wp.get(1).jump_map.put(0, -1);
        wp.add(new Waypoint(cam, 2, 1, 2, 240, 556, 0));
        wp.get(2).jump_map.put(0, -1);
        wp.add(new Waypoint(cam, 3, 0, 0, 554, 35, 0, 1, 4, 5));
        wp.get(3).jump_map.put(0, -1);
        wp.get(3).jump_map.put(4, -1);
        wp.add(new Waypoint(cam, 4, 0, 0, 720, 435, 3, 6));
        wp.get(4).jump_map.put(3, 300);
        wp.add(new Waypoint(cam, 5, 0, 0, 875, 10, 3, 7, 8));
        wp.add(new Waypoint(cam, 6, 2, 0, 980, 440, 4, 7));
        wp.get(6).jump_map.put(7, -1);
        wp.add(new Waypoint(cam, 7, 0, 0, 1120, 140, 5, 6, 8, 9));
        wp.get(7).jump_map.put(6, -1);
        wp.get(7).jump_map.put(9, -1);
        wp.add(new Waypoint(cam, 8, 0, 0, 1248, 0, 5, 7, 11));
        wp.add(new Waypoint(cam, 9, 0, 1, 1360, 395, 7, 10));
        wp.get(9).jump_map.put(7, -1);
        wp.get(9).jump_map.put(10, -1);
        wp.add(new Waypoint(cam, 10, 3, 0, 1500, 300, 9, 11, 12));
        wp.get(10).jump_map.put(9, -1);
        wp.get(10).jump_map.put(11, -1);
        wp.get(10).jump_map.put(12, -1);
        wp.add(new Waypoint(cam, 11, 0, 0, 1650, 20, 8, 10));
        wp.get(11).jump_map.put(10, -1);
        wp.add(new Waypoint(cam, 12, 0, 1, 1695, 380, 10));
        wp.get(12).jump_map.put(10, -1);
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
         int spawn_count = 0;
        for(int i = 0; i <= wp.size() - 1; i++){
            if (wp.get(i).getSpawn_id() != 0) spawn_count++;
        }
        return spawn_count;
    }

    public Texture getRoomBg() {
        return room_bg;
    }
}