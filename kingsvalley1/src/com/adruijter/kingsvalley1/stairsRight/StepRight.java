package com.adruijter.kingsvalley1.stairsRight;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.brick.IBuildingBlock;
import com.adruijter.kingsvalley1.brick.Image;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class StepRight extends Image implements IBuildingBlock {

	//Fields
	private char character;
    
	//Constructor
	public StepRight(KingsValley1 game, Vector2 position, TextureRegion region, char character) 
	{
		super(game, position, region);
		this.character = character;
	}
	
	//Properties
	
	@Override
	public void Draw(float delta) {		
		super.Draw(delta);
	}

	@Override
	public char getCharacter() {
		return this.character;
	}
}
