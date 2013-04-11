package com.Orion.kingsvalley.screens;

import java.io.IOException;

import com.Orion.kingsvalley.KingsValley;
import com.Orion.kingsvalley.level.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {

	//Fields
	private KingsValley game;
	private Level level;
	private float ratio, yzoom = 480f;
	private FPSLogger logger;
	
	
	public float getRatio() {
		return ratio;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}

	private OrthographicCamera cam;
	
	//Constructor
	public GameScreen(KingsValley game) 
	{
		this.game = game;
		this.logger = new FPSLogger();
		cam = new OrthographicCamera();		
		this.ratio = ((float)Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight());
		cam.setToOrtho(true,  this.ratio * this.yzoom, this.yzoom);
		cam.position.set(544f/2f, 480f/2f, 0);
		this.game.getBatch().setColor(1f, 1f, 1f, 1f);		
		cam.update();
	}

	
	@Override
	public void render(float delta) {
		/*
		if ( Gdx.input.isTouched())
		{
			Gdx.app.log("x", Integer.toString(Gdx.input.getX()));
			Gdx.app.log("y", Integer.toString(Gdx.input.getY())); 
			//this.yzoom -= 10;
			cam.setToOrtho(true,  this.ratio * this.yzoom, this.yzoom);
			cam.position.set(544f/2f, 480/2f, 0);
			cam.update();
			Gdx.app.log("test", Float.toString(this.yzoom)); 
		}*/
		this.logger.log();
		//Gdx.app.log("1/delta", Float.toString(1f/delta));
		this.level.Update(delta);
		this.game.getBatch().setProjectionMatrix(cam.combined);	
		this.game.getBatch().begin();
		this.level.Draw(delta);
		this.game.getBatch().end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		try {
			this.level = new Level(this.game, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
