package com.FrobPlugins.me;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class InstructionScreen implements Screen{

    public static OrthographicCamera camera;
    Main main;

    //Textures
    private Texture Background;

    //Sprites
    private Sprite background;

    //Font
    public static BitmapFont font;

    public InstructionScreen(Main main){
        this.main = main;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 800, 400);
        camera.update();
        LoadTextures();
        LoadSprites();
        SetupFont();
    }

    public void show() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        Main.batch.begin();
            Main.batch.draw(background, 0, 0);
        Main.batch.end();
    }

    public void hide() {

    }

    public void dispose() {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void resize(int width, int height) {

    }

    public void LoadTextures(){
        Background = new Texture(Gdx.files.internal("assets/Background.png"));
    }

    public void LoadSprites(){
        background = new Sprite(Background);
    }

    public void SetupFont(){
        font = new BitmapFont(Gdx.files.internal("assets/Font/MyFont.fnt"));
    }
}
