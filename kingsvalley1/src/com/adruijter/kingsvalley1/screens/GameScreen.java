package com.adruijter.kingsvalley1.screens;

import java.io.IOException;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.level.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {

	//Fields
	private KingsValley1 game;
	private Level level;
	private float ratio, yzoom = 480f;
	private FPSLogger logger;
	private int levelIndex;
	
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
	public GameScreen(KingsValley1 game) 
	{
		this.levelIndex = game.getLevelIndex();
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
	public void render(float delta)
	{
		this.logger.log();
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
			this.level = new Level(this.game, levelIndex);
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
