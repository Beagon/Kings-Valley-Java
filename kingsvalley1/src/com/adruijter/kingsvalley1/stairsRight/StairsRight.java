package com.adruijter.kingsvalley1.stairsRight;

import java.util.ArrayList;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.explorer.ExplorerManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2; 

public class StairsRight
{
	//Fields
    private KingsValley1 game;
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

    public StairsRight(KingsValley1 game, Vector2 position, int amountOfSteps, TextureRegion trapRight01,
    						TextureRegion trapRight02, TextureRegion trapTopRight02)
    {
        this.game = game;
        this.collisionText = new Texture("data/Stairs/collision_text.png");
        this.position = position.add(new Vector2(-amountOfSteps * 16, amountOfSteps * 16));
        this.amountOfSteps = amountOfSteps;
        this.stairs = new ArrayList<StepRight>();
        this.LoadContent(trapRight01, trapRight02, trapTopRight02);
    }

    /* Hier wordt de content geladen */
    private void LoadContent(TextureRegion trapRight01, TextureRegion trapRight02, TextureRegion trapTopRight02)
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
                                          trapRight01,
                                          '^'));
            this.stairs.add(new StepRight(this.game,
                                          new Vector2(this.position.x + (i+1) * 16, 
                                                      this.position.y - i * 16),
                                          trapRight02,
                                          '^'));
        }
        this.stairs.add(new StepRight(this.game, 
                                      new Vector2(this.position.x + (this.amountOfSteps + 1) * 16,
                                                  this.position.y - this.amountOfSteps * 16),
                                                  trapTopRight02,
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
