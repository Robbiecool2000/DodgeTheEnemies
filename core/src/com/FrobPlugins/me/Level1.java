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
import com.badlogic.gdx.math.Rectangle;

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
	
	//(Evil)CharBounds
	private final Rectangle CharBounds;
	private final Rectangle EvilCharBounds_1;
	private final Rectangle EvilCharBounds_2;
	private final Rectangle EvilCharBounds_3;
	private final Rectangle EvilCharBounds_4;
	private final Rectangle EvilCharBounds_5;
	private final Rectangle EvilCharBounds_6;
	private final Rectangle EvilCharBounds_7;
	private final Rectangle EvilCharBounds_8;
	
	private OrthographicCamera camera;
	Main main;
	
	public Level1(Main main) {
		this.main = main;
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
		Collide();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(sprite_character, CharBounds.x, CharBounds.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_1.x, EvilCharBounds_1.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_2.x, EvilCharBounds_2.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_3.x, EvilCharBounds_3.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_4.x, EvilCharBounds_4.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_5.x, EvilCharBounds_5.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_6.x, EvilCharBounds_6.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_7.x, EvilCharBounds_7.y);
			batch.draw(sprite_evilcharacter, EvilCharBounds_8.x, EvilCharBounds_8.y);
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
			CharBounds.y -= 6;
		}
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){
			CharBounds.y += 6;
		}
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)){
			CharBounds.x -= 6;
		}
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			CharBounds.x += 6;
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
		if(CharBounds.x == 0 && CharBounds.y >= 0 && CharBounds.y <= 400){
			if(CharBounds.x == 0 && CharBounds.y + 80 >= 0 && CharBounds.y + 80 <= 400){
				System.out.println("Finished!");
			}
		}
	}
	public void Collide(){
		if(CharBounds.overlaps(EvilCharBounds_1)){
			System.out.println("You died!");
		}
		if(CharBounds.overlaps(EvilCharBounds_2)){
			System.out.println("You died!");
		}
		if(CharBounds.overlaps(EvilCharBounds_3)){
			System.out.println("You died!");
		}
		if(CharBounds.overlaps(EvilCharBounds_4)){
			System.out.println("You died!");
		}
		if(CharBounds.overlaps(EvilCharBounds_5)){
			System.out.println("You died!");
		}
		if(CharBounds.overlaps(EvilCharBounds_6)){
			System.out.println("You died!");
		}
		if(CharBounds.overlaps(EvilCharBounds_7)){
			System.out.println("You died!");
		}
		if(CharBounds.overlaps(EvilCharBounds_8)){
			System.out.println("You died!");
		}
	}
}