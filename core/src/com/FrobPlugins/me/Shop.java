package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Shop implements Screen{
	
	private Stage button_stage = new Stage();
	private TextureAtlas atlas;
	private TextButton back_button;
	private Skin skin;
	private Table table;
	
	//Textures
	Texture ShopBackground;
	
	//Sprites
	Sprite sprite_shopbackground;
	
	//Font
	public static BitmapFont font;
	
	Main main;
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	public Shop(Main main){
		this.main = main;
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
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(sprite_shopbackground, 0, 0);
		batch.end();
		button_stage.act();
		button_stage.draw();
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
		Setupfont();
		
		Gdx.input.setInputProcessor(button_stage);
		
		atlas = new TextureAtlas("assets/button.pack");
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
	}
	public void LoadTexture(){
		ShopBackground = new Texture("assets/Background.png");
	}
	public void LoadSprite(){
		sprite_shopbackground = new Sprite(ShopBackground);
	}
	public void Setupfont(){
		font = new BitmapFont(Gdx.files.internal("assets/data/arial-15.fnt"));
		font.setColor(Color.BLACK);
	}
}
