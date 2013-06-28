package com.adruijter.kingsvalley1.time;


import com.adruijter.kingsvalley1.level.Level;
import com.adruijter.kingsvalley1.character.Character;
import com.badlogic.gdx.math.Vector2;

public class Time
{
	private static int gameTime = 300;
/*	private static boolean isOn = true;

	public static boolean isOn() {
		return isOn;
	}

	public static void setOn(boolean isOn) {
		Time.isOn = isOn;
	}*/

	public static int getGameTime() {
		return gameTime;
	}


	public static void setGameTime(int gameTime) {
		Time.gameTime = gameTime;
	}
	
	
	public static void AdjustTime(Level level)
	{
		if(gameTime >= 0){		
		String theTime = Integer.toString(gameTime);

		//Get the difference between the 2 arrays
		int diff = level.getTime().size() - theTime.length();
		
		//Now we build the Time string form left to right
		for (int i = 0; i < diff; i++)
		{
			
			level.getTime().set(i, new Character(level.getGame(),
												  level.getTime().get(i).getPosition(),
												  level.getRegion().get("0"),
												  '0'));
		}
		
		for (int i = diff; i < level.getTime().size(); i++)
		{
			level.getTime().set(i, new Character(level.getGame(),
												  new Vector2(level.getTime().get(i).getPosition().x,
														  	  level.getTime().get(i).getPosition().y),
												  level.getRegion().get(String.valueOf(theTime.charAt(i - diff))),
												  theTime.charAt(i - diff)));
		}		
		}
	}




}
