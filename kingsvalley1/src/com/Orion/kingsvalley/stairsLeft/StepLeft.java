package com.Orion.kingsvalley.stairsLeft;

import com.Orion.kingsvalley.KingsValley;
import com.Orion.kingsvalley.brick.IBuildingBlock;
import com.Orion.kingsvalley.brick.Image;
import com.badlogic.gdx.math.Vector2;

public class StepLeft extends Image implements IBuildingBlock
{
	//Fields
    private char character;
    private String imageName;

    //Properties
    @Override
    public char getCharacter()
    {
        return this.character;
    }
    @Override
    public String getImageName()
    {
        return this.imageName;
    } 
    @Override
    public void setImageName(String imageName)
    {
    	this.imageName = imageName;
    }

    //Constructor
    public StepLeft(KingsValley game, Vector2 position, String imageName, char character)
    {
        super(game, position, "data/Stairs/" + imageName);
        this.character = character;
        this.imageName = imageName;
    }
    @Override
    public void Draw(float delta)
    {
        super.Draw(delta);
    }
}
