package com.Orion.kingsvalley.animatedsprite;

import com.Orion.kingsvalley.explorer.Explorer;

public class AnimatedSprite
{
	//Fields
    private Explorer explorer;
    private int[] xValue = { 0, 18, 36, 54, 72, 90, 108, 126 };
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
        if (this.timer > 3f / 60f)
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
    	this.explorer.getGame().getBatch().draw(this.explorer.getTexture(),
								    			(int)this.explorer.getPosition().x,
								   				(int)this.explorer.getPosition().y,
								   				18f,
								   				32f,
								   				this.xValue[this.i],
                                   				0,
                                   				18,
                                   				32,
                                   				this.effect,
                                   				true);
    }
}
