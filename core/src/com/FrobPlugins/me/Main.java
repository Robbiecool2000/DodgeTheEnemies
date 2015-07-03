package com.FrobPlugins.me;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class Main extends Game implements ApplicationListener {
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 400;
	public static Level1 level1;
	public static Level2 level2;
	public static Level3 level3;
	public static Level4 level4;
	public static Level5 level5;
	public static Level6 level6;
	public static Level7 level7;
	public static Level8 level8;
	public SplashScreen splash_screen;
	
	public void create() {
		splash_screen = new SplashScreen();
		level1 = new Level1(this);
		level2 = new Level2(this);
		level3 = new Level3(this);
		level4 = new Level4(this);
		level5 = new Level5(this);
		level6 = new Level6(this);
		level7 = new Level7(this);
		level8 = new Level8(this);
		setScreen(splash_screen);
	}
}