package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.utils.TimeUtils;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class Level1 implements Screen{
	
	//Texture
	Texture Character;
	Texture EvilCharacter;
	Texture Level1Background;
	Texture died_texture = new Texture("assets/Died.png");
	//Sprites
	Sprite sprite_character;
	Sprite sprite_evilcharacter;
	Sprite sprite_level1background;
	
	WarpClient warpClient;
	
	//Images
	Image died_window = new Image(died_texture);
	private Stage stage = new Stage();
	
	//Font
	public static BitmapFont font;
	
	private SpriteBatch batch;
	
	int EvilCharAtTop_1 = 1;
	int EvilCharAtTop_2 = 1;
	int EvilCharAtTop_3 = 1;
	int EvilCharAtTop_4 = 1;
	
	int EvilCharAtTop_5 = 0;
	int EvilCharAtTop_6 = 0;
	int EvilCharAtTop_7 = 0;
	int EvilCharAtTop_8 = 0;
	
	//(Evil)CharBounds
	private Rectangle CharBounds;
	private Rectangle EvilCharBounds_1;
	private Rectangle EvilCharBounds_2;
	private Rectangle EvilCharBounds_3;
	private Rectangle EvilCharBounds_4;
	private Rectangle EvilCharBounds_5;
	private Rectangle EvilCharBounds_6;
	private Rectangle EvilCharBounds_7;
	private Rectangle EvilCharBounds_8;
	
	private boolean died = false;
	private boolean Disabled = false;
	private OrthographicCamera camera;
	Main main;
	
	private long start;
    private long secsToWait = 3;
    private long displaytime;
	
	public Level1(Main main) {
		this.main = main;
	}
	public void create(){
		
	}
	public void dispose() {
		
	}
	public void hide() {
		stage.dispose();
	}
	public void pause() {
		
	}
	
    public void start()
    {
        start = TimeUtils.millis() / 1000;
    }

    public boolean hasCompleted()
    {
        return TimeUtils.millis() / 1000 - start >= secsToWait;
    }
    
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		CharControls();
		CharBoundaries();
		EvilCharIntSystem();
		EvilCharMovement();
		EvilCharBackMovement();
		CharFinish();
		Collide();
		Setupfont();
		Timer();
		batch.setProjectionMatrix(camera.combined);
		System.out.println(TimeUtils.millis() / 1000 - start);
		batch.begin();
			batch.draw(sprite_level1background, 0, 0);
			batch.draw(sprite_character, CharBounds.x, CharBounds.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_1.x, EvilCharBounds_1.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_2.x, EvilCharBounds_2.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_3.x, EvilCharBounds_3.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_4.x, EvilCharBounds_4.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_5.x, EvilCharBounds_5.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_6.x, EvilCharBounds_6.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_7.x, EvilCharBounds_7.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_8.x, EvilCharBounds_8.y);
			if(!hasCompleted()){
				font.draw(batch, "Hit the left side of the screen to finish the level.", Main.SCREEN_WIDTH/6, 400);
				font.draw(batch, "Starting in: " + displaytime, Main.SCREEN_WIDTH/3, 100);
			}
		batch.end();
		if(died){
			stage.act();
			stage.draw();
			Disabled = true;
			ClickListener();
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
		Setupfont();
		LoadTexture();
		LoadSprite();
		CharBounds = new Rectangle(360, 160, 60, 60);
		EvilCharBounds_1 = new Rectangle(0, 0, 80, 80);
		EvilCharBounds_2 = new Rectangle(160, 0, 80, 80);
		EvilCharBounds_3 = new Rectangle(320, 0, 80, 80);
		EvilCharBounds_4 = new Rectangle(480, 0, 80, 80);
		EvilCharBounds_5 = new Rectangle(80, 320, 80, 80);
		EvilCharBounds_6 = new Rectangle(240, 320, 80, 80);
		EvilCharBounds_7 = new Rectangle(400, 320, 80, 80);
		EvilCharBounds_8 = new Rectangle(560, 320, 80, 80);
		stage.addActor(died_window);
		died_window.setX(Main.SCREEN_WIDTH/2 - 350/2);
		died_window.setY(Main.SCREEN_HEIGHT/2 - 350/2);
		died_window.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f)));
		Disabled = false;
		start();
	}
	
	public void ClickListener(){
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 225 && Gdx.input.getX() < 400 && Gdx.input.getY() > 300 && Gdx.input.getY() < 375){
				died = false;
				Disabled = false;
				CharBounds = new Rectangle(360, 160, 60, 60);
				EvilCharBounds_1 = new Rectangle(0, 0, 80, 80);
				EvilCharBounds_2 = new Rectangle(160, 0, 80, 80);
				EvilCharBounds_3 = new Rectangle(320, 0, 80, 80);
				EvilCharBounds_4 = new Rectangle(480, 0, 80, 80);
				EvilCharBounds_5 = new Rectangle(80, 320, 80, 80);
				EvilCharBounds_6 = new Rectangle(240, 320, 80, 80);
				EvilCharBounds_7 = new Rectangle(400, 320, 80, 80);
				EvilCharBounds_8 = new Rectangle(560, 320, 80, 80);
				start();
			}
			if(Gdx.input.getX() > 400 && Gdx.input.getX() < 575 && Gdx.input.getY() > 300 && Gdx.input.getY() < 375){
				died = false;
				Disabled = false;
				((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen(main));
			}
		}
	}
	
	public void LoadTexture(){
		Character = new Texture("assets/DodgeTheEnemiesCharacter.png");
		EvilCharacter = new Texture("assets/DodgeTheEnemiesEvilCharacter.png");
		Level1Background = new Texture("assets/LevelBackground.png");
	}
	
	public void LoadSprite(){
		sprite_character = new Sprite(Character);
		sprite_evilcharacter = new Sprite(EvilCharacter);
		sprite_level1background = new Sprite(Level1Background);
	}
	
	public void CharControls(){
		if(!Disabled){
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
	public void EvilCharIntSystem(){
		if(EvilCharBounds_1.y == 0){
			EvilCharAtTop_1 = 1;
		}
		if(EvilCharBounds_1.y == 320){
			EvilCharAtTop_1 = 0;
		}
		
		if(EvilCharBounds_2.y == 0){
			EvilCharAtTop_2 = 1;
		}
		if(EvilCharBounds_2.y == 320){
			EvilCharAtTop_2 = 0;
		}
		
		if(EvilCharBounds_3.y == 0){
			EvilCharAtTop_3 = 1;
		}
		if(EvilCharBounds_3.y == 320){
			EvilCharAtTop_3 = 0;
		}
		
		if(EvilCharBounds_4.y == 0){
			EvilCharAtTop_4 = 1;
		}
		if(EvilCharBounds_4.y == 320){
			EvilCharAtTop_4 = 0;
		}
		
		if(EvilCharBounds_5.y == 0){
			EvilCharAtTop_5 = 1;
		}
		if(EvilCharBounds_5.y == 320){
			EvilCharAtTop_5 = 0;
		}
		
		if(EvilCharBounds_6.y == 0){
			EvilCharAtTop_6 = 1;
		}
		if(EvilCharBounds_6.y == 320){
			EvilCharAtTop_6 = 0;
		}
		
		if(EvilCharBounds_7.y == 0){
			EvilCharAtTop_7 = 1;
		}
		if(EvilCharBounds_7.y == 320){
			EvilCharAtTop_7 = 0;
		}
		
		if(EvilCharBounds_8.y == 0){
			EvilCharAtTop_8 = 1;
		}
		if(EvilCharBounds_8.y == 320){
			EvilCharAtTop_8 = 0;
		}
	}
	public void EvilCharMovement(){
		if(EvilCharAtTop_1 == 1){
			EvilCharBounds_1.y += 1;
		}
		
		if(EvilCharAtTop_2 == 1){
			EvilCharBounds_2.y += 1;
		}
		
		if(EvilCharAtTop_3 == 1){
			EvilCharBounds_3.y += 1;
		}
		
		if(EvilCharAtTop_4 == 1){
			EvilCharBounds_4.y += 1;
		}
		
		
		if(EvilCharAtTop_5 == 0){
			EvilCharBounds_5.y -= 1;
		}
		
		if(EvilCharAtTop_6 == 0){
			EvilCharBounds_6.y -= 1;
		}
		
		if(EvilCharAtTop_7 == 0){
			EvilCharBounds_7.y -= 1;
		}
		
		if(EvilCharAtTop_8 == 0){
			EvilCharBounds_8.y -= 1;
		}
	}
	public void EvilCharBackMovement(){
		if(EvilCharAtTop_1 == 0){
			EvilCharBounds_1.y -= 1;
		}
		
		if(EvilCharAtTop_2 == 0){
			EvilCharBounds_2.y -= 1;
		}
		
		if(EvilCharAtTop_3 == 0){
			EvilCharBounds_3.y -= 1;
		}
		
		if(EvilCharAtTop_4 == 0){
			EvilCharBounds_4.y -= 1;
		}
		
		
		if(EvilCharAtTop_5 == 1){
			EvilCharBounds_5.y += 1;
		}
		
		if(EvilCharAtTop_6 == 1){
			EvilCharBounds_6.y += 1;
		}
		
		if(EvilCharAtTop_7 == 1){
			EvilCharBounds_7.y += 1;
		}
		
		if(EvilCharAtTop_8 == 1){
			EvilCharBounds_8.y += 1;
		}
	}
	public void CharFinish(){
		if(!Disabled){
			if(CharBounds.x == 0 && CharBounds.y >= 0 && CharBounds.y <= 400){
				if(CharBounds.x == 0 && CharBounds.y + 80 >= 0 && CharBounds.y + 80 <= 400){
					((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen(main));
				}
			}
		}
	}
	public void Collide(){
		if(!Disabled){
			if(CharBounds.overlaps(EvilCharBounds_1)){
				died = true;
			}
			if(CharBounds.overlaps(EvilCharBounds_2)){
				died = true;
			}
			if(CharBounds.overlaps(EvilCharBounds_3)){
				died = true;
			}
			if(CharBounds.overlaps(EvilCharBounds_4)){
				died = true;
			}
			if(CharBounds.overlaps(EvilCharBounds_5)){
				died = true;
			}
			if(CharBounds.overlaps(EvilCharBounds_6)){
				died = true;
			}
			if(CharBounds.overlaps(EvilCharBounds_7)){
				died = true;
			}
			if(CharBounds.overlaps(EvilCharBounds_8)){
				died = true;
			}
		}
	}
	public void Setupfont(){
		font = new BitmapFont(Gdx.files.internal("assets/data/arial-15.fnt"));
		font.setColor(Color.BLACK);
		
	}
	public void Timer(){
		if(TimeUtils.millis() / 1000 - start == 0){
			displaytime = 3;
		}
		if(TimeUtils.millis() / 1000 - start == 1){
			displaytime = 2;
		}
		if(TimeUtils.millis() / 1000 - start == 2){
			displaytime = 1;
		}
	}
}