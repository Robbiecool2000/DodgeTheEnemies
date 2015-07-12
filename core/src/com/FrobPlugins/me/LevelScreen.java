package com.FrobPlugins.me;

import com.FrobPlugins.me.Actor.PlayButton;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelScreen extends Game implements Screen {
	
	private SpriteBatch batch;
	PlayButton playbutton;
	int numrows = 8;
	int numcols = 3;
	int x;
	int y;
	int rowheight = 85;
	int colwidth = 85;
	public BitmapFont font;
	private Texture redButton;
	private Sprite sprite_redButton;
	Main main;
	private Texture Background;
	private Sprite sprite_Background;
	boolean changeCamera = false;
	
	private OrthographicCamera camera;

	public LevelScreen(Main main) {
		this.main = main;
	}

	public void create(){
		
	}
	
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(sprite_Background, 0, 0);
			for(x = 0; x < numrows; x++){
				for(y = 0; y < numcols; y++){
					int xPos = x * colwidth;
					int yPos = y * rowheight;
					batch.draw(sprite_redButton, xPos + 65, yPos + 75);
				}
			}
			for(x = 0; x < numrows; x++){
				for(y = 0; y < numcols; y++){
					int xPos = x * colwidth;
					int yPos = y * rowheight;
					batch.draw(sprite_redButton, xPos + 885, yPos + 75);
				}
			}
			batch.draw(sprite_redButton, 725, 0);
			font.draw(batch, "1", 0 + (float)97.5, 0 + (float)292.5);
			font.draw(batch, "2", 0 + (float)182.5, 0 + (float)292.5);
			font.draw(batch, "3", 0 + (float)267.5, 0 + (float)292.5);
			font.draw(batch, "4", 0 + (float)352.5, 0 + (float)292.5);
			font.draw(batch, "5", 0 + (float)437.5, 0 + (float)292.5);
			font.draw(batch, "6", 0 + (float)522.5, 0 + (float)292.5);
			font.draw(batch, "7", 0 + (float)607.5, 0 + (float)292.5);
			font.draw(batch, "8", 0 + (float)692.5, 0 + (float)292.5);
			font.draw(batch, "9", 0 + (float)97.5, 0 + (float)207.5);
			font.draw(batch, "10", 0 + 175, 0 + (float)207.5);
			font.draw(batch, "11", 0 + 260, 0 + (float)207.5);
			font.draw(batch, "12", 0 + 345, 0 + (float)207.5);
			font.draw(batch, "13", 0 + 430, 0 + (float)207.5);
			font.draw(batch, "14", 0 + 515, 0 + (float)207.5);
			font.draw(batch, "15", 0 + 600, 0 + (float)207.5);
			font.draw(batch, "16", 0 + 685, 0 + (float)207.5);
			font.draw(batch, "17", 0 + 90, 0 + (float)122.5);
			font.draw(batch, "18", 0 + 175, 0 + (float)122.5);
			font.draw(batch, "19", 0 + 260, 0 + (float)122.5);
			font.draw(batch, "20", 0 + 345, 0 + (float)122.5);
			font.draw(batch, "21", 0 + 430, 0 + (float)122.5);
			font.draw(batch, "22", 0 + 515, 0 + (float)122.5);
			font.draw(batch, "23", 0 + 600, 0 + (float)122.5);
			font.draw(batch, "24", 0 + 685, 0 + (float)122.5);
			font.draw(batch, ">", (float)760, (float)45);
		batch.end();
		onClickEvent();
		if(changeCamera){
			if(camera.position.x >= 1200){
				camera.position.x += 0;
				camera.position.x = 1200;
				changeCamera = false;
			}
			camera.position.x += 20;
			System.out.println(camera.position.x);
		}
	}
	
	public void LoadTexture(){
		
	}
	
	public void LoadSprite(){
		
	}
	
	public void onClickEvent(){
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 725 && Gdx.input.getX() < 725 + 75
					&& Gdx.input.getY() > 325 && Gdx.input.getY() < 325 + 75){
				camera.translate(0, 0);
				changeCamera = true;
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 && Gdx.input.getX() < 140
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level1(main));
				dispose();
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 85 && Gdx.input.getX() < 140 + 85
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level2(main));
				dispose();
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 170 && Gdx.input.getX() < 140 + 170
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level3(main));
				dispose();
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 255 && Gdx.input.getX() < 140 + 255
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level4(main));
				dispose();
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 340 && Gdx.input.getX() < 140 + 340
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level5(main));
				dispose();
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 425 && Gdx.input.getX() < 140 + 425
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level6(main));
				dispose();
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 510 && Gdx.input.getX() < 140 + 510
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level7(main));
				dispose();
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 595 && Gdx.input.getX() < 140 + 595
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level8(main));
				dispose();
			}
		}
	}
	
	public void loadTextures(){
		redButton = new Texture(Gdx.files.internal("assets/Red_Button.png"));
		Background = new Texture("assets/Background.png");
	}
	
	public void loadSprites(){
		sprite_redButton = new Sprite(redButton);
		sprite_Background = new Sprite(Background);
		
		sprite_redButton.flip(false, false);
		sprite_Background.flip(false, false);
	}
	
	public void SetupFont(){
		font = new BitmapFont(Gdx.files.internal("assets/Font/MyFont.fnt"));
		System.out.println("Loaded font(s)");
	}
	
	public void dispose() {
		
	}

	public void hide() {
		
	}

	public void pause() {
		
	}

	public void resize(int arg0, int arg1) {
		
	}

	public void resume() {
		
	}

	public void show() {
		batch = new SpriteBatch();
		playbutton = new PlayButton();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		loadTextures();
		loadSprites();
		SetupFont();
	}
}