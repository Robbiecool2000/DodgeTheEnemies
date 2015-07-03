package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level8 extends Game implements Screen{
	
	//Texture
	Texture Character;
	
	//Sprites
	Sprite sprite_character;
	
	private SpriteBatch batch;
	
	//CharX-Y
	int CharX = 360;
	int CharY = 160;
	
	private OrthographicCamera camera;
	Main main;
	
	public Level8(Main main) {
		this.main = main;
		LoadTexture();
		LoadSprite();
	}
	public void create(){
		
	}
	public void dispose() {
		
	}
	public void hide() {
		
	}
	public void pause() {
		
	}
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		CharControls();
		CharBoundaries();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(sprite_character, CharX, CharY);
		batch.end();
	}
	public void resize(int arg0, int arg1) {
		
	}
	public void resume() {
		
	}
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 800, 400);
		camera.update();
	}
	
	public void LoadTexture(){
		Character = new Texture("assets/DodgeTheEnemiesCharacter.png");
	}
	
	public void LoadSprite(){
		sprite_character = new Sprite(Character);
		
		sprite_character.flip(false, true);
	}
	
	public void CharControls(){
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)){
			CharY -= 5;
		}
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){
			CharY += 5;
		}
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)){
			CharX -= 5;
		}
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			CharX += 5;
		}
	}
	
	public void CharBoundaries(){
		if(CharX >= 720){
			CharX = 720;
		}
		if(CharX <= 0){
			CharX = 0;
		}
		if(CharY >= 320){
			CharY = 320;
		}
		if(CharY <= 0){
			CharY = 0;
		}
	}
}