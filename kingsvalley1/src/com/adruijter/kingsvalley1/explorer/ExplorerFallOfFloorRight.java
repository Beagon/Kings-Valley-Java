package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.math.Vector2;



public class ExplorerFallOfFloorRight extends AnimatedSprite
{
	//Fields
    private Explorer explorer;
    private float startX, startY, a, prev_y;
    private int startH, startK, h, k;


    //Constructor
    public ExplorerFallOfFloorRight(Explorer explorer, int h, int k)
    {
        super(explorer);
    	this.explorer = explorer;
        this.startK = k;
        this.startH = h;
        this.i = 0;
        this.effect = false;
    }

    public void Initialize()
    {
        this.startX = this.explorer.getPosition().x;
        this.startY = this.explorer.getPosition().y;
        this.h = (int)(this.startX + this.startH);
        this.k = (int)(this.startY - this.startK);
        this.prev_y = this.startY;
        this.a = this.CalculateA();
        
        //29-5 9:47 test
        this.explorer.getCollisionRectStairs().setWidth(10f);
        this.explorer.getCollisionRectStairs().setX(this.explorer.getPosition().x + 10);
        //einde
    }

    private float CalculateA()
    {
        return (this.startY - this.k) / (float)Math.pow((double)(this.startX - this.h), 2d);
    }

    public void Update(float delta)
    {
        float x = this.explorer.getPosition().x + this.explorer.getSpeed();
        float y = this.explorer.getPosition().y;
        if ( (this.explorer.getPosition().y - this.prev_y)< 10)
        {
        	this.prev_y = y;
        	y = (float)(this.a * Math.pow((x - this.h), 2d) + this.k);
        	this.explorer.setPosition(new Vector2(x, y));
        }
        else
        {
        	this.explorer.setPosition(this.explorer.getPosition().add(0, 10f));
        } 
        
        this.explorer.getCollisionRectStairs().setWidth(10f);
        this.explorer.getCollisionRectStairs().setX(this.explorer.getCollisionRectStairs().x + 10);
        
       	this.explorer.getCollisionRectJumpLeft().setX(this.explorer.getPosition().x + 10);
        
        if (ExplorerManager.CollisionDetectionGroundAfterJump())
        {
            this.explorer.setPosition(new Vector2(x,
            									  this.explorer.getCollisionRectStairs().y + 
            									  this.explorer.getPixelsThroughFloor()));
            //test 10:51 29-5
            //this.explorer.getCollisionRectStairs().setWidth(20f);
            this.explorer.getCollisionRectStairs().setWidth(8f);
            this.explorer.getCollisionRectStairs().setX(this.explorer.getCollisionRectStairs().x + 12);
            
            //test
            this.explorer.getCollisionRectJumpLeft().setX(this.explorer.getPosition().x + 10);
            
           	if (KingsValley1.IsAndroid())
           	{
           		this.explorer.getWalkRight().Initialize();
           		this.explorer.setState(this.explorer.getWalkRight());
           	}
           	else
           	{
           		this.explorer.getIdleRight().Initialize();
           		this.explorer.setState(this.explorer.getIdleRight());
           	}
        }
        //base.Update(gameTime);
    }

    public void Draw(float delta)
    {
    	/*
    	this.explorer.getGame().getBatch().setColor(0f, 1f, 0f, 1f);
		this.explorer.getGame().getBatch().draw(this.explorer.getCollisionText(), this.explorer.getCollisionRectStairs().x, this.explorer.getCollisionRectStairs().y, 
				this.explorer.getCollisionRectStairs().getWidth(), this.explorer.getCollisionRectStairs().getHeight());
		this.explorer.getGame().getBatch().setColor(1f, 1f, 1f, 1f);*/
    	super.Draw(delta);
    }
}
