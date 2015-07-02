package com.FrobPlugins.me;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class Main extends Game implements ApplicationListener {
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 400;
	MainMenu mainmenu;
	public static LevelScreen level_screen;
	public Level1 level1;
	
	public void create() {
		level_screen = new LevelScreen(this);
		level1 = new Level1(this);
		mainmenu = new MainMenu(this);
		setScreen(mainmenu);
	}
}