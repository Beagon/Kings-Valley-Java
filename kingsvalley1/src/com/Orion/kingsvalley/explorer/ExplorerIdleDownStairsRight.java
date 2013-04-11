package com.Orion.kingsvalley.explorer;

import com.Orion.kingsvalley.animatedsprite.AnimatedSprite;

public class ExplorerIdleDownStairsRight extends AnimatedSprite
{
	//Fields

    //Constructor
    public ExplorerIdleDownStairsRight(Explorer explorer)
    {
    	super(explorer);
        this.i = 7;
        this.effect = true;
    }

    public void Update(float delta)
    {
      
    }

    public void Draw(float delta)
    {
        super.Draw(delta);
    }
}
