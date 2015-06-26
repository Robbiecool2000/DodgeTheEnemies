package com.FrobPlugins.me;

import com.FrobPlugins.me.Actor.PlayButton;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelScreen extends Game implements Screen {
	
	public static SpriteBatch batch;
	PlayButton playbutton;
	int numrows = 12;
	int numcols = 7;
	int x;
	int y;
	int rowheight = 55;
	int colwidth = 55;
	
	public static OrthographicCamera camera;
	
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
					playbutton.drawPlay(batch, xPos, yPos, 50, 50);
				}
			}
		batch.end();
		System.out.println("DEBUG");
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
		camera.setToOrtho(true, 800, 400);
		camera.update();
	}
}