package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.math.Vector2;

public class ExplorerJumpRight extends AnimatedSprite
{
	 //Fields
    private Explorer explorer;
    private float startX, startY, a;
    private int startH, startK, h, k;
    private float collisionRectJumpRightStartY;


    //Constructor
    public ExplorerJumpRight(Explorer explorer, int h, int k)
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
        this.explorer.getCollisionRectStairs().setWidth(2f);
        this.collisionRectJumpRightStartY = this.explorer.getCollisionRectJumpRight().y;
        
    }

    private float CalculateA()
    {
        return (this.startY -this.k) / (float)Math.pow((double)(this.startX - this.h), 2d);
    }

    public void Update(float delta)
    {
        float x = this.explorer.getPosition().x + this.explorer.getSpeed();
        float y = (float)(this.a * Math.pow((x - this.h), 2d) + this.k); 
        this.explorer.setPosition(new Vector2(x,y));
        
        if (x <= this.h)
        {
        	this.explorer.getCollisionRectJumpRight().setY(this.collisionRectJumpRightStartY);
        	this.explorer.getCollisionRectStairs().setHeight(10f);
        }
        else if (x > this.h)
        {
        	this.explorer.getCollisionRectJumpRight().setY(this.collisionRectJumpRightStartY + 15);
        	this.explorer.getCollisionRectStairs().setX(this.explorer.getPosition().x + 18f);
        	this.explorer.getCollisionRectStairs().setWidth(1f);
        	this.explorer.getCollisionRectStairs().setHeight(17f);
        }
        
        
        if (ExplorerManager.CollisionDetectionGroundAfterJump())
        {
            this.explorer.getCollisionRectStairs().setWidth(19f);        	
        	this.explorer.setPosition(new Vector2(x,
            									  this.explorer.getCollisionRectStairs().y + 
            									  this.explorer.getPixelsThroughFloor()));
        	this.explorer.getCollisionRectJumpRight().setY(this.collisionRectJumpRightStartY + 15);
        	if (KingsValley1.IsAndroid())
        	{
        		this.explorer.getWalkRight().Initialize();
        		this.explorer.setState(this.explorer.getWalkRight());
        	}
           	else
           	this.explorer.setState(this.explorer.getIdleRight());
        }
        
        if (ExplorerManager.CollisionDetectionJumpRight())
        {
        	this.explorer.setPosition(this.explorer.getPosition().add(this.explorer.getPixelsInWallRight(),0f));
        	this.explorer.getCollisionRectStairs().setHeight(17f);
        	this.explorer.getIdleFallAfterJump().Initialize(false);
        	this.explorer.setState(this.explorer.getIdleFallAfterJump());
        }
        //base.Update(gameTime);
    }

    public void Draw(float delta)
    {
        super.Draw(delta);
    }
	
}
