package com.adruijter.kingsvalley1.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.brick.Brick;
import com.adruijter.kingsvalley1.brick.IBuildingBlock;
import com.adruijter.kingsvalley1.explorer.Explorer;
import com.adruijter.kingsvalley1.explorer.ExplorerManager;
import com.adruijter.kingsvalley1.floor.Floor;
import com.adruijter.kingsvalley1.gesturelistener.ExplorerGestureListener;
import com.adruijter.kingsvalley1.inputprocessor.ExplorerInputProcessor;
import com.adruijter.kingsvalley1.stairsLeft.StairsLeft;
import com.adruijter.kingsvalley1.stairsLeft.StepLeft;
import com.adruijter.kingsvalley1.stairsRight.StairsRight;
import com.adruijter.kingsvalley1.stairsRight.StepRight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class Level {

	//Fields
	private KingsValley1 game;
    private String levelPath;
    private ArrayList<String> lines;
    private int width, height;
    private IBuildingBlock bricks[][];
    private Explorer explorer;
    private ArrayList<StairsRight> stairsRight;
    private ArrayList<StairsLeft> stairsLeft;
    private ArrayList<Floor> floors;
    private ExplorerInputProcessor input;
    private ExplorerGestureListener gesture;
    private InputMultiplexer multiplexer;
    private Texture spriteSheet;
    private Map<String, TextureRegion> region;
	
	
    //Properties
    public Explorer getExplorer() {
		return explorer;
	}

	public void setExplorer(Explorer explorer) {
		this.explorer = explorer;
	}
    
    //Constructor
    public Level(KingsValley1 game, int levelIndex) throws IOException 
	{
		this.game = game;
		this.levelPath = "data/" + levelIndex + ".txt";
        this.LoadAssets();
        this.stairsRight = new ArrayList<StairsRight>();
        this.stairsLeft = new ArrayList<StairsLeft>();
        this.floors = new ArrayList<Floor>();
        this.DetectStairsRight();
        this.DetectStairsLeft();
        this.DetectFloors();
        this.input = new ExplorerInputProcessor(this);
        this.gesture = new ExplorerGestureListener(this);
        this.multiplexer = new InputMultiplexer();
        this.multiplexer.addProcessor(this.input);
        this.multiplexer.addProcessor(new GestureDetector(this.gesture));
        Gdx.input.setInputProcessor(this.multiplexer);
        ExplorerManager.setStairsRight(this.stairsRight);
        ExplorerManager.setStairsLeft(this.stairsLeft);
        ExplorerManager.setFloors(this.floors);
	}

	private void LoadAssets() throws IOException 
	{
		this.DefineTextureRegions();
		this.lines = new ArrayList<String>();
		FileHandle handle = Gdx.files.internal(this.levelPath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(handle.read()));
	    String line = reader.readLine();
        this.width = line.length();
        while (line != null)
        {
            lines.add(line);
            line = reader.readLine();
        }
        this.height = lines.size();
        this.bricks = new IBuildingBlock[this.width][ this.height];

        for (int i = 0; i < this.height; i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                char brickElement = lines.get(i).charAt(j);
                this.bricks[j][i] = this.LoadObject(brickElement, j * 16, i * 16);
            }
        }
        reader.close();
	}
	
	private void DefineTextureRegions()
	{
		this.spriteSheet = new Texture("data/SpriteSheet.png");
		this.spriteSheet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.region = new HashMap<String, TextureRegion>();
		this.region.put("brick", new TextureRegion(this.spriteSheet,0, 0, 16, 16));
		this.region.put("brick_transparant", new TextureRegion(this.spriteSheet, 0,16, 16, 16));
		this.region.put("fundament", new TextureRegion(this.spriteSheet,32, 0, 16, 16));
		this.region.put("emptySpace", new TextureRegion(this.spriteSheet, 0,16, 16, 16));
		this.region.put("trapTopRight01", new TextureRegion(this.spriteSheet,100, 16, 16, 16));
		this.region.put("trapTopLeft01", new TextureRegion(this.spriteSheet,68, 16, 16, 16));
		this.region.put("floorTexture16x16", new TextureRegion(this.spriteSheet,16, 0, 16, 16));
		this.region.put("trapLeft01", new TextureRegion(this.spriteSheet,68, 0, 16, 16));
		this.region.put("trapLeft02", new TextureRegion(this.spriteSheet,84, 0, 16, 16));
		this.region.put("trapTopLeft02", new TextureRegion(this.spriteSheet,84,16, 16, 16));
		this.region.put("trapRight01", new TextureRegion(this.spriteSheet,100, 0, 16, 16));
		this.region.put("trapRight02", new TextureRegion(this.spriteSheet,116, 0, 16, 16));
		this.region.put("trapTopRight02", new TextureRegion(this.spriteSheet,116, 16, 16, 16));
		this.region.put("explorer", new TextureRegion(this.spriteSheet, 0, 36, 144, 32 ));
		
				
		for (Map.Entry<String, TextureRegion> e : this.region.entrySet())
		{
			e.getValue().flip(false, true);
		}
		
		
	}
	
	private IBuildingBlock LoadObject(char brickElement, int x, int y)
	{
		switch (brickElement)
        {
            case '.':
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), '.');
            case '1':
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick"), '1');
            case '2':
                return new Brick(this.game, new Vector2(x, y), this.region.get("fundament"), '2');
            case '3':
                return new Brick(this.game, new Vector2(x, y), this.region.get("emptySpace"), '3');
            case '+':
            	float speed = (KingsValley1.IsAndroid()) ? 2f : 1.5f;
            	this.explorer = new Explorer(this.game, new Vector2(x, y), speed);                 
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), '+');
            case 's':
                return new StepRight(this.game, new Vector2(x, y), this.region.get("trapTopRight01"), 's');
            case 'x':
                return new StepLeft(this.game, new Vector2(x, y), this.region.get("trapTopLeft01"), 'x');
            default:
                return new Brick(this.game, new Vector2(x, y), this.region.get("Brick_transparant"), '.');
        }
	}
	
	private void DetectFloors()
	{
		for ( int i = 0; i < this.height; i++)
		{
			int amountOfBricks = 0;
			Vector2 position = Vector2.Zero;
			for (int j = 0; j < this.width; j++)
			{
				if ( this.bricks[j][i].getCharacter() == '1' ||
					 this.bricks[j][i].getCharacter() == 's' ||
					 this.bricks[j][i].getCharacter() == 'x' ||
					 this.bricks[j][i].getCharacter() == '3')
				{
					if (amountOfBricks == 0)
					{
						position = new Vector2(j * 16, i * 16);
					}
					amountOfBricks++;
				}
				else
				{
					//Dit is een test
					if (amountOfBricks > 0)
					{
						this.floors.add(new Floor(this.game,
												  position, 
												  this.region.get("floorTexture16x16"),
												  amountOfBricks,
												  this.bricks[j-1][i].getCharacter(),
												  this.bricks[j - amountOfBricks][i].getCharacter()));
						amountOfBricks = 0;
						position = Vector2.Zero;
					}
				}
			}
		}
	}
	
	public void DetectStairsRight()
     {
         for (int i = 0; i < this.height; i++)
         {
             for (int j = 0; j < this.width; j++)
             {
                 if (this.bricks[j][i].getCharacter() == 's')
                 {
                     int amountOfStairs = 0;
                     int horizontal = j + 1;
                     for (int k = (i + 1); k < this.height; k++)
                     {
                         horizontal--;
                         if (this.bricks[horizontal][k].getCharacter() == '1')
                         {
                             amountOfStairs = k - i - 1;
                             break;
                         }
                     }
                     this.stairsRight.add(new StairsRight(this.game, new Vector2(j * 16, i * 16), amountOfStairs,
                    		 								this.region.get("trapRight01"), this.region.get("trapRight02"), this.region.get("trapTopRight02")));
                 }
             }
         }
     }
	 
	 private void DetectStairsLeft()
     {
         for (int i = 0; i < this.height; i++)
         {
             for (int j = 0; j < this.width; j++)
             {
                 if (this.bricks[j][i].getCharacter() == 'x')
                 {
                     int amountOfStairs = 0;
                     int horizontal = j;
                     for (int k = (i + 1); k < this.height; k++)
                     {
                         horizontal++;
                         if (this.bricks[horizontal][k].getCharacter() == '1')
                         {
                             amountOfStairs = k - i - 1;
                             break;
                         }
                     }
                     this.stairsLeft.add(new StairsLeft(this.game, new Vector2(j * 16f, i * 16f), amountOfStairs,
                    		 								this.region.get("trapLeft01"), this.region.get("trapLeft02"), this.region.get("trapTopLeft02")));
                 }
             }
         }
     }
	
	public void Update(float delta)
    {
		if (this.explorer != null)
		{
			this.explorer.Update(delta);
		}
    }

    public void Draw(float delta)
    {
        for (IBuildingBlock[] row : this.bricks )
        	//for (int i = 0; i < this.bricks.length; i++)
        {
            /*
        	for (int j = 0; j < this.bricks[i].length; j++)
            	//for (int j = 0; j < this.bricks[i].length; j++)
            {
               this.bricks[i][j].Draw(delta);
            }*/
        	for ( IBuildingBlock column : row )
        	{
        		column.Draw(delta);
        	}
        }
        
        for (StairsRight stair : this.stairsRight)
        {
            stair.Draw(delta);
        }
        
        for (StairsLeft stair : this.stairsLeft)
        {
            stair.Draw(delta);
        }
        
        if (ExplorerManager.Debug())
        {
	        for (Floor floor : this.floors)
	        {
	        	floor.Draw(delta);
	        }
        }
        
    
        if (this.explorer != null)
        {
            this.explorer.Draw(delta);
        }
    }
}
