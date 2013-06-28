package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.math.Vector2;

public class ExplorerIdleFallAfterJump extends AnimatedSprite
{
	//Fields
	private Explorer explorer;
	private float startX, startY, y;
	private float timer;
	
	//Constructor
	public ExplorerIdleFallAfterJump(Explorer explorer) 
	{
		super(explorer);
		this.explorer = explorer;
		this.i = 0;
		//this.Initialize();
	}
	
	public void Initialize(boolean effect)
	{
		this.startX = this.explorer.getPosition().x;
		this.startY = this.explorer.getPosition().y;
		this.y = this.startY;
		this.effect = effect;
		//this.timer = 0f;
	}
	
	public void Update(float delta)
	{
		//super.Update(delta);
		this.timer += 1/40f; //delta;
		
		this.y = (float)(1200f * Math.pow(this.timer, 2d) + this.startY);
		
		this.explorer.setPosition(new Vector2(this.startX, this.y));
		
		if (this.effect)
		{
			this.explorer.getCollisionRectStairs().setX(this.explorer.getCollisionRectStairs().x + 1f);
		}
		
		if ( ExplorerManager.CollisionDetectionGroundAfterJump())
		{
			this.explorer.getCollisionRectStairs().setWidth(20f);
			this.explorer.setPosition(new Vector2(this.startX,
												  this.explorer.getCollisionRectStairs().y +
												  this.explorer.getPixelsThroughFloor()));
			if (this.effect)
			{
				this.explorer.setState(this.explorer.getIdleLeft());
			}
			else
			{
				this.explorer.setState(this.explorer.getIdleRight());
			}
			this.timer = 0f;
		}
	}
	
	public void Draw(float delta)
	{
		super.Draw(delta);
	}

}
