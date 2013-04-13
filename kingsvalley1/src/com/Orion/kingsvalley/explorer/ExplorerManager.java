package com.Orion.kingsvalley.explorer;

import java.util.ArrayList;

import com.Orion.kingsvalley.floor.Floor;
import com.Orion.kingsvalley.stairsLeft.StairsLeft;
import com.Orion.kingsvalley.stairsRight.StairsRight;
import com.badlogic.gdx.math.Vector2;

public class ExplorerManager
{
	//Fields
    private static Explorer explorer;
    private static ArrayList<StairsRight> stairsRight;
    private static ArrayList<StairsLeft> stairsLeft;
    private static boolean debug = false;
    private static ArrayList<Floor> floors;

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
	public static ArrayList<Floor> getFloors() {
		return floors;
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

}
