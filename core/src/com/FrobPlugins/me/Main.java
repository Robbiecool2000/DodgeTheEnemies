package com.FrobPlugins.me;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class Main extends Game implements ApplicationListener {
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 400;
	public static Level1 level1;
	public SplashScreen splash_screen;
	
	public void create() {
		splash_screen = new SplashScreen();
		level1 = new Level1(this);
		setScreen(splash_screen);
	}
}