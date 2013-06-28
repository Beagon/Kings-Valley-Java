package com.adruijter.kingsvalley1.explorer;
import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class ExplorerWalkLeft extends AnimatedSprite{
	
	//Fields
	private Explorer explorer;
	private float speed;
	
	//Constructor
	public ExplorerWalkLeft(Explorer explorer)
	{
		super(explorer);
		this.explorer = explorer;
		this.speed = -this.explorer.getSpeed();
		this.effect = true;
	}
	
	//test 21:36 29-5
	public void Initialize()
	{
		this.explorer.getCollisionRectStairs().setWidth(12f);
		this.explorer.getCollisionRectStairs().setX(this.explorer.getPosition().x);
	}//einde test
	
	public void Update(float delta)
	{
		this.explorer.setPosition(this.explorer.getPosition().
				add(this.speed, 0f));
		
		//test 30-5 1:04
		this.explorer.getCollisionRectStairs().setX(this.explorer.getPosition().x);
		
		if (ExplorerManager.CollisionDetectionWallInFrontLeft())
		{
			//Verander de state van de explorer
			this.explorer.setPosition(this.explorer.getPosition().add(this.explorer.getPixelsInWallLeft(), 0f));
			this.explorer.setState(this.explorer.getIdleLeftNoLineairMovement());
		}
		
		if ( !Gdx.input.isKeyPressed(Keys.LEFT) && !KingsValley1.IsAndroid())
		{
			this.explorer.setState(this.explorer.getIdleLeft());
		}		
		if ( ExplorerManager.CollisionDetectionTopStairsRight() &&
			 (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.DOWN)))
		{			
			// Zorgt ervoor dat de explorer niet omhoog kan lopen........
			this.explorer.setPosition(this.explorer.getPosition().add(-2f, 2f));
			this.explorer.setState(this.explorer.getWalkDownStairsRight());
		}
		if ( ExplorerManager.CollisionDetectionBottomStairsLeft() &&
			 (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.UP)))
		{
			// Zorgt ervoor dat de explorer niet omlaag kan lopen........
			this.explorer.setPosition(this.explorer.getPosition().add(-2f, -2f));
			this.explorer.setState(this.explorer.getWalkUpStairsLeft());
		}
		if ( ExplorerManager.CollisionDetectionFallOfFloorLeft())
		{
			this.explorer.getFallOfFloorLeft().Initialize();
			this.explorer.setState(this.explorer.getFallOfFloorLeft());
		}
		super.Update(delta);
	}
	
	public void Draw(float delta)
	{
		if (ExplorerManager.Debug())
		{
			this.explorer.getGame().getBatch().setColor(1f, 0f, 1f, 0.5f);
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
