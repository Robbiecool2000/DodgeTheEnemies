package com.FrobPlugins.me;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Rectangle rect;
	ShapeRenderer shapeRenderer;
	OrthographicCamera camera;
	
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
	}

	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.begin();
			batch.draw(sprite_Background, 0, 0);
		batch.end();
		/* Adding a shape without textures.
		 * 
		 * shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.circle(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 150);
        shapeRenderer.end();*/
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
}
