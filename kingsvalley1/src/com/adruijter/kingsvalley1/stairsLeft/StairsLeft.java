package com.adruijter.kingsvalley1.stairsLeft;

import java.util.ArrayList;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.explorer.ExplorerManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class StairsLeft
{
	//Fields
    private KingsValley1 game;
    private Vector2 position;
    private int amountOfSteps;
    private ArrayList<StepLeft> stairs;
    private Rectangle collisionRectBottom, collisionRectTop;
    private Texture collisionText;

    //Properties
    public Rectangle getCollisionRectBottom()
    {
        return this.collisionRectBottom;
    }
    public Rectangle getCollisionRectTop()
    {
        return this.collisionRectTop;
    }
    
    
    public StairsLeft(KingsValley1 game, Vector2 position, int amountOfSteps,
    			TextureRegion trapLeft01, TextureRegion trapLeft02, TextureRegion trapTopLeft02)
    {
        this.game = game;
        this.collisionText = new Texture("data/Stairs/collision_text.png");
        this.position = position.add(new Vector2(amountOfSteps * 16, amountOfSteps * 16));
        this.amountOfSteps = amountOfSteps;
        this.stairs = new ArrayList<StepLeft>();
        this.LoadContent(trapLeft01, trapLeft02, trapTopLeft02);
    }

    //Hier wordt de content geladen
    private void LoadContent(TextureRegion trapLeft01, TextureRegion trapLeft02, TextureRegion trapTopLeft02)
    {
        this.collisionRectBottom = new Rectangle(this.position.x + 16f,
                                                 this.position.y,
                                                 16f,
                                                 16f);
        for (int i = 0; i < this.amountOfSteps; i++)
        {
            
            this.stairs.add(new StepLeft(this.game,
                                          new Vector2(this.position.x - i * 16f,
                                                      this.position.y - i * 16f),
                                          trapLeft01,
                                          '^'));
            
            this.stairs.add(new StepLeft(this.game,
                                          new Vector2(this.position.x - (i - 1) * 16f, 
                                                      this.position.y - i * 16f),
                                          trapLeft02,
                                          '^'));
        }
        this.stairs.add(new StepLeft(this.game, 
                                      new Vector2(this.position.x - (this.amountOfSteps - 1) * 16f,
                                                  this.position.y - this.amountOfSteps * 16f),
                                                  trapTopLeft02,
                                                  '^'));
        this.collisionRectTop = new Rectangle(this.position.x - (this.amountOfSteps - 1) * 16f,
                                              this.position.y - this.amountOfSteps * 16f,
                                              16f,
                                              16f);

    }

    public void Draw(float delta)
    {
        for (StepLeft step : this.stairs)
        {
            step.Draw(delta);
        }
        if (ExplorerManager.Debug())
        {
	        this.game.getBatch().setColor(0f, 0f, 1f, 0.5f);
	        this.game.getBatch().draw(this.collisionText,
	        						  this.collisionRectBottom.x,
	        						  this.collisionRectBottom.y,
	        						  this.collisionRectBottom.getWidth(),
	        						  this.collisionRectBottom.getHeight());
	        this.game.getBatch().draw(this.collisionText,
		        					  this.collisionRectTop.x,
		        					  this.collisionRectTop.y,
		        					  this.collisionRectTop.getWidth(),
		        					  this.collisionRectTop.getHeight());
	        this.game.getBatch().setColor(1f, 1f, 1f, 1f);
        }
    }
}
