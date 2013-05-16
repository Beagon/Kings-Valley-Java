package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.math.Vector2;

public class ExplorerJumpIdleRight extends AnimatedSprite
{
	 //Fields
    private Explorer explorer;
    private float startX, startY, a, x;
    private int startH, startK, h, k;


    //Constructor
    public ExplorerJumpIdleRight(Explorer explorer, int h, int k)
    {
        super(explorer);
    	this.explorer = explorer;
        this.startK = k;
        this.startH = h;
        this.i = 0;
    }

    public void Initialize()
    {
        this.startX = this.explorer.getPosition().x;
        this.startY = this.explorer.getPosition().y;
        this.h = (int)(this.startX + this.startH);
        this.k = (int)(this.startY - this.startK);
        this.a = this.CalculateA();
        this.x = this.startX;
    }

    private float CalculateA()
    {
        return (this.startY - this.k) / (float)Math.pow((double)(this.startX - this.h), 2d);
    }

    public void Update(float delta)
    {
        
        this.x =  this.x + this.explorer.getSpeed();
        float y = (float)(this.a * Math.pow((this.x - this.h), 2d) + this.k);
        float x = this.startX;
        this.explorer.setPosition(new Vector2(x, y));
        if (this.explorer.getPosition().y > this.startY)
        {
            this.explorer.setPosition(new Vector2(x, startY));
            this.explorer.setState(this.explorer.getIdleRight());

        }
        //base.Update(gameTime);
    }

    public void Draw(float delta)
    {
        super.Draw(delta);
    }
}
