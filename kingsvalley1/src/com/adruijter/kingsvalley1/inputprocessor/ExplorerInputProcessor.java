package com.adruijter.kingsvalley1.inputprocessor;

import com.adruijter.kingsvalley1.explorer.Explorer;
import com.adruijter.kingsvalley1.level.Level;

//import com.adruijter.kingsvalley1.screens.GameScreen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class ExplorerInputProcessor implements InputProcessor
{
	//Fields
	//private Level level;
	private Explorer explorer;
	
	//Constructor
	public ExplorerInputProcessor(Level level)
	{
		//this.level = level;
		this.explorer = level.getExplorer();
	}

	@Override
	public boolean keyDown(int keycode) 
	{
		switch(keycode)
		{
			case Keys.RIGHT:
				if (this.explorer.getState().equals(this.explorer.getIdleRight()) ||
					this.explorer.getState().equals(this.explorer.getIdleLeft()) ||
					this.explorer.getState().equals(this.explorer.getWalkLeft()))
				{
					this.explorer.setState(this.explorer.getWalkRight());
				}
				else if (this.explorer.getState().equals(this.explorer.getIdleUpStairsRight()) ||
						 this.explorer.getState().equals(this.explorer.getIdleDownStairsRight()))
				{
					this.explorer.setState(this.explorer.getWalkUpStairsRight());
				}
				else if (this.explorer.getState().equals(this.explorer.getIdleUpStairsLeft()) ||
						 this.explorer.getState().equals(this.explorer.getIdleDownStairsLeft()))
				{
					this.explorer.setState(this.explorer.getWalkDownStairsLeft());
				}
				break;	
			case Keys.LEFT:
				if (this.explorer.getState().equals(this.explorer.getIdleLeft()) ||
					this.explorer.getState().equals(this.explorer.getIdleRight())||
					this.explorer.getState().equals(this.explorer.getWalkRight()))
				{
					this.explorer.setState(this.explorer.getWalkLeft());
				}
				else if (this.explorer.getState().equals(this.explorer.getIdleUpStairsLeft()) ||
						 this.explorer.getState().equals(this.explorer.getIdleDownStairsLeft()))
				{
					this.explorer.setState(this.explorer.getWalkUpStairsLeft());
				}
				else if (this.explorer.getState().equals(this.explorer.getIdleUpStairsRight()) ||
						this.explorer.getState().equals(this.explorer.getIdleDownStairsRight()))
				{
					this.explorer.setState(this.explorer.getWalkDownStairsRight());
					return true;
				}
				break;
			case Keys.SPACE:
				if (this.explorer.getState().equals(this.explorer.getWalkRight()))
				{
					this.explorer.getJumpRight().Initialize();
					this.explorer.setState(this.explorer.getJumpRight());
				}
				else if (this.explorer.getState().equals(this.explorer.getWalkLeft()))
				{
					this.explorer.getJumpLeft().Initialize();
					this.explorer.setState(this.explorer.getJumpLeft());
				}
				else if (this.explorer.getState().equals(this.explorer.getIdleLeft()))
				{
					this.explorer.getJumpIdleLeft().Initialize();
					this.explorer.setState(this.explorer.getJumpIdleLeft());
				}
				else if (this.explorer.getState().equals(this.explorer.getIdleRight()))
				{
					this.explorer.getJumpIdleRight().Initialize();
					this.explorer.setState(this.explorer.getJumpIdleRight());
				}
				break;
		}	
		return false;
	}

	@Override
	public boolean keyUp(int keycode) 
	{	
		switch(keycode)
		{
			case Keys.RIGHT:
				if (this.explorer.getState().equals(this.explorer.getWalkRight()))
				{
					this.explorer.setState(this.explorer.getIdleRight());
				}
				else if (this.explorer.getState().equals(this.explorer.getWalkUpStairsRight()))
				{
					this.explorer.setState(this.explorer.getIdleUpStairsRight());
				}
				else if (this.explorer.getState().equals(this.explorer.getWalkDownStairsLeft()))
				{
					this.explorer.setState(this.explorer.getIdleDownStairsLeft());
				}
				break;
			case Keys.LEFT:
				if (this.explorer.getState().equals(this.explorer.getWalkLeft()))
				{
					this.explorer.setState(this.explorer.getIdleLeft());
				}
				else if (this.explorer.getState().equals(this.explorer.getWalkUpStairsLeft()))
				{
					this.explorer.setState(this.explorer.getIdleUpStairsLeft());
				}
				
				else if (this.explorer.getState().equals(this.explorer.getWalkDownStairsRight()))
				{
					this.explorer.setState(this.explorer.getIdleDownStairsRight());
				}
				break;
		}	
		return false;
	}

	@Override
	public boolean keyTyped(char character) 
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		int x_right = 1200;
		int x_left = 0;
		int y = 620;
		if ( screenX > x_right  && screenX < x_right + 100  && screenY > y && screenY < y + 100)
		{
			this.explorer.setState(this.explorer.getWalkRight());
		}		
		else if ( screenX > x_left  && screenX < x_left + 100  && screenY > y && screenY < y + 100)
		{
			this.explorer.setState(this.explorer.getWalkLeft());
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		int x_right = 1200;
		int x_left = 0;
		int y = 620;
		if ( screenX > x_right  && screenX < x_right + 100  &&
			 screenY > y && screenY < y + 100 &&
			 this.explorer.getState().equals(this.explorer.getWalkRight()))
		{
			this.explorer.setState(this.explorer.getIdleRight());
		}		
		else if ( screenX > x_left  && screenX < x_left + 100  &&
				  screenY > y && screenY < y + 100 &&
				  this.explorer.getState().equals(this.explorer.getWalkLeft()))
		{
			this.explorer.setState(this.explorer.getIdleLeft());
		}		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) 
	{
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
			
		return false;
	}

	@Override
	public boolean scrolled(int amount) 
	{
		return false;
	}
}
