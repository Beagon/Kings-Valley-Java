package com.adruijter.kingsvalley1.floor;
import java.util.ArrayList;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.brick.Brick;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Floor 
{
	//Fields
	private KingsValley1 game;
	private Vector2 position;
	private int amountOfBricks;
	private char highOrLowFallRight;
	private char highOrLowFallLeft;
	private ArrayList<Brick> floor;
	private Rectangle collisionRectangle;
	
	//Properties
	public Rectangle getCollisionRectangle() {
		return collisionRectangle;
	}
	public void setCollisionRectangle(Rectangle collisionRectangle) {
		this.collisionRectangle = collisionRectangle;
	}
	
	public int getAmountOFBricks()
	{
		return this.amountOfBricks;
	}

	//Constructor
	public Floor(KingsValley1 game, Vector2 position, TextureRegion region, int amountOfBricks,
					char highOrLowFallRight, char highOrLowFallLeft)
	{
		this.game = game;
		this.position = position;
		this.amountOfBricks = amountOfBricks;
		this.highOrLowFallRight = highOrLowFallRight;
		this.highOrLowFallLeft = highOrLowFallLeft;	
		this.floor = new ArrayList<Brick>();
		this.LoadContent(region);
	}
	
	//LoadContent
	private void LoadContent(TextureRegion region)
	{
		//Voor het tekenen om te debuggen
		for ( int i = 0; i < this.amountOfBricks; i++)
		{
			this.floor.add(new Brick(this.game, new Vector2(i*16f, 0f).add(this.position), region, 'F' ));
		}
		this.collisionRectangle = new Rectangle(this.position.x,
												this.position.y,
												this.amountOfBricks * 16f,
												16f);
	}
	
	
	public void Draw(float delta)
	{
		for (Brick brick : this.floor)
		{
			brick.Draw(delta);
		}
	}
}
