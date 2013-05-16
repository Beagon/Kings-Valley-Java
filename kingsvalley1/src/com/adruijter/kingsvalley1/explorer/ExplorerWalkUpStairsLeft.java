package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;

public class ExplorerWalkUpStairsLeft extends AnimatedSprite
{
	//Fields
    private Explorer explorer;

    //Constructor
    public ExplorerWalkUpStairsLeft(Explorer explorer)
    {
        super(explorer);
    	this.explorer = explorer;
        this.effect = true;
    }

    //Update
    public void Update(float delta)
    {
        this.explorer.getPosition().add(-this.explorer.getSpeed(), -this.explorer.getSpeed());
        this.explorer.getCollisionRectStairs().x = this.explorer.getPosition().x;
        this.explorer.getCollisionRectStairs().y = this.explorer.getPosition().y + 16;
        if (ExplorerManager.CollisionDetectionTopStairsLeft())
        {
            this.explorer.setState(this.explorer.getWalkLeft());
        }
        super.Update(delta);
    }

    //Draw
    public void Draw(float delta)
    {
        if (ExplorerManager.Debug())
        {
	    	this.explorer.getGame().getBatch().draw(this.explorer.getCollisionText(),
									            	this.explorer.getCollisionRectStairs().x,
									            	this.explorer.getCollisionRectStairs().y,
									            	this.explorer.getCollisionRectStairs().width,
									            	this.explorer.getCollisionRectStairs().height);
        }
        super.Draw(delta);
    }
}
