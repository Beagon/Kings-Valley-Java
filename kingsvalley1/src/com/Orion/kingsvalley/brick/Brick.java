package com.Orion.kingsvalley.brick;

import com.Orion.kingsvalley.KingsValley;
import com.badlogic.gdx.math.Vector2;

public class Brick extends Image implements IBuildingBlock
{
	 //Fields
    private char character;
    private String imageName;
	
	//Properties
	public String getImageName() {
		return this.imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public char getCharacter() {
		return this.character;
	}
	public void setCharacter(char character) {
		this.character = character;
	}

	//Constructor
	public Brick(KingsValley game, Vector2 position, String imagePath, char character)
	{
		super(game, position,  "data/Bricks/" + imagePath);
		this.imageName = imagePath;
		this.character = character;
	}
	
	//Draw
	public void Draw(float delta)
    {
        super.Draw(delta);
    }
}
