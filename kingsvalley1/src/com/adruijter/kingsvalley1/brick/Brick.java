package com.adruijter.kingsvalley1.brick;

import com.adruijter.kingsvalley1.KingsValley1;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Brick extends Image implements IBuildingBlock
{
	 //Fields
    private char character;
    
	//Properties
   	public char getCharacter() {
		return this.character;
	}
	public void setCharacter(char character) {
		this.character = character;
	}

	//Constructor
	public Brick(KingsValley1 game, Vector2 position, TextureRegion region, char character)
	{
		super(game, position, region);
		this.character = character;
	}
	
	//Draw
	public void Draw(float delta)
    {
        super.Draw(delta);
    }
}
