package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu extends Game implements Screen {
	public static OrthographicCamera camera;
	public static BitmapFont font;
	Main game;
	private Stage stage = new Stage();
	private Stage button_stage = new Stage();
	private TextureAtlas atlas;
	private TextButton play_button;
	private TextButton shop_button;
	private Skin skin;
	private Table table;
	
	//Textures
	Texture Background;

	//Images
	Image sprite_Background;
	
	public MainMenu(Main main){
		this.game = main;
		LoadTexture();
		LoadSprite();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 800, 400);
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
        button_stage.act();
        button_stage.draw();
	}
	
	public void dispose() {
		font.dispose();
		button_stage.dispose();
	}
	
	public void hide() {
		dispose();
	}

	public void pause() {
		
	}
	
	//Loading all of the textures in the 'assets' folder.
	public void LoadTexture(){
		Background = new Texture("assets/Background.png");
	}
	
	//Loading and flipping the sprites.
	public void LoadSprite(){
		sprite_Background = new Image(Background);
	}
	
	public void SetupFont(){
		font = new BitmapFont(Gdx.files.internal("assets/Font/MyFont.fnt"));
	}

	public void resize(int arg0, int arg1) {
		
	}

	public void resume() {
		
	}

	public void show() {
		stage.addActor(sprite_Background);
		sprite_Background.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f)));
		
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
		
		play_button = new TextButton("Play", textButtonStyle);
		play_button.pad(10);
		play_button.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				((Game)Gdx.app.getApplicationListener()).setScreen(new LevelScreen(game));
			}
		});
		shop_button = new TextButton("Instructions", textButtonStyle);
		shop_button.pad(10);
		shop_button.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new InstructionScreen(game));
			}
		});
		
		table.add(play_button);
		table.getCell(play_button).spaceBottom(10);
		table.getCell(play_button).prefSize(100, 50);
		table.row();
		table.add(shop_button);
		table.getCell(shop_button).prefSize(100, 50);
		button_stage.addActor(table);
	}

	public void create() {
		// TODO Auto-generated method stub
		
	}
}