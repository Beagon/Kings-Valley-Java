package com.adruijter.kingsvalley1.gesturelistener;

import com.adruijter.kingsvalley1.explorer.Explorer;
import com.adruijter.kingsvalley1.level.Level;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class ExplorerGestureListener implements GestureListener
{
	private Explorer explorer;
	private Vector2 flingVector;
	
	public ExplorerGestureListener(Level level)
	{
		this.explorer = level.getExplorer();
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) 
	{
		if ( this.explorer.getState().equals(this.explorer.getWalkUpStairsRight()))
		{
			this.explorer.setState(this.explorer.getIdleUpStairsRight());
		}
		else if ( this.explorer.getState().equals(this.explorer.getWalkDownStairsRight()))
		{
			this.explorer.setState(this.explorer.getIdleDownStairsRight());
		}
		else if ( this.explorer.getState().equals(this.explorer.getWalkUpStairsLeft()))
		{
			this.explorer.setState(this.explorer.getIdleUpStairsLeft());
		}
		else if ( this.explorer.getState().equals(this.explorer.getWalkDownStairsLeft()))
		{
			this.explorer.setState(this.explorer.getIdleDownStairsLeft());
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) 
	{
		if ( this.explorer.getState().equals(this.explorer.getWalkRight()))
		{
			this.explorer.setState(this.explorer.getIdleRight());
		}
		else if ( this.explorer.getState().equals(this.explorer.getWalkLeft()))
		{
			this.explorer.setState(this.explorer.getIdleLeft());
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) 
	{
		
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button)
	{
		this.flingVector = new Vector2(velocityX, velocityY);
		float angle = this.flingVector.angle();
		
		if ( !this.explorer.getState().equals(this.explorer.getJumpLeft())  			&&
			 !this.explorer.getState().equals(this.explorer.getJumpRight()) 			&&
			 !this.explorer.getState().equals(this.explorer.getWalkUpStairsRight()) 	&&
			 !this.explorer.getState().equals(this.explorer.getWalkDownStairsRight())   &&
			 !this.explorer.getState().equals(this.explorer.getWalkUpStairsLeft()) 		&&
			 !this.explorer.getState().equals(this.explorer.getWalkDownStairsLeft())    &&
			 !this.explorer.getState().equals(this.explorer.getJumpIdleLeft())			&&
			 !this.explorer.getState().equals(this.explorer.getJumpIdleRight()))
		{
			if ( velocityX > 0 )
			{
				if ( (angle > 340 && angle <= 360) || (angle >= 0 && angle < 20))
				{
					if ( this.explorer.getState().equals(this.explorer.getIdleUpStairsRight()) ||
						 this.explorer.getState().equals(this.explorer.getIdleDownStairsRight()))
					{
						this.explorer.setState(this.explorer.getWalkUpStairsRight());
					}
					else if (this.explorer.getState().equals(this.explorer.getIdleUpStairsLeft()) ||
							 this.explorer.getState().equals(this.explorer.getIdleDownStairsLeft()))
					{
						this.explorer.setState(this.explorer.getWalkDownStairsLeft());
					}					
					else
					{
						this.explorer.setState(this.explorer.getWalkRight());
					}
				}
				else if (angle > 290 && angle < 340 )
				{
					if ( this.explorer.getState().equals(this.explorer.getWalkRight()) ||
						 this.explorer.getState().equals(this.explorer.getWalkLeft()) ||
						 this.explorer.getState().equals(this.explorer.getIdleRight()) ||
						 this.explorer.getState().equals(this.explorer.getIdleLeft()))
					{
						this.explorer.getJumpRight().Initialize();
						this.explorer.setState(this.explorer.getJumpRight());
					}
				}
				else if (angle > 270 && angle < 290 )
				{
					if ( this.explorer.getState().equals(this.explorer.getWalkRight()) || 
						 this.explorer.getState().equals(this.explorer.getWalkLeft()) || 
						 this.explorer.getState().equals(this.explorer.getIdleRight()) ||
						 this.explorer.getState().equals(this.explorer.getIdleLeft()))
					{
						this.explorer.getJumpIdleRight().Initialize();
						this.explorer.setState(this.explorer.getJumpIdleRight());
					}
				}
			}
			else if ( velocityX < 0 )
			{
				if ( angle > 160 && angle <= 200 )
				{
					if ( this.explorer.getState().equals(this.explorer.getIdleUpStairsRight()) ||
						 this.explorer.getState().equals(this.explorer.getIdleDownStairsRight()))
					{
						this.explorer.setState(this.explorer.getWalkDownStairsRight());
					}
					else if (this.explorer.getState().equals(this.explorer.getIdleUpStairsLeft()) ||
							 this.explorer.getState().equals(this.explorer.getIdleDownStairsLeft()))
					{
						this.explorer.setState(this.explorer.getWalkUpStairsLeft());
					}
					else
					{
						this.explorer.setState(this.explorer.getWalkLeft());
					}
				}
				else if (angle < 250  && angle > 200)
				{
					if ( this.explorer.getState().equals(this.explorer.getWalkLeft()) ||
						 this.explorer.getState().equals(this.explorer.getWalkRight()) ||
						 this.explorer.getState().equals(this.explorer.getIdleLeft()) ||
						 this.explorer.getState().equals(this.explorer.getIdleRight()))
					{
						this.explorer.getJumpLeft().Initialize();
						this.explorer.setState(this.explorer.getJumpLeft());
					}
				}
				else if (angle < 270  && angle > 250)
				{
					if ( this.explorer.getState().equals(this.explorer.getWalkLeft()) || 
						 this.explorer.getState().equals(this.explorer.getWalkRight()) || 
					     this.explorer.getState().equals(this.explorer.getIdleLeft()) ||
					     this.explorer.getState().equals(this.explorer.getIdleRight()))
					{
						this.explorer.getJumpIdleLeft().Initialize();
						this.explorer.setState(this.explorer.getJumpIdleLeft());
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) 
	{
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance)
	{
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) 
	{
		return false;
	}

}
