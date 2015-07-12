package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainMenu extends Game implements Screen {
	private SpriteBatch batch;
	public static OrthographicCamera camera;
	public static BitmapFont font;
	Main game;
	boolean hover = true;
	ShapeRenderer shapeRenderer, shapeRenderer2;
	private Stage stage = new Stage();
	
	public static int FPS;
	
	public static boolean hoverButton1 = false;
	public static boolean hoverButton2 = false;
	
	//Textures
	Texture Background;

	//Images
	Image sprite_Background;
	
	public MainMenu(Main main){
		this.game = main;
		batch = new SpriteBatch();
		LoadTexture();
		LoadSprite();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 800, 400);
		camera.update();
		SetupFont();
		levelscreen = new LevelScreen(main);
	}
	
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		stage.act();
		stage.draw();
		batch.begin();
			font.draw(batch, "Play", Main.SCREEN_WIDTH/2 - 25, Main.SCREEN_HEIGHT/2 + 80);
			font.draw(batch, "Options", Main.SCREEN_WIDTH/2 - 45, Main.SCREEN_HEIGHT/2 + 20);
			font.draw(batch, "X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY(), 100, 100);
		batch.end();
        if(hover){
        	Hover();
        }
	}
	
	public void dispose() {
	}

	public void hide() {
	}

	public void pause() {
		
	}
	
	public void Hover(){
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > Main.SCREEN_WIDTH/2 - 60 && Gdx.input.getX() < Main.SCREEN_WIDTH/2 + 60
        		&& Gdx.input.getY() < Main.SCREEN_HEIGHT/2 - 40 && Gdx.input.getY() > Main.SCREEN_HEIGHT/2 - 95){
				dispose();
				((Game)Gdx.app.getApplicationListener()).setScreen(new LevelScreen(game));
			}
		}
	}
	
	//Loading all of the textures in the 'assets' folder.
	public void LoadTexture(){
		Background = new Texture("assets/Background.png");
	}
	
	//Loading and flipping the sprites.
	public void LoadSprite(){
		sprite_Background = new Image(Background);
	}
	
	public void SetupFont(){
		font = new BitmapFont(Gdx.files.internal("assets/Font/MyFont.fnt"));
	}

	public void resize(int arg0, int arg1) {
		
	}

	public void resume() {
		
	}

	public void show() {
		stage.addActor(sprite_Background);
		sprite_Background.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f)));
	}

	public void create() {
		// TODO Auto-generated method stub
		
	}
}