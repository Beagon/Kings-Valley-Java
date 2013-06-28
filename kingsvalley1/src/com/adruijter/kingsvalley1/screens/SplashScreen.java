package com.adruijter.kingsvalley1.screens;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.brick.Image;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SplashScreen implements Screen 
{

	//Fields
	private KingsValley1 game;
	private float ratio, yzoom = 480f;	
	private Image image, image1985Konami, imagePush, imagePlayStart;
	private TextureRegion msxRegion, konamiRegion, nothingRegion,
						  kingsValleyRegion, konami1985Region,  pushRegion,
						  playStartRegion;
	private Texture texture;
	private float timer;
	private boolean showImage1985Konami = false, showImagePlayStart = false,
					showImagePush = false;
	private float timerPlayStart;
	
	public float getRatio() {
		return ratio;
	}
	
	private OrthographicCamera cam;
	
	//Constructor
	public SplashScreen(KingsValley1 game) 
	{
		this.game = game;
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
			else if (this.timer < 3)
			{
				this.image.setRegion(this.nothingRegion);
			}
			else if (this.timer < 4)
			{
				this.image.setRegion(this.konamiRegion);
				this.image.getPosition().set(new Vector2(this.cam.position.x - this.image.getRegion().getRegionWidth()/2, this.cam.position.y/2 + 200));
				this.timer = 4;
			}
			else if (this.timer < 100)
			{
				this.image.setRegion(this.kingsValleyRegion);
				this.image.setPosition(new Vector2(this.cam.position.x - kingsValleyRegion.getRegionWidth()/2, this.cam.position.y/2));
				this.showImage1985Konami = true;
				this.showImagePush = true;
				this.game.setBackgroundColor(new Color(0.00f, 0.00f, 0.00f, 1f));
				if (Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isTouched())
				{
					this.timer = 100;
					this.game.getIntroMusic().play();
					this.game.getIntroMusic().setVolume(0.5f);
				}
			}
			else if (this.timer < 102)
			{
				
				this.showImage1985Konami = true;
				this.showImagePush = false;
				if (this.timerPlayStart < 4)
				{
					this.timerPlayStart += delta * 40;
					//this.showImagePlayStart = true;
				}
				else
				{
					this.showImagePlayStart = !this.showImagePlayStart;
					this.timerPlayStart = 0;
				}
			}
			else
			{
				this.game.setScreen(this.game.getGameScreen());
			}
		}
		this.game.getBatch().setProjectionMatrix(cam.combined);
		
		this.game.getBatch().begin();
		this.image.Draw(delta);
		if (this.showImage1985Konami)
		{
			this.image1985Konami.Draw(delta);			
		}
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
		this.texture = new Texture(Gdx.files.internal("data/SpriteSheet.png"));
		this.msxRegion = new TextureRegion(this.texture, 22, 164, 406, 204 );
		this.konamiRegion =  new TextureRegion(this.texture, 182, 377, 200, 80);
		this.nothingRegion = new TextureRegion(this.texture, 0, 0, 0, 0);
		this.kingsValleyRegion = new TextureRegion(this.texture, 148, 0, 300, 111);
		this.konami1985Region = new TextureRegion(this.texture, 148, 112, 197, 23);
		this.pushRegion = new TextureRegion(this.texture, 148, 136,224, 20);
		this.playStartRegion = new TextureRegion(this.texture, 349, 113, 160, 20);
		this.msxRegion.flip(false,  true);
		this.konamiRegion.flip(false, true);
		this.kingsValleyRegion.flip(false, true);
		this.konami1985Region.flip(false, true);
		this.pushRegion.flip(false, true);
		this.playStartRegion.flip(false, true);
		this.image = new Image(this.game, new Vector2(this.cam.position.x - msxRegion.getRegionWidth()/2, this.cam.position.y/2 + 200), this.msxRegion);
		this.image1985Konami = new Image(this.game, new Vector2(this.cam.position.x - this.konami1985Region.getRegionWidth()/2, this.cam.position.y/2 + 150), this.konami1985Region);
		this.imagePush = new Image(this.game, new Vector2(this.cam.position.x - this.pushRegion.getRegionWidth()/2, this.cam.position.y/2 + 200), this.pushRegion);
		this.imagePlayStart = new Image(this.game, new Vector2(this.cam.position.x - this.playStartRegion.getRegionWidth()/2, this.cam.position.y/2 + 200), this.playStartRegion);
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
