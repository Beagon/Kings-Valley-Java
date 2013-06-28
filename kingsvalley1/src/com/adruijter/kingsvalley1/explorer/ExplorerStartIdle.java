package com.adruijter.kingsvalley1.explorer;

import java.util.HashMap;
import java.util.Map;

import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.math.Vector2;

public class ExplorerStartIdle extends AnimatedSprite
{
	private Vector2 rightDoorPosition, leftDoorPosition;
	private float timer = 0f;
	private float timerHandle = 0;
	private Map<Integer, String> handleRegions;
	private Explorer explorer;
	private String behind, inFront;
	private String handle = "handle";
	private float leftDoorWidth;
	private float offset = -16f;
	private int j = 0;
	
	public ExplorerStartIdle(Explorer explorer)
	{
		super(explorer);
		this.behind = "leftDoor";
		this.inFront = "rightDoor";
		this.leftDoorWidth = 48f;
		this.explorer = explorer;
		this.effect = true;
		this.i = 7;
		this.rightDoorPosition = new Vector2 (explorer.getPosition().x + 12f, explorer.getPosition().y);
		this.leftDoorPosition = new Vector2(explorer.getPosition().x - 36f, explorer.getPosition().y);
		this.handleRegions = new HashMap<Integer, String>();
		this.handleRegions.put(0, "handleDownTransparant");
		this.handleRegions.put(1, "handleDownWhite");
		this.handleRegions.put(2, "handleDownYellow");
		//this.handle = this.handleRegions.get(0);
		
	}
	
	public void Update(float delta)
	{
		this.timer += delta;
		if (this.timer < 40f/60f)
		{
			this.behind = "leftDoor";
			this.inFront = "rightDoor";
		}
		
		else if (this.timer < 100f/60f)
		{
			this.behind = "doorHalfOpen";
			this.leftDoorPosition = new Vector2 (explorer.getPosition().x, explorer.getPosition().y - 15f);
			this.offset = -24f;
			this.inFront = "transparantDoor";
		}
		
		else if (this.timer < 200f/60f)
		{
			this.behind = "doorClosed";
			this.offset = -31f;
			this.leftDoorWidth = 32f;
			this.leftDoorPosition = new Vector2 (explorer.getPosition().x + 7f, explorer.getPosition().y - 15f);			
			this.inFront = "transparantDoor";
			if (this.timerHandle < 2)
			{
				this.timerHandle += delta * 40;
				this.handle = this.handleRegions.get(this.j);
			}
			else
			{
				this.timerHandle = 0;
				if (this.j < 2)
				{
					this.j++;
				}
				else
				{
					this.j = 0;
				}
				
			}
		}

		else
		{
			this.explorer.setState(this.explorer.getIdleLeft());
			this.explorer.getGame().getGameScreen().getLevel().getMasterMelody().play();
			this.explorer.getGame().getGameScreen().getLevel().getMasterMelody().setVolume(0.6f);
			this.explorer.getGame().getGameScreen().getLevel().getMasterMelody().setLooping(true);
		}
		
		/*
		this.explorer.setPosition(this.explorer.getPosition().add(-1f, 0.5f));
		if (ExplorerManager.CollisionDetectionStartWalkDownStairs())
		{
			this.explorer.setPosition(this.explorer.getPosition().add(0f, this.explorer.getPixelsThroughFloor()));
			
		}*/
		//super.Update(delta);
	}
	
	public void Draw(float delta)
	{
		
		this.explorer.getGame().getBatch().draw(this.explorer.getRegion().get(this.behind),
												this.leftDoorPosition.x,
												this.leftDoorPosition.y, 
												this.leftDoorWidth, 
												48);
		this.explorer.getGame().getBatch().draw(this.explorer.getRegion().get(this.handle),
												this.leftDoorPosition.x + this.offset,
												this.leftDoorPosition.y, 
												16, 
												21);
		super.Draw(delta);	
		this.explorer.getGame().getBatch().draw(this.explorer.getRegion().get(this.inFront),
						this.rightDoorPosition.x,
						this.rightDoorPosition.y, 
						16, 
						48);
	}
}
