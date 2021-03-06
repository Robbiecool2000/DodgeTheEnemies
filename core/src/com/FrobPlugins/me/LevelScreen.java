package com.FrobPlugins.me;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class LevelScreen implements Screen{
	
	/*
	 * Things to do:
	 * 
	 * - Assets class for all the assets.
	 * - Fix sound system.
	 * - Make the timer system better.
	 * - Save coins in a file. //DONE
	 * - Adding coins when finished to the total coins. //DONE
	 * - Make a finish line
	 * - Make upgrades like: Being smaller or being faster (examples)
	 */
	
	//Texture
	Texture Character;
	Texture EvilCharacter;
	Texture Level1Background;
	Texture finished_texture;
	Texture died_texture;
	Texture Coin;
	Texture TextureFinish;
	Texture Star;
	Texture Cross;
	
	//Sprites
	Sprite sprite_character;
	Sprite sprite_evilcharacter;
	Sprite sprite_level1background;
	Sprite Coin_Image;
	Sprite sprite_finish;
	Sprite sprite_star;
	Sprite sprite_cross;
	
	private ParticleEffect effect;
	
	private Stage button_stage = new Stage();
	private TextureAtlas atlas;
	private TextButton back_button;
	private Skin skin;
	private Table table;
	
	//Images
	Image finished_window;
	Image died_window;
	Image star_image;
	Image cross_image;
	
	//Stages
	private Stage stagedied = new Stage();
	private Stage stagefinished = new Stage();
	
	//Font
	public static BitmapFont font;
	
	int EvilCharAtTop_1 = 1;
	int EvilCharAtTop_2 = 1;
	int EvilCharAtTop_3 = 1;
	int EvilCharAtTop_4 = 1;
	
	int EvilCharAtTop_5 = 0;
	int EvilCharAtTop_6 = 0;
	int EvilCharAtTop_7 = 0;
	int EvilCharAtTop_8 = 0;
	
	int CoinX1;
	int CoinY1;
	int CoinX2;
	int CoinY2;
	int CoinX3;
	int CoinY3;
	
	public static int coinCount;
	private int coinCountSession;
	
	private boolean CollectedCoins = false;
	
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
	private Rectangle Finish;
	
	private Rectangle CoinRect1;
	private Rectangle CoinRect2;
	private Rectangle CoinRect3;
	
	private boolean finished = false;
	private boolean drawstage2 = false;
	private boolean died = false;
	private boolean drawstage = false;
	private boolean Disabled = false;
	private OrthographicCamera camera;
	Main main;

	private long start;
    private long secsToWait = 4;
    private long displaytime;
    
    private long CoinsCollected;
	
	public LevelScreen(Main main) {
		this.main = main;
	}

	public void dispose() {
		
	}

	public void hide() {
		stagedied.dispose();
	}

	public void pause() {
		
	}

    public void start() {
        start = TimeUtils.millis() / 1000;
    }

    public boolean hasCompleted() {
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
		effect.setPosition(CharBounds.x +30, CharBounds.y +30);
		effect.update(deltaTime);
		
		long InputX = Gdx.input.getX();
		long InputY = Gdx.input.getY();
		
		Main.batch.begin();
			Main.batch.draw(sprite_level1background, 0, 0);
		Main.batch.end();
		button_stage.act();
		button_stage.draw();
		Main.batch.begin();
			if(!CollectedCoins){
				Main.batch.draw(Coin_Image, CoinRect1.x, CoinRect1.y);
				Main.batch.draw(Coin_Image, CoinRect2.x, CoinRect2.y);
				Main.batch.draw(Coin_Image, CoinRect3.x, CoinRect3.y);
			}
			effect.draw(Main.batch, deltaTime);
			Main.batch.draw(sprite_character, CharBounds.x, CharBounds.y);
			Main.batch.draw(sprite_evilcharacter, EvilCharBounds_1.x, EvilCharBounds_1.y);
			Main.batch.draw(sprite_evilcharacter, EvilCharBounds_2.x, EvilCharBounds_2.y);
			Main.batch.draw(sprite_evilcharacter, EvilCharBounds_3.x, EvilCharBounds_3.y);
			Main.batch.draw(sprite_evilcharacter, EvilCharBounds_4.x, EvilCharBounds_4.y);
			Main.batch.draw(sprite_evilcharacter, EvilCharBounds_5.x, EvilCharBounds_5.y);
			Main.batch.draw(sprite_evilcharacter, EvilCharBounds_6.x, EvilCharBounds_6.y);
			Main.batch.draw(sprite_evilcharacter, EvilCharBounds_7.x, EvilCharBounds_7.y);
			Main.batch.draw(sprite_evilcharacter, EvilCharBounds_8.x, EvilCharBounds_8.y);
			Main.batch.draw(sprite_finish, Finish.x, Finish.y);
			font.draw(Main.batch, InputX + "   " + InputY, 200, 200);
			if(!hasCompleted()){
				font.draw(Main.batch, "Hit the left side of the screen to finish the level.", Main.SCREEN_WIDTH/6, 400);
				font.draw(Main.batch, "Starting in: " + displaytime, Main.SCREEN_WIDTH/3, 100);
			}
			Main.batch.end();
			if(drawstage2){
				stagefinished.act();
				stagefinished.draw();
				Disabled = true;
				ClickListener2();
			}
			if(drawstage){
				stagedied.act();
				stagedied.draw();
				Disabled = true;
				ClickListener();
			}
			Main.batch.begin();
			if(finished){
				font.draw(Main.batch, "+ " + CoinsCollected + " " + "Coins", 240, 320);
				font.draw(Main.batch, "Total coins: " + CoinManager.TotalCoins, 240, 295);
				drawstage2 = true;
			}
			if(died){
				drawstage = true;
			}
			Main.batch.end();
			
		
		if(coinCountSession == 3){
			CollectedCoins = true;
		}
	}
	public void resize(int arg0, int arg1) {
		
	}
	public void resume() {
		
	}
	public void show() {
		main = new Main();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		camera.update();
		Setupfont();
		LoadTexture();
		LoadSprite();
		LoadImage();
		
		Random random = new Random();
		CoinX1 = random.nextInt(800);
		CoinY1 = random.nextInt(400);
		CoinX2 = random.nextInt(800);
		CoinY2 = random.nextInt(400);
		CoinX3 = random.nextInt(800);
		CoinY3 = random.nextInt(400);
		
		CharBounds = new Rectangle(360, 160, 60, 60);
		EvilCharBounds_1 = new Rectangle(0, 0, 80, 80);
		EvilCharBounds_2 = new Rectangle(160, 0, 80, 80);
		EvilCharBounds_3 = new Rectangle(320, 0, 80, 80);
		EvilCharBounds_4 = new Rectangle(480, 0, 80, 80);
		EvilCharBounds_5 = new Rectangle(80, 320, 80, 80);
		EvilCharBounds_6 = new Rectangle(240, 320, 80, 80);
		EvilCharBounds_7 = new Rectangle(400, 320, 80, 80);
		EvilCharBounds_8 = new Rectangle(560, 320, 80, 80);
		Finish = new Rectangle(0, 0, 1, 400);
		CoinRect1 = new Rectangle(CoinX1, CoinY1, 30, 30);
		CoinRect2 = new Rectangle(CoinX2, CoinY2, 30, 30);
		CoinRect3 = new Rectangle(CoinX3, CoinY3, 30, 30);
		
		//Stage for finished
		stagefinished.addActor(finished_window);
		stagefinished.addActor(star_image);
		finished_window.setX(Main.SCREEN_WIDTH/2 - 350/2);
		finished_window.setY(Main.SCREEN_HEIGHT/2 - 350/2);
		star_image.setX(305);
		star_image.setY(110);
		finished_window.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f)));
		star_image.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f)));
		
		//Stage for died
		stagedied.addActor(died_window);
		stagedied.addActor(cross_image);
		died_window.setX(Main.SCREEN_WIDTH/2 - 350/2);
		died_window.setY(Main.SCREEN_HEIGHT/2 - 350/2);
		cross_image.setX(305);
		cross_image.setY(110);
		died_window.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f)));
		cross_image.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f)));
		Disabled = false;
		
		Gdx.input.setInputProcessor(button_stage);
		
		atlas = new TextureAtlas("button.pack");
		skin = new Skin(atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		TextButtonStyle textButtonStyle = new TextButtonStyle(); 
		textButtonStyle.up = skin.getDrawable("button_up");
		textButtonStyle.down = skin.getDrawable("button_down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = font;
		
		back_button = new TextButton("Back", textButtonStyle);
		
		back_button.pad(10);
		back_button.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu(main));
			}
		});
		
		table.add(back_button);
		table.getCell(back_button).spaceBottom(10);
		table.getCell(back_button).prefSize(100, 50);
		table.getCell(back_button).pad(Main.SCREEN_HEIGHT/2 + 150, Main.SCREEN_WIDTH/2 + 300, 0, 0);
		button_stage.addActor(table);
		
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("effects/green.p"), Gdx.files.internal("effects"));
		effect.start();
		
		start();
	}
	
	public void ClickListener(){
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 225 && Gdx.input.getX() < 400 && Gdx.input.getY() > 300 && Gdx.input.getY() < 375){
				if(finished){
					coinCount += coinCountSession;
					CoinManager.writeText("" + coinCount);
				}
				died = false;
				Disabled = false;
				((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen(main));
			}
			if(Gdx.input.getX() > 400 && Gdx.input.getX() < 575 && Gdx.input.getY() > 300 && Gdx.input.getY() < 375){
				reset();
			}
		}
	}
	
	public void ClickListener2(){
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() > 225 && Gdx.input.getX() < 400 && Gdx.input.getY() > 300 && Gdx.input.getY() < 375){
				if(finished){
					System.out.println("Coins in this session: " + coinCountSession);
					CoinManager.TotalCoins += coinCountSession;
					CoinManager.writeText("" + CoinManager.TotalCoins);
				}
				died = false;
				Disabled = false;
				((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen(main));
			}
			if(Gdx.input.getX() > 400 && Gdx.input.getX() < 575 && Gdx.input.getY() > 300 && Gdx.input.getY() < 375){
				CoinManager.TotalCoins += coinCountSession;
				CoinManager.writeText("" + CoinManager.TotalCoins);
			}
		}
	}
	
	public void reset(){
		if(finished){
			System.out.println("Coins in this session: " + coinCountSession);
			CoinManager.TotalCoins += coinCountSession;
			CoinManager.writeText("" + CoinManager.TotalCoins);
		}
		died = false;
		finished = false;
		drawstage2 = false;
		drawstage = false;
		Disabled = false;
		effect.reset();
		Random random = new Random();
		CoinX1 = random.nextInt(800);
		CoinY1 = random.nextInt(400);
		CoinX2 = random.nextInt(800);
		CoinY2 = random.nextInt(400);
		CoinX3 = random.nextInt(800);
		CoinY3 = random.nextInt(400);
		CharBounds = new Rectangle(360, 160, 60, 60);
		EvilCharBounds_1 = new Rectangle(0, 0, 80, 80);
		EvilCharBounds_2 = new Rectangle(160, 0, 80, 80);
		EvilCharBounds_3 = new Rectangle(320, 0, 80, 80);
		EvilCharBounds_4 = new Rectangle(480, 0, 80, 80);
		EvilCharBounds_5 = new Rectangle(80, 320, 80, 80);
		EvilCharBounds_6 = new Rectangle(240, 320, 80, 80);
		EvilCharBounds_7 = new Rectangle(400, 320, 80, 80);
		EvilCharBounds_8 = new Rectangle(560, 320, 80, 80);
		CoinRect1 = new Rectangle(CoinX1, CoinY1, 30, 30);
		CoinRect2 = new Rectangle(CoinX2, CoinY2, 30, 30);
		CoinRect3 = new Rectangle(CoinX3, CoinY3, 30, 30);
		coinCountSession = 0;
		CollectedCoins = false;
		start();
	}
	
	public void LoadTexture(){
		Character = new Texture("assets/DodgeTheEnemiesCharacter.png");
		EvilCharacter = new Texture("assets/DodgeTheEnemiesEvilCharacter.png");
		Level1Background = new Texture("assets/LevelBackground.png");
		Coin = new Texture("assets/Coin.png");
		TextureFinish = new Texture("assets/InvisibleFinish.png");
		Star = new Texture("assets/Star.png");
		Cross = new Texture("assets/Cross.png");
		finished_texture = new Texture("assets/Finished.png");
		died_texture = new Texture("assets/Died.png");
		Star = new Texture("assets/Star.png");
		Cross = new Texture("assets/Cross.png");
	}
	
	public void LoadSprite(){
		sprite_character = new Sprite(Character);
		sprite_evilcharacter = new Sprite(EvilCharacter);
		sprite_level1background = new Sprite(Level1Background);
		Coin_Image = new Sprite(Coin);
		sprite_finish = new Sprite(TextureFinish);
		sprite_star = new Sprite(Star);
		sprite_cross = new Sprite(Cross);
	}

	public void LoadImage(){
		finished_window = new Image(finished_texture);
		died_window = new Image(died_texture);
		star_image = new Image(Star);
		cross_image = new Image(Cross);
	}
	
	public void CharControls(){
		if(!Disabled && hasCompleted()){
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
		if(hasCompleted()){
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
	}
	public void EvilCharBackMovement(){
		if(hasCompleted()){
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
	}
	public void CharFinish(){
		if(!Disabled){
			if(CharBounds.overlaps(Finish)){
				finished = true;
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
			if(CharBounds.overlaps(CoinRect1)){
				CoinRect1.x = 10000;
				CoinRect1.y = 10000;
				coinCount++;
				coinCountSession++;
				System.out.println(coinCountSession);
				CoinsCollected++;
			}
			if(CharBounds.overlaps(CoinRect2)){
				CoinRect2.x = 10000;
				CoinRect2.y = 10000;
				coinCount++;
				coinCountSession++;
				System.out.println(coinCountSession);
				CoinsCollected++;
			}
			if(CharBounds.overlaps(CoinRect3)){
				CoinRect3.x = 10000;
				CoinRect3.y = 10000;
				coinCount++;
				coinCountSession++;
				System.out.println(coinCountSession);
				CoinsCollected++;
			}
		}
	}
	public void Setupfont(){
		font = new BitmapFont(Gdx.files.internal("Font/MyFont.fnt"));
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
