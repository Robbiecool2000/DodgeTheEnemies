package com.FrobPlugins.me.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.FrobPlugins.me.Main;
import com.FrobPlugins.me.MainMenu;
	
public class DesktopLauncher {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Main(), config);
		config.width = Main.SCREEN_WIDTH;
		config.height = Main.SCREEN_HEIGHT;
		config.resizable = false;
		config.title = "DodgeTheEnemies";
	}
}
