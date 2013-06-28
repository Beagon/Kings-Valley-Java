package com.adruijter.kingsvalley1.explorer;


import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class ExplorerIdleRight extends AnimatedSprite
{
	//Field
	private Explorer explorer;
	
	
	//Constructor
	public ExplorerIdleRight(Explorer explorer)
	{
		super(explorer);
		this.explorer = explorer;
		this.i = 7;
		this.Initialize();
		this.effect = false;
	}
	
	public void Initialize()
	{
		this.explorer.getCollisionRectStairs().setWidth(8f);
	}
	
	//Update
	public void Update(float delta)
	{
		if ( Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			this.explorer.getWalkRight().Initialize();
			this.explorer.setState(this.explorer.getWalkRight());
		}
		if ( Gdx.input.isKeyPressed(Keys.LEFT))
		{
			this.explorer.setState(this.explorer.getWalkLeft());
		}
		//Uit de put komen tegen het trillen....
		this.explorer.getCollisionRectJumpLeft().setX(this.explorer.getPosition().x + 10);	
	}
	
	public void Draw(float delta)
	{
		/*
		this.explorer.getGame().getBatch().setColor(0f, 0f, 1f, 1f);
		this.explorer.getGame().getBatch().draw(this.explorer.getCollisionText(), this.explorer.getCollisionRectStairs().x, this.explorer.getCollisionRectStairs().y, 
				this.explorer.getCollisionRectStairs().getWidth(), this.explorer.getCollisionRectStairs().getHeight());
		this.explorer.getGame().getBatch().setColor(1f, 1f, 1f, 1f);*/
		super.Draw(delta);		
	}
}
