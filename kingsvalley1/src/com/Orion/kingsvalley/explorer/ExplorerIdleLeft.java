package com.Orion.kingsvalley.explorer;

import com.Orion.kingsvalley.animatedsprite.AnimatedSprite;



public class ExplorerIdleLeft extends AnimatedSprite
{
	//Field
	
	
	//Constructor
	public ExplorerIdleLeft(Explorer explorer)
	{
		super(explorer);
		this.i = 7;
		this.effect = true;
	}
	
	//Update
	public void Update(float delta)
	{
		
	}
	
	public void Draw(float delta)
	{
		super.Draw(delta);		
	}
}
