package com.example.catgame.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CatAnimation {

    private static final float FRAME_SPEED = 0.15f;
    public static final float JUMP_SPEED = 0.15f;

    private static final int WALK_COLS = 4;
    Animation walkAnimation;
    Texture walkSheet;
    TextureRegion[] walkFrames;

    private static final int SIT_COLS = 5;
    public Animation sitAnimation;
    Texture sitSheet;
    TextureRegion[] sitFrames;

    private static final int LIE_COLS = 5;
    public Animation lieAnimation;
    Texture lieSheet;
    TextureRegion[] lieFrames;

    private static final int JUMP_COLS = 8;
    Animation jumpAnimation;
    Texture jumpSheet;
    TextureRegion[] jumpFrames;

    TextureRegion currentFrame;
    public float stateTime;

    public CatAnimation() {
        initWalk();
        initSit();
        initLie();
        initJump();
        stateTime = 0f;
    }

    public void initWalk() {
        walkSheet = new Texture("cat_walk_sheet.png");
        walkSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/WALK_COLS, walkSheet.getHeight());
        walkFrames = new TextureRegion[WALK_COLS];
        int index = 0;
        for (int i = 0; i < WALK_COLS; i++) {
            walkFrames[index++] = tmp[0][i];
        }
        walkAnimation = new Animation(FRAME_SPEED, walkFrames);
    }

    public void initSit() {
        sitSheet = new Texture("cat_sit_sheet.png");
        sitSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion[][] tmp = TextureRegion.split(sitSheet, sitSheet.getWidth()/SIT_COLS, sitSheet.getHeight());
        sitFrames = new TextureRegion[SIT_COLS];
        int index = 0;
        for (int i = 0; i < SIT_COLS; i++) {
            sitFrames[index++] = tmp[0][i];
        }
        sitAnimation = new Animation(FRAME_SPEED, sitFrames);
    }

    public void initLie() {
        lieSheet = new Texture("cat_lie_sheet.png");
        lieSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion[][] tmp = TextureRegion.split(lieSheet, lieSheet.getWidth()/LIE_COLS, lieSheet.getHeight());
        lieFrames = new TextureRegion[LIE_COLS];
        int index = 0;
        for (int i = 0; i < LIE_COLS; i++) {
            lieFrames[index++] = tmp[0][i];
        }
        lieAnimation = new Animation(FRAME_SPEED, lieFrames);
    }

    private void initJump() {
        jumpSheet = new Texture("cat_jump_sheet8f.png");
        jumpSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion[][] tmp = TextureRegion.split(jumpSheet, jumpSheet.getWidth()/JUMP_COLS, jumpSheet.getHeight());
        jumpFrames = new TextureRegion[JUMP_COLS];
        int index = 0;
        for (int i = 0; i < JUMP_COLS; i++) {
            jumpFrames[index++] = tmp[0][i];
        }
        jumpAnimation = new Animation(JUMP_SPEED, jumpFrames);
    }
}
