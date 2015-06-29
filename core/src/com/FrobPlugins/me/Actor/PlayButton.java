package com.FrobPlugins.me.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayButton extends Actor {
	
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite_texture;
	
	public PlayButton(){
	     batch = new SpriteBatch();
	 }

	public void drawPlay(SpriteBatch batch, int x, int y){
		this.batch = batch;
		batch.end();
		batch.begin();
			batch.draw(sprite_texture, x, y);
		batch.end();
	}
	
	public void loadTexture(){
		texture = new Texture(Gdx.files.internal("assets/Red_Button.png"));
	}
	
	public void loadSprite(){
		sprite_texture = new Sprite(texture);
		sprite_texture.flip(false, true);
	}
}