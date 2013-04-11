package com.Orion.kingsvalley.stairsRight;

import java.util.ArrayList;

import com.Orion.kingsvalley.KingsValley;
import com.Orion.kingsvalley.explorer.ExplorerManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2; 

public class StairsRight
{
	//Fields
    private KingsValley game;
    private Vector2 position;
    private int amountOfSteps;
    private ArrayList<StepRight> stairs;
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

    public StairsRight(KingsValley game, Vector2 position, int amountOfSteps)
    {
        this.game = game;
        this.collisionText = new Texture("data/Stairs/collision_text.png");
        this.position = position.add(new Vector2(-amountOfSteps * 16, amountOfSteps * 16));
        this.amountOfSteps = amountOfSteps;
        this.stairs = new ArrayList<StepRight>();
        this.LoadContent();
    }

    /* Hier wordt de content geladen */
    private void LoadContent()
    {
        this.collisionRectBottom = new Rectangle(this.position.x,
                                                 this.position.y,
                                                 16f,
                                                 16f);
        for (int i = 0; i < this.amountOfSteps; i++)
        {
            this.stairs.add(new StepRight(this.game,
                                          new Vector2(this.position.x + i * 16,
                                                      this.position.y - i * 16),
                                          "trapRight01.png",
                                          '^'));
            this.stairs.add(new StepRight(this.game,
                                          new Vector2(this.position.x + (i+1) * 16, 
                                                      this.position.y - i * 16),
                                          "trapRight02.png",
                                          '^'));
        }
        this.stairs.add(new StepRight(this.game, 
                                      new Vector2(this.position.x + (this.amountOfSteps + 1) * 16,
                                                  this.position.y - this.amountOfSteps * 16),
                                                  "trapTopRight02.png",
                                                  '^'));
        this.collisionRectTop = new Rectangle(this.position.x + (this.amountOfSteps) * 16,
                                              this.position.y - this.amountOfSteps * 16,
                                              16,
                                              16);

    }

    public void Draw(float delta)
    {
        for (StepRight step : this.stairs)
        {
            step.Draw(delta);
        }
        if (ExplorerManager.Debug())
        {
	        this.game.getBatch().setColor(0f, 1f, 0f, 0.3f);
	        this.game.getBatch().draw(this.collisionText,
	        						  this.collisionRectBottom.x,
	        						  this.collisionRectBottom.y,
	        						  this.collisionRectBottom.width,
	        						  this.collisionRectBottom.height);
	        this.game.getBatch().draw(this.collisionText,
	        						  this.collisionRectTop.x,
	        						  this.collisionRectTop.y,
	        						  this.collisionRectTop.width,
	        						  this.collisionRectTop.height);
	        this.game.getBatch().setColor(1f, 1f, 1f, 1f);
        }
    }

}
