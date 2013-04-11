package com.Orion.kingsvalley.explorer;


import com.Orion.kingsvalley.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class ExplorerIdleRight extends AnimatedSprite
{
	//Field
	private Explorer explorer;
	
	
	//Constructor
	public ExplorerIdleRight(Explorer explorer)
	{
		super(explorer);
		this.explorer = explorer;
		this.i = 7;
	}
	
	//Update
	public void Update(float delta)
	{
		if ( Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			this.explorer.setState(this.explorer.getWalkRight());
		}
	}
	
	public void Draw(float delta)
	{
		super.Draw(delta);		
	}
}
