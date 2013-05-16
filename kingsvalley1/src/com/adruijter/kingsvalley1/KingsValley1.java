package com.adruijter.kingsvalley1;


import com.adruijter.kingsvalley1.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KingsValley1 extends Game {
	
	//Fields
	private SpriteBatch batch;
	private GameScreen gameScreen;
	private static boolean isAndroid = true;
	
	//Properties
	public static boolean IsAndroid()
	{
		return isAndroid;
	}
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public SpriteBatch getBatch() {
		return batch;
	}
	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	@Override
	public void create() {
		this.batch = new SpriteBatch();
		this.gameScreen = new GameScreen(this);
		this.setScreen(this.gameScreen);		
	}

	@Override
	public void dispose() {
		this.batch.dispose();
		this.gameScreen.dispose();
		super.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0.06f, 0.06f, 0.06f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
