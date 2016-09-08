package com.example.catgame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Cat {

    private Vector3 position;
    private Texture cat;
    private boolean direction; // 0 is LEFT, 1 is RIGHT

    public Cat() {
        position = new Vector3();
        cat = new Texture("cat_lying.png");
    }

    public Texture getCat() {
        return cat;
    }
}
