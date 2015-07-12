package com.FrobPlugins.me;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level1 extends Game implements Screen{
	
	//Texture
	Texture Character;
	Texture EvilCharacter;
	
	//Sprites
	Sprite sprite_character;
	Sprite sprite_evilcharacter;
	
	private SpriteBatch batch;
	
	int EvilCharAtTop_1 = 1;
	int EvilCharAtTop_2 = 1;
	int EvilCharAtTop_3 = 1;
	int EvilCharAtTop_4 = 1;
	
	int EvilCharAtTop_5 = 0;
	int EvilCharAtTop_6 = 0;
	int EvilCharAtTop_7 = 0;
	int EvilCharAtTop_8 = 0;
	
	//(Evil)CharX-Y
	int CharX = 360;
	int CharY = 160;
	int EvilCharX_1 = 0;
	int EvilCharY_1 = 0;
	int EvilCharX_2 = 160;
	int EvilCharY_2 = 0;
	int EvilCharX_3 = 320;
	int EvilCharY_3 = 0;
	int EvilCharX_4 = 480;
	int EvilCharY_4 = 0;
	int EvilCharX_5 = 80;
	int EvilCharY_5 = 320;
	int EvilCharX_6 = 240;
	int EvilCharY_6 = 320;
	int EvilCharX_7 = 400;
	int EvilCharY_7 = 320;
	int EvilCharX_8 = 560;
	int EvilCharY_8 = 320;
	
	private OrthographicCamera camera;
	Main main;
	
	public Level1(Main main) {
		this.main = main;
		LoadTexture();
		LoadSprite();
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
		EvilCharIntSystem();
		EvilCharMovement();
		EvilCharBackMovement();
		CharFinish();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(sprite_character, CharX, CharY);
			batch.draw(sprite_evilcharacter, EvilCharX_1, EvilCharY_1);
			batch.draw(sprite_evilcharacter, EvilCharX_2, EvilCharY_2);
			batch.draw(sprite_evilcharacter, EvilCharX_3, EvilCharY_3);
			batch.draw(sprite_evilcharacter, EvilCharX_4, EvilCharY_4);
			batch.draw(sprite_evilcharacter, EvilCharX_5, EvilCharY_5);
			batch.draw(sprite_evilcharacter, EvilCharX_6, EvilCharY_6);
			batch.draw(sprite_evilcharacter, EvilCharX_7, EvilCharY_7);
			batch.draw(sprite_evilcharacter, EvilCharX_8, EvilCharY_8);
		batch.end();
	}
	public void resize(int arg0, int arg1) {
		
	}
	public void resume() {
		
	}
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 800, 400);
		camera.update();
	}
	
	public void LoadTexture(){
		Character = new Texture("assets/DodgeTheEnemiesCharacter.png");
		EvilCharacter = new Texture("assets/DodgeTheEnemiesEvilCharacter.png");
	}
	
	public void LoadSprite(){
		sprite_character = new Sprite(Character);
		sprite_character.flip(false, true);
		sprite_evilcharacter = new Sprite(EvilCharacter);
		sprite_evilcharacter.flip(false, true);
	}
	
	public void CharControls(){
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)){
			CharY -= 6;
		}
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){
			CharY += 6;
		}
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)){
			CharX -= 6;
		}
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			CharX += 6;
		}
	}
	
	public void CharBoundaries(){
		if(CharX >= 720){
			CharX = 720;
		}
		if(CharX <= 0){
			CharX = 0;
		}
		if(CharY >= 320){
			CharY = 320;
		}
		if(CharY <= 0){
			CharY = 0;
		}
	}
	public void EvilCharIntSystem(){
		if(EvilCharY_1 == 0){
			EvilCharAtTop_1 = 1;
		}
		if(EvilCharY_1 == 320){
			EvilCharAtTop_1 = 0;
		}
		
		if(EvilCharY_2 == 0){
			EvilCharAtTop_2 = 1;
		}
		if(EvilCharY_2 == 320){
			EvilCharAtTop_2 = 0;
		}
		
		if(EvilCharY_3 == 0){
			EvilCharAtTop_3 = 1;
		}
		if(EvilCharY_3 == 320){
			EvilCharAtTop_3 = 0;
		}
		
		if(EvilCharY_4 == 0){
			EvilCharAtTop_4 = 1;
		}
		if(EvilCharY_4 == 320){
			EvilCharAtTop_4 = 0;
		}
		
		if(EvilCharY_5 == 0){
			EvilCharAtTop_5 = 1;
		}
		if(EvilCharY_5 == 320){
			EvilCharAtTop_5 = 0;
		}
		
		if(EvilCharY_6 == 0){
			EvilCharAtTop_6 = 1;
		}
		if(EvilCharY_6 == 320){
			EvilCharAtTop_6 = 0;
		}
		
		if(EvilCharY_7 == 0){
			EvilCharAtTop_7 = 1;
		}
		if(EvilCharY_7 == 320){
			EvilCharAtTop_7 = 0;
		}
		
		if(EvilCharY_8 == 0){
			EvilCharAtTop_8 = 1;
		}
		if(EvilCharY_8 == 320){
			EvilCharAtTop_8 = 0;
		}
	}
	public void EvilCharMovement(){
		if(EvilCharAtTop_1 == 1){
			EvilCharY_1 += 1;
		}
		
		if(EvilCharAtTop_2 == 1){
			EvilCharY_2 += 1;
		}
		
		if(EvilCharAtTop_3 == 1){
			EvilCharY_3 += 1;
		}
		
		if(EvilCharAtTop_4 == 1){
			EvilCharY_4 += 1;
		}
		
		
		if(EvilCharAtTop_5 == 0){
			EvilCharY_5 -= 1;
		}
		
		if(EvilCharAtTop_6 == 0){
			EvilCharY_6 -= 1;
		}
		
		if(EvilCharAtTop_7 == 0){
			EvilCharY_7 -= 1;
		}
		
		if(EvilCharAtTop_8 == 0){
			EvilCharY_8 -= 1;
		}
	}
	public void EvilCharBackMovement(){
		if(EvilCharAtTop_1 == 0){
			EvilCharY_1 -= 1;
		}
		
		if(EvilCharAtTop_2 == 0){
			EvilCharY_2 -= 1;
		}
		
		if(EvilCharAtTop_3 == 0){
			EvilCharY_3 -= 1;
		}
		
		if(EvilCharAtTop_4 == 0){
			EvilCharY_4 -= 1;
		}
		
		
		if(EvilCharAtTop_5 == 1){
			EvilCharY_5 += 1;
		}
		
		if(EvilCharAtTop_6 == 1){
			EvilCharY_6 += 1;
		}
		
		if(EvilCharAtTop_7 == 1){
			EvilCharY_7 += 1;
		}
		
		if(EvilCharAtTop_8 == 1){
			EvilCharY_8 += 1;
		}
	}
	public void CharFinish(){
		if(CharX == 0 && CharY >= 0 && CharY <= 400){
			if(CharX == 0 && CharY + 80 >= 0 && CharY + 80 <= 400){
				System.out.println("Finished!");
			}
		}
	}
}