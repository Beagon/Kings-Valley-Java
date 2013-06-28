package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;

public class ExplorerIdleLeftNoLineairMovement extends AnimatedSprite
{
	private Explorer explorer;
	
	//Constructor
	public ExplorerIdleLeftNoLineairMovement(Explorer explorer)
	{
		super(explorer);
		this.explorer = explorer;
		this.i = 7;
		this.effect = true;
	}
	
	public void Update(float delta)
	{
		if (KingsValley1.IsAndroid())
		{
			this.explorer.setState(this.explorer.getIdleLeft());
		}
		super.Update(delta);
	}
	
	public void Draw(float delta)
	{
		super.Draw(delta);
	}
}
