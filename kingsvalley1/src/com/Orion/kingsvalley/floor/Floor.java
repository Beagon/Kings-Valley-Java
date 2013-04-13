package com.Orion.kingsvalley.floor;

import java.util.ArrayList;

import com.Orion.kingsvalley.KingsValley;
import com.Orion.kingsvalley.brick.Brick;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Floor {
	
	//Fields
	private KingsValley game;
	private Vector2 position;
	private Rectangle collisionRectangle;
	private int amountOfBricks;
	private char highOrLowFallRight;
	private char highOrLowFallLeft;
	private ArrayList<Brick> floor;
	
	//Properties
	public Rectangle getCollisionRectangle() {
		return collisionRectangle;
	}

	public void setCollisionRectangle(Rectangle collisionRect) {
		this.collisionRectangle = collisionRect;
	}

	// Constructor
	public Floor(KingsValley game, Vector2 position, int amountOfBricks, char highOrLowFallRight, char highOrLowFallLeft) 
	{
		this.game = game;
		this.position = position;
		this.amountOfBricks = amountOfBricks;
		this.highOrLowFallRight = highOrLowFallRight;
		this.highOrLowFallLeft = highOrLowFallLeft;
		this.floor = new ArrayList<Brick>();
		this.LoadContent();
		
	}
	
	//Load Content. This will load all the blocks for the floor into the list floor.
	private void LoadContent()
	{ 
		//Used to draw and debug
		for (int i = 0; i < this.amountOfBricks; i++){
			this.floor.add(new Brick(this.game, new Vector2(this.position.x  + i * 16f, this.position.y), "floorTexture16x16.png", 'F'));
		}
		this.collisionRectangle = new Rectangle(this.position.x, this.position.y, this.amountOfBricks * 16f, 16f);
		
	}
	
	//Draw Method
	public void Draw(float delta)
	{
			for (Brick brick : this.floor){
				brick.Draw(delta);
			}
	}
	

}
