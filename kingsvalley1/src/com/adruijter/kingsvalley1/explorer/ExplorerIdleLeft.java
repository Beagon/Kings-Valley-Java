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
			//test 21:37 29-5
			this.explorer.getWalkLeft().Initialize();
			this.explorer.setState(this.explorer.getWalkLeft());
		}
		if ( Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			this.explorer.getWalkRight().Initialize();
			this.explorer.setState(this.explorer.getWalkRight());
		}
		//Gdx.app.log("flip", Boolean.toString(this.effect));
		
	}
	
	public void Draw(float delta)
	{
		super.Draw(delta);		
	}
}
