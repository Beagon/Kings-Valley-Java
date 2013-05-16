package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;



public class ExplorerIdleLeft extends AnimatedSprite
{
	//Field
	private Explorer explorer;
	
	//Constructor
	public ExplorerIdleLeft(Explorer explorer)
	{
		super(explorer);
		this.explorer = explorer;
		this.i = 7;
		this.effect = true;
	}
	
	//Update
	public void Update(float delta)
	{
		if ( Gdx.input.isKeyPressed(Keys.LEFT))
		{
			this.explorer.setState(this.explorer.getWalkLeft());
		}
	}
	
	public void Draw(float delta)
	{
		super.Draw(delta);		
	}
}
