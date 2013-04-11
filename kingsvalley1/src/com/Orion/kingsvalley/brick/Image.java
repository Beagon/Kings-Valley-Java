package com.Orion.kingsvalley.brick;

import com.Orion.kingsvalley.KingsValley;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Image
{
	//Fields
    protected KingsValley game;
    protected Texture texture;
    protected Rectangle rectangle;
    protected Vector2 position;
    protected String imagePath;


    //Constructor
    public Image(KingsValley game, Vector2 position, String imagePath)
    {
        this.game = game;
        this.position = position;
        this.imagePath = imagePath;
        this.texture = new Texture(this.imagePath);
        this.rectangle = new Rectangle((int)this.position.x,
                                       (int)this.position.y,
                                       this.texture.getWidth(),
                                       this.texture.getHeight());
    }

    //Draw
    public void Draw(float delta)
    {
        //this.game.getBatch().draw(this.texture, this.position.x, this.position.y);
        this.game.getBatch().draw(this.texture,
				    			  this.position.x,
				   				  this.position.y,
				   				  16f,
				   				  16f,
				   				  0,
				   				  0,
				   				  16,
				   				  16,
				   				  false,
				   				  true);
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
