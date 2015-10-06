package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SplashScreen implements Screen {
	
	Main main;
	public static Texture texture;
    public static Image splashImage;
    Stage stage;
	private OrthographicCamera camera;

	public SplashScreen(Main main){
		this.main = main;
	}

	public void dispose() {
		texture.dispose();
		stage.dispose();
	}

	public void hide() {
		dispose();
	}

	public void pause() {
		
	}

	public void render(float deltaTime) {
		 Gdx.gl.glClearColor(0, 0, 0, 1);
	     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 camera.update();
	     stage.act();
	     stage.draw();
	}

	public void resize(int arg0, int arg1) {
		
	}

	public void resume() {
		
	}

	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		camera.update();
		LoadTextures();
		LoadImages();
		LoadStage();
		stage.addActor(splashImage);
		
		splashImage.addAction(Actions.sequence(Actions.alpha(0)
                ,Actions.fadeIn(2f),Actions.delay(2),Actions.fadeOut(2f),Actions.run(new Runnable() {
                	public void run() {
                		((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu(main));
                	}
                })));
	}

	public void LoadTextures(){
		texture = new Texture("assets/DodgeTheEnemies_Splash.png");
	}

	public void LoadImages(){
		splashImage = new Image(texture);
	}

	public void LoadStage(){
		stage = new Stage();
	}
}