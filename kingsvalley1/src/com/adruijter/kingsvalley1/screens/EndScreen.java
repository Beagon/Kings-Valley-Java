package com.adruijter.kingsvalley1.screens;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.brick.Image;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class EndScreen implements Screen {

	//Fields
	private KingsValley1 game;
	private float ratio, yzoom = 480f;	
	private Image image, imagePush, imagePlayStart;
	private TextureRegion msxRegion;
	private Texture texture;
	private float timer;
	private boolean  showImagePlayStart = false,
					 showImagePush = false;
	private OrthographicCamera cam;
	
	public float getRatio() {
		return ratio;
	}

	
	//Constructor
	public EndScreen(KingsValley1 game) 
	{
		this.game = game;
		cam = new OrthographicCamera();		
		this.ratio = ((float)Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight());
		cam.setToOrtho(true,  this.ratio * this.yzoom, this.yzoom);
		cam.position.set(544f, 480f, 0);
		this.game.getBatch().setColor(1f, 1f, 1f, 1f);		
		cam.update();		
	}

	
	@Override
	public void render(float delta)
	{
		
		if ( this.image.getPosition().y > this.cam.position.y/2)
		{
			this.image.getPosition().add(0f, -2f);
		}
		else 
		{
			this.timer += delta;
			if (this.timer < 2)
			{			
				this.image.setRegion(this.msxRegion);
			}
		}
		this.game.getBatch().setProjectionMatrix(cam.combined);
		
		this.game.getBatch().begin();
		this.image.Draw(delta);
		if (this.showImagePush)
		{
			this.imagePush.Draw(delta);
		}
		if (this.showImagePlayStart)
		{
			this.imagePlayStart.Draw(delta);
		}
		this.game.getBatch().end();
	}

	@Override
	public void resize(int width, int height) 
	{
	}

	@Override
	public void show() {
		this.texture = new Texture(Gdx.files.internal("data/EndScreen.png"));
		this.msxRegion = new TextureRegion(this.texture, 0, 0, 545, 480);
		this.image = new Image(this.game, new Vector2(this.cam.position.x - msxRegion.getRegionWidth()/2, this.cam.position.y/2 + 480), this.msxRegion);
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
