package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.math.Vector2;

public class ExplorerStart extends AnimatedSprite
{
	private Explorer explorer;
	private Vector2 rightDoorPosition, leftDoorPosition;
	private float timer = 0f;
	
	public ExplorerStart(Explorer explorer) 
	{
		super(explorer);
		this.explorer = explorer;
		this.effect = true;
		this.i = 7;
		this.rightDoorPosition = new Vector2 (explorer.getPosition().x + 16f, explorer.getPosition().y - 16f);
		this.leftDoorPosition = new Vector2(explorer.getPosition().x - 32f, explorer.getPosition().y - 16f);
		this.explorer.getPosition().add(4f, -16f);		
	}
	
	public void Update(float delta)
	{
		this.timer += delta;
		/*
		if ( this.timer < 120f/60f)
		{
			
		}
		else */
		if ( this.timer < 150f/60f )
		{
			this.explorer.setState(this.explorer.getStartWalkDownStairs());
		}
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
