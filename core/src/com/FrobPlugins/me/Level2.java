package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Level2 extends Game implements Screen{
	
	//Texture
	Texture Character;
	Texture Level2Background;
	
	//Sprites
	Sprite sprite_character;
	Sprite sprite_level2background;
	
	//Font
	public static BitmapFont font;
	
	private SpriteBatch batch;
	
	//(Evil)CharBounds
	private final Rectangle CharBounds;
	
	private OrthographicCamera camera;
	Main main;
	
	public Level2(Main main) {
		this.main = main;
		LoadTexture();
		LoadSprite();
		CharBounds = new Rectangle(360, 160, 60, 60);
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
		Setupfont();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(sprite_character, CharBounds.x, CharBounds.y);
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
	}
	
	public void LoadTexture(){
		Character = new Texture("assets/DodgeTheEnemiesCharacter.png");
		Level2Background = new Texture("assets/LevelBackground.png");
	}
	
	public void LoadSprite(){
		sprite_character = new Sprite(Character);
		
	}
	
	public void CharControls(){
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)){
			CharBounds.y -= 2;
		}
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){
			CharBounds.y += 2;
		}
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)){
			CharBounds.x -= 2;
		}
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			CharBounds.x += 2;
		}
	}
	
	public void CharBoundaries(){
		if(CharBounds.x >= 720){
			CharBounds.x = 720;
		}
		if(CharBounds.x <= 0){
			CharBounds.x = 0;
		}
		if(CharBounds.y >= 320){
			CharBounds.y = 320;
		}
		if(CharBounds.y <= 0){
			CharBounds.y = 0;
		}
	}
	public void Setupfont(){
		font = new BitmapFont(Gdx.files.internal("assets/Font/MyFont.fnt"));
	}
}