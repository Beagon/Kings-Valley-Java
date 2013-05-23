package com.adruijter.kingsvalley1.explorer;

import java.util.ArrayList;

import com.adruijter.kingsvalley1.floor.Floor;
import com.adruijter.kingsvalley1.stairsRight.StairsRight;
import com.adruijter.kingsvalley1.stairsLeft.StairsLeft;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ExplorerManager
{
	//Fields
    private static Explorer explorer;
    private static ArrayList<StairsRight> stairsRight;
    private static ArrayList<StairsLeft> stairsLeft;
    private static ArrayList<Floor> floors;
    private static boolean debug = false;

    //Properties
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
    				Gdx.app.log("diff", Float.toString(pixelsThroughFloor));
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
	    			if (explorer.getCollisionRectStairs().x + 14 < floor.getCollisionRectangle().x)
	    			{
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
    				if (explorer.getCollisionRectStairs().x + 2 > 
    					(floor.getCollisionRectangle().x + floor.getCollisionRectangle().getWidth()))
	    			{
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
				if ((explorer.getPosition().x + explorer.getCollisionRectStairs().getWidth()) >
					 floor.getCollisionRectangle().x) 
				{
					if ((explorer.getPosition().x + explorer.getCollisionRectStairs().getWidth()) < 
			    		(floor.getCollisionRectangle().x  + floor.getCollisionRectangle().getWidth()))
					{
						if ((explorer.getPosition().y + 2 * explorer.getCollisionRectStairs().getHeight()) >
		    				(floor.getCollisionRectangle().y + floor.getCollisionRectangle().getHeight()))
						{
							float inWall = floor.getCollisionRectangle().x - (explorer.getCollisionRectStairs().x +
										 explorer.getCollisionRectStairs().getWidth());
							explorer.setPixelsInWallRight(inWall - 4);
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
    	//Loops trough floors to see if the collision rectangles of the explorer and a wall collide.
    	for (Floor floor : floors)
    	{
    		if (explorer.getCollisionRectStairs().overlaps(floor.getCollisionRectangle()))
    		{    				
    			if ((explorer.getPosition().x < floor.getCollisionRectangle().x + floor.getCollisionRectangle().getWidth())){
    				if((explorer.getPosition().x > floor.getCollisionRectangle().x)){
    					if((explorer.getPosition().y + 2 * explorer.getCollisionRectStairs().getHeight()) > 
    						(floor.getCollisionRectangle().y + floor.getCollisionRectangle().getHeight())){
    						float inWall = ((floor.getCollisionRectangle().x + floor.getCollisionRectangle().getWidth()) - explorer.getCollisionRectStairs().x);
    						explorer.setPixelsInWallLeft(inWall + 4);
    						return true;
    					}
    				}
    			
				}
    		}
    	}
    	return false;
    }
    
}
