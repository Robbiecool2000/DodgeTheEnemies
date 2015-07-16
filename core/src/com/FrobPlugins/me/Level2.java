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
	Texture EvilCharacter;
	
	//Sprites
	Sprite sprite_character;
	Sprite sprite_level2background;
	Sprite sprite_evilcharacter;
	
	//Font
	public static BitmapFont font;
	
	private SpriteBatch batch;
	
	//(Evil)CharBounds
	private final Rectangle CharBounds;
	private final Rectangle EvilCharBounds_1;
	private final Rectangle EvilCharBounds_2;
	
	int EvilCharAtTop_1 = 0;
	int EvilCharAtTop_2 = 0;
	
	private OrthographicCamera camera;
	Main main;
	
	public Level2(Main main) {
		this.main = main;
		LoadTexture();
		LoadSprite();
		CharBounds = new Rectangle(360, 160, 60, 60);
		EvilCharBounds_1 = new Rectangle(0, 0, 80, 80);
		EvilCharBounds_2 = new Rectangle(720, 0, 80, 80);
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
		EvilCharIntSystem();
		EvilCharMovement();
		EvilCharBackMovement();
		Collide();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(sprite_level2background, 0, 0);
			batch.draw(sprite_character, CharBounds.x, CharBounds.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_1.x, EvilCharBounds_1.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_2.x, EvilCharBounds_2.y);
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
		EvilCharacter = new Texture("assets/DodgeTheEnemiesEvilCharacter.png");
	}
	
	public void LoadSprite(){
		sprite_character = new Sprite(Character);
		sprite_level2background = new Sprite(Level2Background);
		sprite_evilcharacter = new Sprite(EvilCharacter);
	}
	
	public void CharControls(){
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)){
			CharBounds.y += 2;
		}
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){
			CharBounds.y -= 2;
		}
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)){
			CharBounds.x -= 2;
		}
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			CharBounds.x += 2;
		}
	}
	
	public void CharBoundaries(){
		if(CharBounds.x >= 740){
			CharBounds.x = 740;
		}
		if(CharBounds.x <= 0){
			CharBounds.x = 0;
		}
		if(CharBounds.y >= 340){
			CharBounds.y = 340;
		}
		if(CharBounds.y <= 0){
			CharBounds.y = 0;
		}
	}
	public void Setupfont(){
		font = new BitmapFont(Gdx.files.internal("assets/Font/MyFont.fnt"));
	}
	public void EvilCharIntSystem(){
		if(EvilCharBounds_1.y == 0){
			EvilCharAtTop_1 = 0;
		}
		if(EvilCharBounds_1.y == 320){
			EvilCharAtTop_1 = 1;
		}
		
		if(EvilCharBounds_2.y == 0){
			EvilCharAtTop_2 = 0;
		}
		if(EvilCharBounds_2.y == 320){
			EvilCharAtTop_2 = 1;
		}
	}
	public void EvilCharMovement(){
		if(EvilCharAtTop_1 == 0){
			EvilCharBounds_1.y += 1;
			EvilCharBounds_1.x += 2.25;
		}
		
		if(EvilCharAtTop_2 == 0){
			EvilCharBounds_2.y += 1;
			EvilCharBounds_2.x -= 2.25;
		}
	}
	public void EvilCharBackMovement(){
		if(EvilCharAtTop_1 == 1){
			EvilCharBounds_1.y -= 1;
			EvilCharBounds_1.x -= 2.25;
		}
		if(EvilCharAtTop_2 == 1){
			EvilCharBounds_2.y -= 1;
			EvilCharBounds_2.x += 2.25;
		}
	}
	public void Collide(){
		if(CharBounds.overlaps(EvilCharBounds_1)){
			System.out.println("You died!");
		}
		if(CharBounds.overlaps(EvilCharBounds_2)){
			System.out.println("You died!");
		}
	}
}