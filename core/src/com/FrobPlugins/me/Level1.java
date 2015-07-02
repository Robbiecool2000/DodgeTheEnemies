package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level1 extends Game implements Screen{
	
	//Texture
	Texture Character;
	
	//Sprites
	Sprite sprite_character;
	
	private SpriteBatch batch;
	
	private OrthographicCamera camera;
	Main main;
	
	public Level1(Main main) {
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
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(sprite_character, 360, 160);
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
}