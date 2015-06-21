package com.FrobPlugins.me;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Main extends Game implements Screen,ApplicationListener {
	public static SpriteBatch batch;
	Rectangle rect;
	ShapeRenderer shapeRenderer;
	ShapeRenderer shapeRenderer2;
	public static OrthographicCamera camera;
	public static BitmapFont font;
	LevelScreen levelscreen;
	
	public static boolean hoverButton1 = false;
	public static boolean hoverButton2 = false;
	
	//Textures
	Texture Background;

	//Sprites
	Sprite sprite_Background;
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 400;
	
	public void create () {
		batch = new SpriteBatch();
		LoadTexture();
		LoadSprite();
		rect = new Rectangle(0, 0, 100, 100);
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 800, 400);
		camera.update();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer2 = new ShapeRenderer();
		SetupFont();
		levelscreen = new LevelScreen(this);
	}
	
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.begin();
			batch.draw(sprite_Background, 0, 0);
			font.draw(batch, "Play", SCREEN_WIDTH/2 - 25, SCREEN_HEIGHT/2 + 80);
			font.draw(batch, "Options", SCREEN_WIDTH/2 - 45, SCREEN_HEIGHT/2 + 20);
			font.draw(batch, "X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY(), 100, 100);
		batch.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    
		shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(new Color((float) 0.3,(float) 0.3,(float) 0.3, 0.5f));
			shapeRenderer.rect(SCREEN_WIDTH/2 - 100, SCREEN_HEIGHT/2 + 100, 200, -300);
			
			if(!hoverButton1){ shapeRenderer.setColor(new Color((float) 0.3,(float) 0.3,(float) 0.3, 0.5f)); }
			if(hoverButton1){ shapeRenderer.setColor(new Color((float) 1,(float) 1,(float) 1, 0.5f)); }
			shapeRenderer.rect(SCREEN_WIDTH/2 - 60, SCREEN_HEIGHT/2 + 95, 120, -55);
        shapeRenderer.end();
       	shapeRenderer2.begin(ShapeType.Filled);
        	if(!hoverButton2){ shapeRenderer2.setColor(new Color((float) 0.3,(float) 0.3,(float) 0.3, 0.5f)); }
        	if(hoverButton2){ shapeRenderer2.setColor(new Color((float) 1,(float) 1,(float) 1, 0.5f)); }
        	shapeRenderer2.rect(SCREEN_WIDTH/2 - 60, SCREEN_HEIGHT/2 + 35, 120, -55);
       	shapeRenderer2.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Hover();
	}
	
	public void Hover(){
		if(Gdx.input.getX() > SCREEN_WIDTH/2 - 60 && Gdx.input.getX() < SCREEN_WIDTH/2 + 60
        		&& Gdx.input.getY() < SCREEN_HEIGHT/2 - 40 && Gdx.input.getY() > SCREEN_HEIGHT/2 - 95){
			if(Gdx.input.justTouched()){
				setScreen(levelscreen);
			}
        	hoverButton1 = true;
    	}else{
    		hoverButton1 = false;
    	}
		
		if(Gdx.input.getX() > SCREEN_WIDTH/2 - 60 && Gdx.input.getX() < SCREEN_WIDTH/2 + 60
				&& Gdx.input.getY() < SCREEN_HEIGHT/2 + 20 && Gdx.input.getY() > SCREEN_HEIGHT/2 - 35){
			hoverButton2 = true;
		}else{
			hoverButton2 = false;
		}
	}
	
	//Loading all of the textures in the 'assets' folder.
	public void LoadTexture(){
		Background = new Texture("assets/Background.png");
	}
	
	//Loading and flipping the sprites.
	public void LoadSprite(){
		sprite_Background = new Sprite(Background);
	
		sprite_Background.flip(false, false);
	}
	
	public void SetupFont(){
		font = new BitmapFont(Gdx.files.internal("assets/Font/MyFont.fnt"));
	}

	public void dispose() {
		shapeRenderer.dispose();
		shapeRenderer2.dispose();
		Background.dispose();
	}

	public void hide() {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void render(float arg0) {
		// TODO Auto-generated method stub
		
	}

	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public void resume() {
		// TODO Auto-generated method stub
		
	}

	public void show() {
		// TODO Auto-generated method stub
		
	}
}