package com.FrobPlugins.me;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Rectangle rect;
	ShapeRenderer shapeRenderer;
	OrthographicCamera camera;
	BitmapFont font;
	
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
		SetupFont();
	}

	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.begin();
			batch.draw(sprite_Background, 0, 0);
			font.draw(batch, "Play", SCREEN_WIDTH/2 - 10, SCREEN_HEIGHT/2);
		batch.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(new Color((float) 0.3,(float) 0.3,(float) 0.3, 0.5f));
			shapeRenderer.rect(SCREEN_WIDTH/2 - 100, SCREEN_HEIGHT/2 + 100, 200, -300);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
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
		font = new BitmapFont(Gdx.files.internal("assets/Font"));
	}
}
