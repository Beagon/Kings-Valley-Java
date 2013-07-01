package com.adruijter.kingsvalley1.explorer;

import java.util.ArrayList;

import com.adruijter.kingsvalley1.customFunctions.Functions;
import com.adruijter.kingsvalley1.floor.Floor;
import com.adruijter.kingsvalley1.jewel.Jewel;
import com.adruijter.kingsvalley1.level.Level;
import com.adruijter.kingsvalley1.score.Score;
import com.adruijter.kingsvalley1.screens.GameScreen;
import com.adruijter.kingsvalley1.stairsRight.StairsRight;
import com.adruijter.kingsvalley1.stairsLeft.StairsLeft;
import com.adruijter.kingsvalley1.time.Time;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class ExplorerManager
{
	//Fields
    private static Explorer explorer;
    private static ArrayList<StairsRight> stairsRight;
    private static ArrayList<StairsLeft> stairsLeft;
    private static ArrayList<Floor> floors;
    private static ArrayList<Jewel> jewels;
    private static boolean debug = false;
    private static Level level;

    //Properties
    public static void setLevel(Level level)
    {
    	ExplorerManager.level = level;
    }
    
    public static boolean Debug()
    {
    	return debug;
    }
    public static void setExplorer(Explorer value)
    {
        explorer = value;
    }
    public static void setStairsRight(ArrayList<StairsRight> value)
    {
        stairsRight = value;
    }
    public static void setStairsLeft(ArrayList<StairsLeft> value)
    {
        stairsLeft = value;
    }
    
	public static void setFloors(ArrayList<Floor> floors) {
		ExplorerManager.floors = floors;
	}
	
	public static void setJewels(ArrayList<Jewel> jewels) {
		ExplorerManager.jewels = jewels;
	}
	
	
	public static boolean CollisionDetectionBottomStairsRight()
    {
        for (StairsRight stairs : stairsRight)
        {
            if (explorer.getCollisionRectStairs().overlaps(stairs.getCollisionRectBottom()))
            {
                if (explorer.getState().equals(explorer.getWalkRight()))
                {
                    int offset = 10;
                    if ( ((explorer.getCollisionRectStairs().x + explorer.getCollisionRectStairs().width) > 
                    	   stairs.getCollisionRectBottom().x + offset - 2) &&
                         ((explorer.getCollisionRectStairs().x + explorer.getCollisionRectStairs().width) < 
                           stairs.getCollisionRectBottom().x + offset + 2))
                    {
                        return true;
                    }
                }
                else if(explorer.getState().equals(explorer.getWalkDownStairsRight()))
                {
                    int offset = 0;
                    if ((explorer.getCollisionRectStairs().y > stairs.getCollisionRectBottom().y + offset - 3) &&
                        (explorer.getCollisionRectStairs().y < stairs.getCollisionRectBottom().y + offset + 5))
                    {
                    	explorer.setPosition( new Vector2(explorer.getPosition().x, stairs.getCollisionRectBottom().y - stairs.getCollisionRectBottom().height ));
                    	return true;
                    } 
                }
            }
        }
        return false;
    }
    public static boolean CollisionDetectionTopStairsRight()
    {
        
        for (StairsRight stairs : stairsRight)
        {
            if (explorer.getCollisionRectStairs().overlaps(stairs.getCollisionRectTop()))
            {
                if (explorer.getState().equals(explorer.getWalkLeft()))
                {
                    int offset = -10;
                    if ( (explorer.getCollisionRectStairs().x > stairs.getCollisionRectTop().x + stairs.getCollisionRectTop().width + offset - 3) &&
                         (explorer.getCollisionRectStairs().x < stairs.getCollisionRectTop().x + stairs.getCollisionRectTop().width + offset + 3))
                    {
                        return true;
                    }
                }
                else if (explorer.getState().equals(explorer.getWalkUpStairsRight()))
                {
                	int offset = 5;
                    if ((explorer.getCollisionRectStairs().y < (stairs.getCollisionRectTop().y - stairs.getCollisionRectTop().height + offset ) &&
                        (explorer.getCollisionRectStairs().y > (stairs.getCollisionRectTop().y - stairs.getCollisionRectTop().height - offset ))))
                    {
                        explorer.setPosition( new Vector2(explorer.getPosition().x, stairs.getCollisionRectTop().y - 2 * stairs.getCollisionRectTop().height ));
                    	return true;
                    }                	
                }
            }

        }
        return false;
    }
    
  
    public static boolean CollisionDetectionBottomStairsLeft()
    {
        for (StairsLeft stairs : stairsLeft)
        {
            if (explorer.getCollisionRectStairs().overlaps(stairs.getCollisionRectBottom()))
            {
                if (explorer.getState().equals(explorer.getWalkLeft()))
                {
                    int offset = -10;
                    if ((explorer.getCollisionRectStairs().x > stairs.getCollisionRectBottom().x + stairs.getCollisionRectBottom().width + offset - 2) &&
                        (explorer.getCollisionRectStairs().x < stairs.getCollisionRectBottom().x + stairs.getCollisionRectBottom().width + offset + 2)) 
                    {
                        return true;
                    }
                }
                else if (explorer.getState().equals(explorer.getWalkDownStairsLeft()))
                {
                    int offset = 0;
                    if ((explorer.getCollisionRectStairs().y > stairs.getCollisionRectBottom().y + offset - 2) &&
                         (explorer.getCollisionRectStairs().y < stairs.getCollisionRectBottom().y + offset + 5))
                    {
                    	explorer.setPosition( new Vector2(explorer.getPosition().x, stairs.getCollisionRectBottom().y - stairs.getCollisionRectBottom().height ));
                    	return true;
                    } 
                }
            }
        }
        return false;
    }


    public static boolean CollisionDetectionTopStairsLeft()
    {
        for (StairsLeft stairs : stairsLeft)
        {
            if (explorer.getCollisionRectStairs().overlaps(stairs.getCollisionRectTop()))
            {
                if (explorer.getState().equals(explorer.getWalkRight()))
                {
                    int offset = 10;
                    if ((explorer.getCollisionRectStairs().x + explorer.getCollisionRectStairs().width > stairs.getCollisionRectTop().x + offset - 3) &&
                        (explorer.getCollisionRectStairs().x + explorer.getCollisionRectStairs().width < stairs.getCollisionRectTop().x + offset + 3))
                    {
                        return true;
                    }
                }
                else if (explorer.getState().equals(explorer.getWalkUpStairsLeft()))
                {
                    int offset = 5;
                    if ((explorer.getCollisionRectStairs().y < (stairs.getCollisionRectTop().y - stairs.getCollisionRectTop().height + offset ) &&
                        (explorer.getCollisionRectStairs().y > (stairs.getCollisionRectTop().y - stairs.getCollisionRectTop().height - offset ))))
                    {
                        explorer.setPosition( new Vector2(explorer.getPosition().x, stairs.getCollisionRectTop().y - 2 * stairs.getCollisionRectTop().height ));
                     	return true;
                    }
                }
            }

        }
        return false;
    }
    
    public static boolean CollisionDetectionGroundAfterJump()
    {
    	//Check voor iedere vloer of...
    	for (Floor floor : floors)
    	{
	    		//de botsrechthoek overlap heeft met de botsrechthoek van deze ene vloer...
    			if ( explorer.getCollisionRectStairs().overlaps(floor.getCollisionRectangle())
	    				/* en of de explorer zich boven de vloer bevindt 
    					   (geen botsing detecteren met een vloer boven de explorer.*/
    					&& explorer.getCollisionRectStairs().y < floor.getCollisionRectangle().y)
	    		{
	    			/* Bereken dan hoeveel pixels de explorer rechthoek in de rechthoek van de vloer
    				   is gezakt... */
    				float pixelsThroughFloor = floor.getCollisionRectangle().y - 
	    							 explorer.getCollisionRectStairs().y - 
	    							 explorer.getCollisionText().getHeight() + 3;
	    			// Geef deze waarde door aan de console voor debuggen...
    				//Gdx.app.log("diff", Float.toString(pixelsThroughFloor));
	    			//Geef deze waarde door aan het veld setPixelsThroughFloor van de explorer class
    				explorer.setPixelsThroughFloor(pixelsThroughFloor);
	    			/* Bevestig dat er een botsing heeft plaatsgevonden tussen de explorer en de vloer
    				   aan de ExplorerJumpLeft of ExplorerJumpRight class */
    				return true;
	    		}  
    	}
    	return false;
    }
    
    public static boolean CollisionDetectionFallOfFloorLeft()
    {
    	
    	for (Floor floor : floors)
    	{
	    		if ( explorer.getCollisionRectStairs().overlaps(floor.getCollisionRectangle()))
	    		{
	    			if (explorer.getCollisionRectStairs().x + explorer.getCollisionRectStairs().getWidth() - 8 < floor.getCollisionRectangle().x)
	    			{
	    				if (floor.getHighOrLowFallLeft() == '{')
    					{
    						explorer.getFallOfHighFloorSound().play(0.6f);
    					}
    					else if (floor.getHighOrLowFallLeft() == '[')
    					{
    						explorer.getFallOfLowFloorSound().play(0.6f);
    					}
	    				return true;
	    			}	    			
	    		}  
    	}
    	return false;
    }
    
    public static boolean CollisionDetectionFallOfFloorRight()
    {
    	//Kijk voor elke vloer of...
    	for (Floor floor : floors)
    	{
	    		//er een overlap is van de beide collisionrectangles van de explorer en de betreffende vloer
    			if ( explorer.getCollisionRectStairs().overlaps(floor.getCollisionRectangle()))
	    		{
	    			//als de linkerkant + 2 van de explorer rectangle groter is dan de linkerkant van de
    				//floor rectangle dan moet je true teruggeven
    				if (explorer.getCollisionRectStairs().x + 4 > 
    					(floor.getCollisionRectangle().x + floor.getCollisionRectangle().getWidth()))
	    			{
    					floor.setColor(new Color(0f,1f,0f,1f));
    					if (floor.getHighOrLowFallRight() == '}')
    					{
    						explorer.getFallOfHighFloorSound().play(0.6f);
    					}
    					else if (floor.getHighOrLowFallRight() == ']')
    					{
    						explorer.getFallOfLowFloorSound().play(0.6f);
    					}
    					return true;
	    			}	    			
	    		}  
    	}
    	return false;
    }
    
    public static boolean CollisionDetectionWallInFrontRight()
    {
    	for (Floor floor : floors)
    	{
    		if (explorer.getCollisionRectStairs().overlaps(floor.getCollisionRectangle()))
    		{    				
				if ((explorer.getCollisionRectStairs().x + explorer.getCollisionRectStairs().getWidth()) >
					 floor.getCollisionRectangle().x) 
				{
					if ((explorer.getCollisionRectStairs().x + explorer.getCollisionRectStairs().getWidth()) < 
			    		(floor.getCollisionRectangle().x  + floor.getCollisionRectangle().getWidth()))
					{
						if ((explorer.getPosition().y + 2 * explorer.getCollisionRectStairs().getHeight()) >
		    				(floor.getCollisionRectangle().y + floor.getCollisionRectangle().getHeight()))
						{
							float inWall = floor.getCollisionRectangle().x - (explorer.getCollisionRectStairs().x +
										 explorer.getCollisionRectStairs().getWidth());
							explorer.setPixelsInWallRight(inWall);
							return true;
						}
					}    					
				}
    		}
    	}
    	return false;
    }
    
    public static boolean CollisionDetectionWallInFrontLeft()
    {
    	//Kijk voor iedere floor.....
    	for (Floor floor : floors)
    	{
    		//Of er overlop is tussen de collisionrectangle van de explorer en een specifieke floor....
    		if (explorer.getCollisionRectStairs().overlaps(floor.getCollisionRectangle()))
    		{    				
				//Check of de 
    			if ((explorer.getCollisionRectStairs().x < floor.getCollisionRectangle().x + 
						floor.getCollisionRectangle().getWidth()))
				{
					if (explorer.getCollisionRectStairs().x > floor.getCollisionRectangle().x)
					{
						if ((explorer.getPosition().y + 2 * explorer.getCollisionRectStairs().getHeight()) >
		    				(floor.getCollisionRectangle().y + floor.getCollisionRectangle().getHeight()))
						{
							float inWall = ((floor.getCollisionRectangle().x + 
													floor.getCollisionRectangle().getWidth()) -
														explorer.getCollisionRectStairs().x);
							explorer.setPixelsInWallLeft(inWall);
							return true;
						}
					}    					
				}
    		}
    	}
    	return false;
    }
    
    public static boolean CollisionDetectionJumpRight()
    {
    	for (Floor floor : floors)
    	{
    		if (explorer.getCollisionRectJumpRight().overlaps(floor.getCollisionRectangle()))
    		{
    			float inWall = floor.getCollisionRectangle().x - (explorer.getCollisionRectJumpRight().x +
    							explorer.getCollisionRectJumpRight().getWidth());
    			explorer.setPixelsInWallRight(inWall);
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public static boolean CollisionDetectionJumpLeft()
    {
    	for (Floor floor : floors)
    	{
    		if (explorer.getCollisionRectJumpLeft().overlaps(floor.getCollisionRectangle()))
    		{
    			float inWall = (floor.getCollisionRectangle().x + floor.getCollisionRectangle().getWidth()) - explorer.getCollisionRectJumpLeft().x;
    			explorer.setPixelsInWallLeft(inWall);
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public static boolean CollisionDetectionExplorerJewels()
    {
		String response;
    	for (Jewel jewel : jewels)
    	{
    		if (explorer.getCollisionRectStairs().overlaps(jewel.getCollisionRectangle()))
    		{
    			jewels.remove(jewel);
    			Score.setGameScore(Score.getGameScore() + 500);
    			Score.AdjustScore(level);
    			if(Score.getGameScore() >= Score.getHighScore()){
    			Score.setHighScore(Score.getHighScore() + 500);
    			Score.AdjustHighScore(level);
    			}
	    			if(jewels.isEmpty()){ //If all the jewels are gone, what do?!
	    		int points = Time.getGameTime() * 10;
	    		Time.setGameTime(0);
	    		Time.AdjustTime(level);
	    		int finalscore = points + Score.getGameScore();
    			Score.setGameScore(finalscore);
    			Score.AdjustScore(level);	    		
    			if(Score.getGameScore() >= Score.getHighScore()){
    			Score.setHighScore(Score.getHighScore());
    			Score.AdjustHighScore(level);
    			}
    			if(level.getGame().getLevelIndex() >= 1){
    			level.getGame().setScreen(level.getGame().getEndScreen());
	    		System.out.println("You won the game, submiting your score of "+Score.getGameScore()+" to highscore.");
	    		response = Functions.getUrlSource("http://www.struckbythunder.net/KingsValley/SetHighscore.php?s=" + Score.getGameScore());
	    		System.out.println("You've ended up as #" + response + " on the highscore!");
    			level.getMasterMelody().stop();
    		    level.setMasterMelody((Gdx.audio.newMusic(Gdx.files.internal("data/Sound/intro.mp3"))));
    		    level.getMasterMelody().play();
    		    level.getMasterMelody().setLooping(false);
    			}else{
    			level.getMasterMelody().stop();
    		    level.setMasterMelody((Gdx.audio.newMusic(Gdx.files.internal("data/Sound/exitLevelTune.mp3"))));
    		    level.getMasterMelody().play();
    		    level.getMasterMelody().setLooping(false);
    			level.getGame().setLevelIndex(level.getLevelIndex() + 1);
    		    GameScreen gameScreen = new GameScreen(level.getGame());
    			level.getGame().setGameScreen(gameScreen);
        		level.getGame().setScreen(level.getGame().getGameScreen());   
        		Time.setGameTime(300);
        		Time.AdjustTime(level);
    			}
	    			}
    			return true;
    		}

    	}
    	return false;
    }
    
    public static boolean CollisionDetectionStartWalkDownStairs()
    {
    	for (Floor floor : floors)
    	{
    		if (explorer.getCollisionRectStairs().overlaps(floor.getCollisionRectangle()))
    		{
    			float pixelsThroughFloor = floor.getCollisionRectangle().y - 
						 					explorer.getCollisionRectStairs().y - 
						 							explorer.getCollisionText().getHeight()/2;    			
    			explorer.setPixelsThroughFloor(pixelsThroughFloor);
    			return true;
    		}
    	}
    	return false;
    }
    
}
