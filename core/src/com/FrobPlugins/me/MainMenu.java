package com.FrobPlugins.me;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainMenu implements Screen {
	public static OrthographicCamera camera;
	public static BitmapFont font;
	Main game;
	private Stage stage = new Stage();
	
	//Textures
	Texture Background;
	Texture PlayButton;

	//Images
	Image sprite_Background;
	Image play_button;
	
	public MainMenu(Main main){
		this.game = main;
		LoadTexture();
		LoadSprite();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		camera.update();
		SetupFont();
	}
	
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		stage.act();
		stage.draw();
		Main.batch.begin();

		Main.batch.end();
	}
	
	public void dispose() {
		font.dispose();
	}
	
	public void hide() {
		dispose();
	}

	public void pause() {
		
	}
	
	//Loading all of the textures in the 'assets' folder.
	public void LoadTexture(){
		Background = new Texture("Background.png");
		PlayButton = new Texture("Playbutton.png");
	}
	
	//Loading and flipping the sprites.
	public void LoadSprite(){
		sprite_Background = new Image(Background);
		play_button = new Image(PlayButton);
	}
	
	public void SetupFont(){
		font = new BitmapFont(Gdx.files.internal("Font/MyFont.fnt"));
	}

	public void resize(int arg0, int arg1) {

	}

	public void resume() {
		
	}

	public void show() {
		stage.addActor(sprite_Background);
		sprite_Background.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
		stage.addActor(play_button);
		play_button.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f)));
	}
}