package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;

public class ExplorerIdleUpStairsLeft extends AnimatedSprite
{
	 //Fields

    //Constructor
    public ExplorerIdleUpStairsLeft(Explorer explorer)
    {
        super(explorer);
        this.effect = true;
        this.i = 7;
    }

    public void Update(float delta)
    {
        
    }

    public void Draw(float delta)
    {
        super.Draw(delta);
    }
}
