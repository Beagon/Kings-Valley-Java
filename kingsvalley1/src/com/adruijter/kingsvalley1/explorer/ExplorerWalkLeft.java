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
	
	public void Update(float delta)
	{
		this.explorer.setPosition(this.explorer.getPosition().
				add(this.speed, 0f));
		if ( !Gdx.input.isKeyPressed(Keys.LEFT) && !KingsValley1.IsAndroid())
		{
			this.explorer.setState(this.explorer.getIdleLeft());
		}		
		if ( ExplorerManager.CollisionDetectionTopStairsRight() &&
			 (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.DOWN)))
		{			
			this.explorer.setState(this.explorer.getWalkDownStairsRight());
		}
		if ( ExplorerManager.CollisionDetectionBottomStairsLeft() &&
			 (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.UP)))
		{
			
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
