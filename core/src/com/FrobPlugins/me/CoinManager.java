package com.FrobPlugins.me;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class CoinManager {
	
	public static FileHandle handle = Gdx.files.local("assets/Coins.txt");
	
	public static String TotalCoins;
	
	public CoinManager(){
		TotalCoins = handle.readString();
	}
	public static void writeText(String text){
		handle.writeString(text, false);
		System.out.println(Main.Message_DEBUG + "wrote: " + text);
	}
	
	public static int getText(){
		handle.readString();
		System.out.println(Main.Message_DEBUG + "read: " + handle.readString());
		return 0;
	}
}