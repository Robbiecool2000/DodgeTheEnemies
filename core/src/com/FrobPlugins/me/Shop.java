package com.FrobPlugins.me;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shop implements Screen{
	
	//Textures
	Texture ShopBackground;
	
	//Sprites
	Sprite sprite_shopbackground;
	
	Main main;
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	public Shop(Main main){
		this.main = main;
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
			batch.draw(sprite_shopbackground, 0, 0);
		batch.end();
	}

	public void resize(int arg0, int arg1) {
		
		
	}

	public void resume() {
		
		
	}

	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
		camera.update();
		LoadTexture();
		LoadSprite();
	}
	public void LoadTexture(){
		ShopBackground = new Texture("assets/Background.png");
	}
	public void LoadSprite(){
		sprite_shopbackground = new Sprite(ShopBackground);
	}
}
