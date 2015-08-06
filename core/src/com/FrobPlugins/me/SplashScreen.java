package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SplashScreen implements Screen {
	
	Main main;
	private Texture texture = new Texture(Gdx.files.internal("assets/DodgeTheEnemies_Splash.png"));
    private Image splashImage = new Image(texture);
    private Stage stage = new Stage();

	public void dispose() {
		texture.dispose();
		stage.dispose();
	}

	public void hide() {
		dispose();
	}

	public void pause() {
		
	}

	public void render(float arg0) {
		 Gdx.gl.glClearColor(1,1,1,1);
	     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	     stage.act();
	     stage.draw();
	}

	public void resize(int arg0, int arg1) {
		
	}

	public void resume() {
		
	}

	public void show() {
		stage.addActor(splashImage);
		
		splashImage.addAction(Actions.sequence(Actions.alpha(0)
                ,Actions.fadeIn(2f),Actions.delay(2),Actions.fadeOut(2f),Actions.run(new Runnable() {
                	public void run() {
                		((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu(main));
                	}
                })));
	}
}