package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;

public class ExplorerIdleRightNoLineairMovement extends AnimatedSprite
{
	private Explorer explorer;
	
	//Constructor
	public ExplorerIdleRightNoLineairMovement(Explorer explorer)
	{
		super(explorer);
		this.explorer = explorer;
		this.i = 7;
	}
	
	public void Update(float delta)
	{
		if (KingsValley1.IsAndroid())
		{
			this.explorer.setState(this.explorer.getIdleRight());
		}
       	this.explorer.getCollisionRectJumpLeft().setX(this.explorer.getPosition().x + 10);
		super.Update(delta);
	}
	
	public void Draw(float delta)
	{
		super.Draw(delta);
	}
}
