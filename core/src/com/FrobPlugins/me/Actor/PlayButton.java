package com.FrobPlugins.me.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayButton extends Actor {
	
	private ShapeRenderer shapeRenderer;
	private ShapeRenderer shapeRenderer2;
	static private boolean projectionMatrixSet;
	static private boolean projectionMatrixSet2;
	
	public PlayButton(){
	     shapeRenderer = new ShapeRenderer();
	     shapeRenderer2 = new ShapeRenderer();
	     projectionMatrixSet = false;
	 }

	   public void drawPlay(SpriteBatch batch, int x, int y, int width, int height){
	       if(!projectionMatrixSet){
	           shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	       }
	       Gdx.gl.glEnable(GL20.GL_BLEND);
		   Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	       shapeRenderer.begin(ShapeType.Filled);
	       shapeRenderer.setColor(Color.RED);
	       shapeRenderer.rect(x, y, width, height);
	       shapeRenderer.end();
	       Gdx.gl.glDisable(GL20.GL_BLEND);
	   }
	   
	   public void drawOptions(SpriteBatch batch, int x, int y, int width, int height){
		   if(!projectionMatrixSet2){
			   shapeRenderer2.setProjectionMatrix(batch.getProjectionMatrix());
		   }
		   shapeRenderer2.begin(ShapeType.Filled);
		   shapeRenderer2.setColor(Color.RED);
		   shapeRenderer2.rect(x, y, width, height);
		   shapeRenderer2.end();
		   
	   }
}