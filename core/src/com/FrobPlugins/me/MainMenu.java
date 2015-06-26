package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainMenu extends Game implements Screen {
	public static SpriteBatch batch;
	public static OrthographicCamera camera;
	public static BitmapFont font;
	Main game;
	private Game game_class;
	ShapeRenderer shapeRenderer, shapeRenderer2;
	
	public static int FPS;
	
	public static boolean hoverButton1 = false;
	public static boolean hoverButton2 = false;
	
	//Textures
	Texture Background;

	//Sprites
	Sprite sprite_Background;
	
	public MainMenu(Main main){
		this.game = main;
		batch = new SpriteBatch();
		LoadTexture();
		LoadSprite();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 800, 400);
		camera.update();
		SetupFont();
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
       	
       	
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Hover(game_class);
        
        this.FPS = Gdx.graphics.getFramesPerSecond();
	}
	
	public void dispose() {
		super.dispose();
		batch.dispose();
	}

	public void hide() {
		
	}

	public void pause() {
		
	}
	
	public void Hover(Game game){
		if(Gdx.input.getX() > Main.SCREEN_WIDTH/2 - 60 && Gdx.input.getX() < Main.SCREEN_WIDTH/2 + 60
        		&& Gdx.input.getY() < Main.SCREEN_HEIGHT/2 - 40 && Gdx.input.getY() > Main.SCREEN_HEIGHT/2 - 95){
			if(Gdx.input.justTouched()){
				setScreen(new LevelScreen());
				dispose();
			}
    	}else{
    		
    	}
		
		if(Gdx.input.getX() > Main.SCREEN_WIDTH/2 - 60 && Gdx.input.getX() < Main.SCREEN_WIDTH/2 + 60
				&& Gdx.input.getY() < Main.SCREEN_HEIGHT/2 + 20 && Gdx.input.getY() > Main.SCREEN_HEIGHT/2 - 35){
			
		}else{
			
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

	public void create() {
		// TODO Auto-generated method stub
		
	}
}