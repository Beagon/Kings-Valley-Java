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
	
	public void Update(float delta)
	{
		if (ExplorerManager.CollisionDetectionWallInFrontRight())
		{
			Gdx.app.log("123", "gebotst met muur");
			this.explorer.setPosition(this.explorer.getPosition().add(this.explorer.getPixelsInWallRight(), 0f));
			//this.explorer.getIdleRight();
			this.explorer.setState(explorer.getIdleRightNoLineairMovement());
		}
		this.explorer.setPosition(this.explorer.getPosition().
				add(this.speed, 0f));
		if ( !Gdx.input.isKeyPressed(Keys.RIGHT) && !KingsValley1.IsAndroid())
		{
			this.explorer.setState(this.explorer.getIdleRight());
		}
		if ( ExplorerManager.CollisionDetectionTopStairsLeft() &&
		     (Gdx.input.isTouched() ||
		      Gdx.input.isKeyPressed(Keys.DOWN)))
		{			
			this.explorer.setState(this.explorer.getWalkDownStairsLeft());
		}
		if ( ExplorerManager.CollisionDetectionBottomStairsRight() &&
			 (Gdx.input.isTouched() ||
			  Gdx.input.isKeyPressed(Keys.UP)))
		{			
			this.explorer.setState(this.explorer.getWalkUpStairsRight());
		}
		if ( ExplorerManager.CollisionDetectionFallOfFloorRight())
		{
			this.explorer.getFallOfFloorRight().Initialize();
			this.explorer.setState(this.explorer.getFallOfFloorRight());
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
		super.Draw(delta);		
	}

}
