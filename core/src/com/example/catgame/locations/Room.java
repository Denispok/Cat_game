package com.example.catgame.locations;

import com.badlogic.gdx.graphics.Texture;

public class Room {

    private Texture room_bg;

    public Room (){
        room_bg = new Texture("room1920w.jpg");

    }

    public Texture getRoomBg() {
        return room_bg;
    }
}