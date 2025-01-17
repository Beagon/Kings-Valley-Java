package com.adruijter.kingsvalley1.animatedsprite;

import com.adruijter.kingsvalley1.explorer.Explorer;

public class AnimatedSprite
{
	//Fields
    protected Explorer explorer;
    protected int i = 0;
    private float timer = 0;
    protected boolean effect = false;

    //Constructor
    public AnimatedSprite(Explorer explorer)
    {
        this.explorer = explorer;
    }

    //Update
    public void Update(float delta)
    {
        this.timer += delta;
        if (this.timer > 5f / 60f)
        {
            this.i++;
            this.timer = 0f;
            if (this.i > 7)
            {
                this.i = 0;
            }
        }
    }

    //Draw
    public void Draw(float delta)
    {
    	
    	if (this.effect)
    	{
    		if (!this.explorer.getRegion().get("explorer" + Integer.toString(this.i)).isFlipX())
    		this.explorer.getRegion().get("explorer" + Integer.toString(this.i)).flip(true, false);    		
    	}
    	else
    	{
    		if (this.explorer.getRegion().get("explorer" + Integer.toString(this.i)).isFlipX())
    		this.explorer.getRegion().get("explorer" + Integer.toString(this.i)).flip(true, false); 
    	}
    	this.explorer.getGame().getBatch().draw(this.explorer.getRegion().get("explorer" + Integer.toString(this.i)),
								    			(int)this.explorer.getPosition().x,
								   				(int)this.explorer.getPosition().y,
								   				18f,
								   				32f);
    }
}
