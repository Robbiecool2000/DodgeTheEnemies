package com.FrobPlugins.me;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game implements ApplicationListener {
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 400;
	public static LevelScreen level1;
	public SplashScreen splash_screen;
	public static InstructionScreen instruction_screen;
	public static final String Message_INFO = "[Main/Info]: ";
	public static final String Message_ERROR = "[Main/ERROR]: ";
	public static final String Message_WARNING = "[Main/WARNING]: ";
	public static final String Message_DEBUG = "[Main/DEBUG]: ";
	public static SpriteBatch batch;
	public void create() {
		batch = new SpriteBatch();
		splash_screen = new SplashScreen(this);
		level1 = new LevelScreen(this);
		instruction_screen = new InstructionScreen(this);
		setScreen(splash_screen);
	}
}