package com.FrobPlugins.me;

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

public class MainMenu implements Screen {
	public static SpriteBatch batch;
	Rectangle rect;
	ShapeRenderer shapeRenderer;
	ShapeRenderer shapeRenderer2;
	public static OrthographicCamera camera;
	public static BitmapFont font;
	Main game;
	
	public LevelScreen levelscreen;
	
	public static boolean hoverButton1 = false;
	public static boolean hoverButton2 = false;
	
	//Textures
	Texture Background;

	//Sprites
	Sprite sprite_Background;
	
	public MainMenu(Main main){
		this.game = main;
		System.out.println("hi");
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
	}
	
	public void dispose() {
		shapeRenderer.dispose();
		shapeRenderer2.dispose();
		Background.dispose();
		
	}

	public void hide() {
		System.out.println("boe");
		
	}

	public void pause() {
		System.out.println("boe");
		
	}
	
	public void Hover(){
		if(Gdx.input.getX() > Main.SCREEN_WIDTH/2 - 60 && Gdx.input.getX() < Main.SCREEN_WIDTH/2 + 60
        		&& Gdx.input.getY() < Main.SCREEN_HEIGHT/2 - 40 && Gdx.input.getY() > Main.SCREEN_HEIGHT/2 - 95){
			if(Gdx.input.justTouched()){
				
			}
        	hoverButton1 = true;
    	}else{
    		hoverButton1 = false;
    	}
		
		if(Gdx.input.getX() > Main.SCREEN_WIDTH/2 - 60 && Gdx.input.getX() < Main.SCREEN_WIDTH/2 + 60
				&& Gdx.input.getY() < Main.SCREEN_HEIGHT/2 + 20 && Gdx.input.getY() > Main.SCREEN_HEIGHT/2 - 35){
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

	public void resize(int arg0, int arg1) {
		
	}

	public void resume() {
		
	}

	public void show() {
		
	}

	public void render(float deltaTime) {
			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			camera.update();
			batch.begin();
				batch.draw(sprite_Background, 0, 0);
				font.draw(batch, "Play", Main.SCREEN_WIDTH/2 - 25, Main.SCREEN_HEIGHT/2 + 80);
				font.draw(batch, "Options", Main.SCREEN_WIDTH/2 - 45, Main.SCREEN_HEIGHT/2 + 20);
				font.draw(batch, "X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY(), 100, 100);
			batch.end();
			Gdx.gl.glEnable(GL20.GL_BLEND);
		    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		    
			shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(new Color((float) 0.3,(float) 0.3,(float) 0.3, 0.5f));
				shapeRenderer.rect(Main.SCREEN_WIDTH/2 - 100, Main.SCREEN_HEIGHT/2 + 100, 200, -300);
				
				if(!hoverButton1){ shapeRenderer.setColor(new Color((float) 0.3,(float) 0.3,(float) 0.3, 0.5f)); }
				if(hoverButton1){ shapeRenderer.setColor(new Color((float) 1,(float) 1,(float) 1, 0.5f)); }
				shapeRenderer.rect(Main.SCREEN_WIDTH/2 - 60, Main.SCREEN_HEIGHT/2 + 95, 120, -55);
	        shapeRenderer.end();
	       	shapeRenderer2.begin(ShapeType.Filled);
	        	if(!hoverButton2){ shapeRenderer2.setColor(new Color((float) 0.3,(float) 0.3,(float) 0.3, 0.5f)); }
	        	if(hoverButton2){ shapeRenderer2.setColor(new Color((float) 1,(float) 1,(float) 1, 0.5f)); }
	        	shapeRenderer2.rect(Main.SCREEN_WIDTH/2 - 60, Main.SCREEN_HEIGHT/2 + 35, 120, -55);
	       	shapeRenderer2.end();
	        Gdx.gl.glDisable(GL20.GL_BLEND);
	        Hover();
	        System.out.println("gelezen");
	}
}