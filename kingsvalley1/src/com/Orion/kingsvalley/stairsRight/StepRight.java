package com.Orion.kingsvalley.stairsRight;

import com.Orion.kingsvalley.KingsValley;
import com.Orion.kingsvalley.brick.IBuildingBlock;
import com.Orion.kingsvalley.brick.Image;
import com.badlogic.gdx.math.Vector2;

public class StepRight extends Image implements IBuildingBlock {

	//Fields
	private char character;
    private String imageName;
    
	//Constructor
	public StepRight(KingsValley game, Vector2 position, String imageName, char character) {
		super(game, position, "data/Stairs/" + imageName);
		this.character = character;
		this.imageName = imageName;
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

	@Override
	public String getImageName() {
		return this.imageName;
	}

	@Override
	public void setImageName(String imageName) {
		// TODO Auto-generated method stub
		
	}

}
