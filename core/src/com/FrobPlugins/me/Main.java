package com.FrobPlugins.me;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class Main extends Game implements ApplicationListener{
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 400;
	MainMenu mainmenu;
	
	private final Game game;
	
	public Main(final Game game) {
	    this.game = game;
	  }
	
	public void create() {
		mainmenu = new MainMenu(this);
		game.setScreen(mainmenu);
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void render() {
		// TODO Auto-generated method stub
		
	}

	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public void resume() {
		// TODO Auto-generated method stub
		
	}
}