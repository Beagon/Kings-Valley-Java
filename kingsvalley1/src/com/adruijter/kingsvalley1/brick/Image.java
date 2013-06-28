package com.adruijter.kingsvalley1.brick;

import com.adruijter.kingsvalley1.KingsValley1;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Image
{
	//Fields
    protected KingsValley1 game;
    protected Rectangle rectangle;
    protected Vector2 position;
    protected TextureRegion region;

    public TextureRegion getRegion() {
		return region;
	}

	public void setRegion(TextureRegion region) {
		this.region = region;
	}

    public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	//Constructor
    public Image(KingsValley1 game, Vector2 position, TextureRegion region)
    {
        this.game = game;
        this.position = position;
        this.region = region;
        this.rectangle = new Rectangle((int)this.position.x,
                                       (int)this.position.y,
                                       this.region.getRegionWidth(),
                                       this.region.getRegionHeight());
    }

    //Draw
    public void Draw(float delta)
    {
        this.game.getBatch().draw(this.region,
				    			  this.position.x,
				   				  this.position.y,
				   				  0f,
				   				  0f,
				   				  this.region.getRegionWidth(),
				   				  this.region.getRegionHeight(),
				   				  1f,
				   				  1f,
				   				  0f);
    }
    
	public String getImageName() {
		return null;
	}

	public char getCharacter() {
		return 0;
	}

	public void setImageName(String imageName) {		
	}
}
