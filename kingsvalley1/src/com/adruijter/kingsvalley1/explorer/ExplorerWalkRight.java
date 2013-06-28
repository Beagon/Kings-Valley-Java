package com.adruijter.kingsvalley1.explorer;
import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class ExplorerWalkRight extends AnimatedSprite{
	
	//Fields
	private Explorer explorer;
	private float speed;
	
	//Constructor
	public ExplorerWalkRight(Explorer explorer)
	{
		super(explorer);
		this.explorer = explorer;
		this.speed = this.explorer.getSpeed();
		
	}
	
	public void Initialize()
	{
		this.explorer.getCollisionRectStairs().setWidth(12f);
		this.explorer.getCollisionRectStairs().setX(this.explorer.getPosition().x + 8f);
		//this.effect = false;
	}
	
	public void Update(float delta)
	{
		this.explorer.setPosition(this.explorer.getPosition().
				add(this.speed, 0f));	

       	this.explorer.getCollisionRectJumpLeft().setX(this.explorer.getPosition().x + 10);
       	this.explorer.getCollisionRectStairs().setX(this.explorer.getPosition().x + 8f);    	
       	
       	
		if (ExplorerManager.CollisionDetectionWallInFrontRight())
		{
			this.explorer.setPosition(this.explorer.getPosition().add(this.explorer.getPixelsInWallRight(), 0f));
			//test 29-5 13:53
			this.explorer.getCollisionRectStairs().setWidth(15f);
			this.explorer.getCollisionRectStairs().setX(this.explorer.getPosition().x + 5f);
			this.explorer.setState(explorer.getIdleRightNoLineairMovement());
		}
		
		if ( !Gdx.input.isKeyPressed(Keys.RIGHT) && !KingsValley1.IsAndroid())
		{
			this.explorer.setState(this.explorer.getIdleRight());
		}
		if ( ExplorerManager.CollisionDetectionTopStairsLeft() &&
		     (Gdx.input.isTouched() ||
		      Gdx.input.isKeyPressed(Keys.DOWN)))
		{			
			// Zorgt ervoor dat de explorer niet omhoog kan lopen........
			this.explorer.setPosition(this.explorer.getPosition().add(2f, 2f));
			this.explorer.setState(this.explorer.getWalkDownStairsLeft());
		}
		if ( ExplorerManager.CollisionDetectionBottomStairsRight() &&
			 (Gdx.input.isTouched() ||
			  Gdx.input.isKeyPressed(Keys.UP)))
		{	
			// Zorgt ervoor dat de explorer niet omlaag kan lopen........
			this.explorer.setPosition(this.explorer.getPosition().add(2f, -2f));
			this.explorer.setState(this.explorer.getWalkUpStairsRight());
		}
		if ( ExplorerManager.CollisionDetectionFallOfFloorRight())
		{
			this.explorer.getFallOfFloorRight().Initialize();
			this.explorer.setState(this.explorer.getFallOfFloorRight());
			
			//test ..dit is m waarschijnlijk....
			this.explorer.getCollisionRectJumpLeft().setX(this.explorer.getPosition().x + 10);
		}
		super.Update(delta);
	}
	
	public void Draw(float delta)
	{
		if (ExplorerManager.Debug())
		{
			this.explorer.getGame().getBatch().setColor(1f, 0f, 0f, 0.5f);
			this.explorer.getGame().getBatch().draw(this.explorer.getCollisionText(),
												    this.explorer.getCollisionRectStairs().x,
												    this.explorer.getCollisionRectStairs().y,
												    this.explorer.getCollisionRectStairs().width,
												    this.explorer.getCollisionRectStairs().height);
			this.explorer.getGame().getBatch().setColor(1f, 1f, 1f, 1f);
		}
		this.explorer.getGame().getBatch().setColor(1f, 1f, 1f, 1f);
		super.Draw(delta);		
	}

}
