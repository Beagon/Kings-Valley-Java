package com.adruijter.kingsvalley1.explorer;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.math.Vector2;

public class ExplorerJumpLeft extends AnimatedSprite
{
	//Fields
    private Explorer explorer;
    private float startX, startY, a;
    private int startH, startK, h, k;
    private float collisionRectJumpLeftStartY;


    //Constructor
    public ExplorerJumpLeft(Explorer explorer, int h, int k)
    {
        super(explorer);
    	this.explorer = explorer;
        this.startK = k;
        this.startH = h;
        this.i = 0;
        this.effect = true;
    }

    public void Initialize()
    {
        this.startX = this.explorer.getPosition().x;
        this.startY = this.explorer.getPosition().y;
        this.h = (int)(this.startX + this.startH);
        this.k = (int)(this.startY - this.startK);
        this.a = this.CalculateA();
        this.explorer.getCollisionRectStairs().setWidth(2f);
        this.explorer.getCollisionRectStairs().setHeight(12f);
        this.collisionRectJumpLeftStartY = this.explorer.getCollisionRectJumpLeft().y;
    }

    private float CalculateA()
    {
        return (this.startY - this.k) / (float)Math.pow((double)(this.startX - this.h), 2d);
    }

    public void Update(float delta)
    {
        float x = this.explorer.getPosition().x - this.explorer.getSpeed();
        float y = (float)(this.a * Math.pow((x - this.h), 2d) + this.k);
        this.explorer.setPosition(new Vector2(x, y));
        
        if (x >= this.h)
        {
        	this.explorer.getCollisionRectStairs().setX(this.explorer.getCollisionRectStairs().x + 18f);
        	this.explorer.getCollisionRectJumpLeft().setY(this.collisionRectJumpLeftStartY);
        	
        }
        else if (x < this.h)
        {
        	this.explorer.getCollisionRectJumpLeft().setY(this.collisionRectJumpLeftStartY + 15);
        	//this.explorer.getCollisionRectStairs().setX(this.explorer.getCollisionRectStairs().x + 18f);
        	this.explorer.getCollisionRectStairs().setWidth(1f);
            this.explorer.getCollisionRectStairs().setHeight(17f);
        }
        
        if (ExplorerManager.CollisionDetectionGroundAfterJump())
        {
        	this.explorer.getCollisionRectStairs().setWidth(20f);
        	this.explorer.setPosition(new Vector2(x,
            									  this.explorer.getCollisionRectStairs().y + 
            									  this.explorer.getPixelsThroughFloor()));
        	this.explorer.getCollisionRectJumpLeft().setY(this.collisionRectJumpLeftStartY + 15);
           	if (KingsValley1.IsAndroid())
           	{
           		this.explorer.getWalkLeft().Initialize();
           		this.explorer.setState(this.explorer.getWalkLeft());
           	}
           	else
           	this.explorer.setState(this.explorer.getIdleLeft());
        }
        
        if (ExplorerManager.CollisionDetectionJumpLeft())
        {
        	this.explorer.getCollisionRectStairs().setX(this.explorer.getCollisionRectStairs().x + 19f);
        	this.explorer.getCollisionRectStairs().setHeight(17f);
        	this.explorer.setPosition(this.explorer.getPosition().add(this.explorer.getPixelsInWallLeft(),0f));
        	this.explorer.getIdleFallAfterJump().Initialize(true);
        	this.explorer.setState(this.explorer.getIdleFallAfterJump());
        }
        //base.Update(gameTime);
    }

    public void Draw(float delta)
    {
        super.Draw(delta);
    }
}
