package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.animatedsprite.*;
import com.badlogic.gdx.math.Vector2;

public class ExplorerIdleFallAfterJump extends AnimatedSprite
{
	//Fields
	private Explorer explorer;
	private float startX, startY, y;
	private float timer;
	
	public ExplorerIdleFallAfterJump(Explorer explorer) {
		super(explorer);
		this.explorer = explorer;
		this.i = 0;
		//this.Initialize();
	}
	
	public void Initialize() //Sets the start positions
	{
		this.startX = this.explorer.getPosition().x;
		this.startY = this.explorer.getPosition().y;
		this.y = this.startY;
		//this.timer = 0f;
	}
	
	public void Update(float delta)
	{
		//super.Update(delta);
		this.timer += delta; //Updates the timer
		this.y = (float)(1200f * Math.pow(this.timer, 2d) + this.startY); //Calculates the Y value
		
		this.explorer.setPosition(new Vector2(this.startX, this.y)); //Sets the position to the changes Y value
		
		if (ExplorerManager.CollisionDetectionGroundAfterJump())
		{
			this.explorer.getCollisionRectStairs().setWidth(20f);
			this.explorer.setPosition(new Vector2(this.startX, this.explorer.getCollisionRectStairs().y + this.explorer.getPixelsThroughFloor()));
			this.explorer.setState(this.explorer.getIdleRightNoLineairMovement());
			this.timer = 0;
		}
	}
	
	public void Draw(float delta)
	{
		super.Draw(delta);
	}

}
