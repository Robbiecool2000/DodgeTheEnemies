package com.FrobPlugins.me;

import com.FrobPlugins.me.Actor.PlayButton;
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

public class LevelScreen extends Game implements Screen {
	
	public static SpriteBatch batch;
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
	int X = 1;
	int Y = 2;
	Main main;
	
	public static OrthographicCamera camera;
	
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
			for(x = 0; x < numrows; x++){
				for(y = 0; y < numcols; y++){
					int xPos = x * colwidth;
					int yPos = y * rowheight;
					batch.draw(sprite_redButton, xPos + 65, yPos + 75);
				}
			}
			font.draw(batch, "1", 0 + (float)97.5, 0 + (float)292.5);
			font.draw(batch, "2", 0 + (float)182.5, 0 + (float)292.5);
			font.draw(batch, "3", 0 + (float)267.5, 0 + (float)292.5);
			font.draw(batch, "4", 0 + (float)352.5, 0 + (float)292.5);
			font.draw(batch, "5", 0 + (float)437.5, 0 + (float)292.5);
			font.draw(batch, "6", 0 + (float)522.5, 0 + (float)292.5);
			font.draw(batch, "7", 0 + (float)607.5, 0 + (float)292.5);
			font.draw(batch, "8", 0 + (float)692.5, 0 + (float)292.5);
			font.draw(batch, "9", 0 + 95, 0 + (float)207.5);
			font.draw(batch, "10", 0 + 175, 0 + (float)207.5);
			font.draw(batch, "11", 0 + 260, 0 + (float)207.5);
			font.draw(batch, "12", 0 + 345, 0 + (float)207.5);
			font.draw(batch, "13", 0 + 430, 0 + (float)207.5);
			font.draw(batch, "14", 0 + 515, 0 + (float)207.5);
			font.draw(batch, "15", 0 + 600, 0 + (float)207.5);
			font.draw(batch, "16", 0 + 685, 0 + (float)207.5);
			font.draw(batch, "17", 0 + 95, 0 + (float)122.5);
			font.draw(batch, "18", 0 + 180, 0 + (float)122.5);
			font.draw(batch, "19", 0 + 265, 0 + (float)122.5);
			font.draw(batch, "20", 0 + 350, 0 + (float)122.5);
			font.draw(batch, "21", 0 + 435, 0 + (float)122.5);
			font.draw(batch, "22", 0 + 520, 0 + (float)122.5);
			font.draw(batch, "23", 0 + 605, 0 + (float)122.5);
			font.draw(batch, "24", 0 + 690, 0 + (float)122.5);
		batch.end();
		onClickEvent();
	}
	
	public void onClickEvent(){
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 && Gdx.input.getX() < 140
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				main.setScreen(main.level1);
				dispose();
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 85 && Gdx.input.getX() < 140 + 85
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				System.out.println("MAAK GVD LEVEL 2, NU!");
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 170 && Gdx.input.getX() < 140 + 170
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				System.out.println("MAAK GVD LEVEL 3, NU!");
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 255 && Gdx.input.getX() < 140 + 255
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				System.out.println("MAAK GVD LEVEL 4, NU!");
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 340 && Gdx.input.getX() < 140 + 340
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				System.out.println("MAAK GVD LEVEL 5, NU!");
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 425 && Gdx.input.getX() < 140 + 425
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				System.out.println("MAAK GVD LEVEL 6, NU!");
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 510 && Gdx.input.getX() < 140 + 510
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				System.out.println("MAAK GVD LEVEL 7, NU!");
			}
		}
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 65 + 595 && Gdx.input.getX() < 140 + 595
					&& Gdx.input.getY() > 75 && Gdx.input.getY() < 150){
				System.out.println("MAAK GVD LEVEL 8, NU!");
			}
		}
	}
	
	public void loadTextures(){
		redButton = new Texture(Gdx.files.internal("assets/Red_Button.png"));
	}
	
	public void loadSprites(){
		sprite_redButton = new Sprite(redButton);
	}
	
	public void SetupFont(){
		font = new BitmapFont(Gdx.files.internal("assets/Font/MyFont.fnt"));
		System.out.println("Loaded font(s)");
	}
	
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void hide() {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public void resume() {
		// TODO Auto-generated method stub
		
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