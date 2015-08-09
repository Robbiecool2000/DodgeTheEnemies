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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Level2 extends Game implements Screen{
	
	//Texture
	Texture Character;
	Texture Level2Background;
	Texture EvilCharacter;
	
	//Sprites
	Sprite sprite_character;
	Sprite sprite_level2background;
	Sprite sprite_evilcharacter;
	
	private Stage stage = new Stage();
	private Image died_image = new Image(new Texture(Gdx.files.internal("assets/Died.png")));
	
	private boolean died = false;
	
	//Font
	public static BitmapFont font;
	
	private SpriteBatch batch;
	
	//(Evil)CharBounds
	private Rectangle CharBounds;
	private Rectangle EvilCharBounds_1;
	private Rectangle EvilCharBounds_2;
	
	int EvilCharAtTop_1 = 0;
	int EvilCharAtTop_2 = 0;
	
	private OrthographicCamera camera;
	Main main;
	
	public Level2(Main main) {
		this.main = main;
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
		Setupfont();
		EvilCharIntSystem();
		EvilCharMovement();
		EvilCharBackMovement();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(sprite_level2background, 0, 0);
			batch.draw(sprite_character, CharBounds.x, CharBounds.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_1.x, EvilCharBounds_1.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_2.x, EvilCharBounds_2.y);
		batch.end();
		
		if(died){
			stage.act();
			stage.draw();
			ClickListener();
		}else{
			Collide();
			CharControls();
			CharBoundaries();
		}
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
		CharBounds = new Rectangle(360, 160, 60, 60);
		EvilCharBounds_1 = new Rectangle(0, 0, 80, 80);
		EvilCharBounds_2 = new Rectangle(720, 0, 80, 80);
		stage.addActor(died_image);
		died_image.setX(Main.SCREEN_WIDTH/2 - 350/2);
		died_image.setY(Main.SCREEN_HEIGHT/2 - 350/2);
		died_image.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
	}
	
	public void ClickListener(){
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 225 && Gdx.input.getX() < 400 && Gdx.input.getY() > 300 && Gdx.input.getY() < 375){
				died = false;
				CharBounds = new Rectangle(360, 160, 60, 60);
				EvilCharBounds_1 = new Rectangle(0, 0, 80, 80);
				EvilCharBounds_2 = new Rectangle(720, 0, 80, 80);
			}
			if(Gdx.input.getX() > 400 && Gdx.input.getX() < 575 && Gdx.input.getY() > 300 && Gdx.input.getY() < 375){
				died = false;
				((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen(main));
			}
		}
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
			died = true;
		}
		if(CharBounds.overlaps(EvilCharBounds_2)){
			died = true;
		}
	}
}