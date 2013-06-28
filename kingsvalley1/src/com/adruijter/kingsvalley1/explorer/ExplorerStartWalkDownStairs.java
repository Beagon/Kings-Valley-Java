package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.math.Vector2;

public class ExplorerStartWalkDownStairs extends AnimatedSprite
{
	private Vector2 rightDoorPosition, leftDoorPosition;
	private float timer = 0f;
	private Explorer explorer;
	
	public ExplorerStartWalkDownStairs(Explorer explorer)
	{
		super(explorer);
		this.explorer = explorer;
		this.effect = true;
		this.i = 7;
		this.rightDoorPosition = new Vector2 (explorer.getPosition().x + 12f, explorer.getPosition().y);
		this.leftDoorPosition = new Vector2(explorer.getPosition().x - 36f, explorer.getPosition().y);
	}
	
	public void Update(float delta)
	{
		this.explorer.setPosition(this.explorer.getPosition().add(-1f, 0.5f));
		if (ExplorerManager.CollisionDetectionStartWalkDownStairs())
		{
			this.explorer.setPosition(this.explorer.getPosition().add(0f, this.explorer.getPixelsThroughFloor()));
			this.explorer.setState(this.explorer.getStartIdle());
		}
		super.Update(delta);
	}
	
	public void Draw(float delta)
	{
		this.explorer.getGame().getBatch().draw(this.explorer.getRegion().get("leftDoor"),
												this.leftDoorPosition.x,
												this.leftDoorPosition.y, 
												48, 
												48);
		this.explorer.getGame().getBatch().draw(this.explorer.getRegion().get("handle"),
												this.leftDoorPosition.x - 16f,
												this.leftDoorPosition.y, 
												16, 
												21);
		super.Draw(delta);	
		this.explorer.getGame().getBatch().draw(this.explorer.getRegion().get("rightDoor"),
						this.rightDoorPosition.x,
						this.rightDoorPosition.y, 
						16, 
						48);
	}

}
