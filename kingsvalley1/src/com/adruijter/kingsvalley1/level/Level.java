package com.adruijter.kingsvalley1.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.brick.Brick;
import com.adruijter.kingsvalley1.brick.IBuildingBlock;
import com.adruijter.kingsvalley1.exitDoor.ExitDoor;
import com.adruijter.kingsvalley1.explorer.Explorer;
import com.adruijter.kingsvalley1.explorer.ExplorerManager;
import com.adruijter.kingsvalley1.floor.Floor;
import com.adruijter.kingsvalley1.gesturelistener.ExplorerGestureListener;
import com.adruijter.kingsvalley1.inputprocessor.ExplorerInputProcessor;
import com.adruijter.kingsvalley1.jewel.Jewel;
import com.adruijter.kingsvalley1.score.Score;
import com.adruijter.kingsvalley1.stairsLeft.StairsLeft;
import com.adruijter.kingsvalley1.stairsLeft.StepLeft;
import com.adruijter.kingsvalley1.stairsRight.StairsRight;
import com.adruijter.kingsvalley1.stairsRight.StepRight;
import com.adruijter.kingsvalley1.time.Time;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.adruijter.kingsvalley1.character.Character;

public class Level {

	//Fields
	private KingsValley1 game;
    private String levelPath;
    private ArrayList<String> lines;
    private int width, height, updates;
    private IBuildingBlock bricks[][];
    private Explorer explorer;
    private ArrayList<StairsRight> stairsRight;
    private ArrayList<StairsLeft> stairsLeft;
    private ArrayList<Floor> floors;
    private ArrayList<Jewel> jewels;
    private ExplorerInputProcessor input;
    private ExplorerGestureListener gesture;
    private InputMultiplexer multiplexer;
    private Texture spriteSheet;
    private Map<String, TextureRegion> region;
    private Music masterMelody;
    private ArrayList<Character> score;
    private ArrayList<Character> time;
    private ArrayList<Character> highScore;
	private ExitDoor exitDoor;
	private int levelIndex;
	
    //Properties
    public KingsValley1 getGame()
    {
    	return this.game;
    }
    
    public Explorer getExplorer() {
		return explorer;
	}

	public void setExplorer(Explorer explorer) {
		this.explorer = explorer;
	}
    
    public Map<String, TextureRegion> getRegion() {
		return region;
	}
    
    public ArrayList<Character> getScore() {
		return score;
	}

	public void setScore(ArrayList<Character> score) {
		this.score = score;
	}

	public ArrayList<Character> getHighScore() {
		return highScore;
	}

	public void setHighScore(ArrayList<Character> highScore) {
		this.highScore = highScore;
	}

	public ArrayList<Character> getTime() {
		return time;
	}

	public void setTime(ArrayList<Character> time) {
		this.time = time;
	}

	public Music getMasterMelody() {
		return masterMelody;
	}

	public void setMasterMelody(Music masterMelody) {
		this.masterMelody = masterMelody;
	}

	public ExitDoor getExitDoor() {
		return exitDoor;
	}

	public void setExitDoor(ExitDoor exitDoor) {
		this.exitDoor = exitDoor;
	}

	public int getLevelIndex() {
		return levelIndex;
	}

	//Constructor
    public Level(KingsValley1 game, int levelIndex) throws IOException 
	{
		this.game = game;
		this.levelIndex = levelIndex;
		this.levelPath = "data/" + levelIndex + ".txt";
		this.jewels = new ArrayList<Jewel>();
		this.score = new ArrayList<Character>();
		this.highScore = new ArrayList<Character>();
		this.time = new ArrayList<Character>();
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
        ExplorerManager.setJewels(this.jewels);
        
        this.setMasterMelody(Gdx.audio.newMusic(Gdx.files.internal("data/Sound/masterMelody.mp3")));
        //this.masterMelody.play();
       // this.masterMelody.setLooping(true);
        //this.masterMelody.setVolume(0.6f);
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
		
		//Door textureregions
		this.region.put("rightDoor", new TextureRegion(this.spriteSheet, 4, 377, 16, 48));
		this.region.put("leftDoor", new TextureRegion(this.spriteSheet, 24, 377, 48, 48));
		this.region.put("switch", new TextureRegion(this.spriteSheet, 78, 377, 16, 20));
		this.region.put("doorClosed", new TextureRegion(this.spriteSheet, 96, 377, 32, 48));
		this.region.put("doorHalfOpen", new TextureRegion(this.spriteSheet, 130, 377, 48, 48));
		this.region.put("transparantDoor", new TextureRegion(this.spriteSheet, 436, 164, 16, 48));
		this.region.put("handle", new TextureRegion(this.spriteSheet, 78, 377, 16, 21));
		this.region.put("handleDownYellow", new TextureRegion(this.spriteSheet, 78, 400, 16, 21));
		this.region.put("handleDownTransparant", new TextureRegion(this.spriteSheet, 78, 422, 16, 21));
		this.region.put("handleDownWhite", new TextureRegion(this.spriteSheet, 78, 444, 16, 21));
		
		//Explorer textureregions
		this.region.put("explorer0", new TextureRegion(this.spriteSheet, 0, 36, 18, 32));
		this.region.put("explorer1", new TextureRegion(this.spriteSheet, 18, 36, 18, 32));
		this.region.put("explorer2", new TextureRegion(this.spriteSheet, 36, 36, 18, 32));
		this.region.put("explorer3", new TextureRegion(this.spriteSheet, 54, 36, 18, 32));
		this.region.put("explorer4", new TextureRegion(this.spriteSheet, 72, 36, 18, 32));
		this.region.put("explorer5", new TextureRegion(this.spriteSheet, 90, 36, 18, 32));
		this.region.put("explorer6", new TextureRegion(this.spriteSheet, 108, 36, 18, 32));
		this.region.put("explorer7", new TextureRegion(this.spriteSheet, 126, 36, 18, 32));
		
		//Jewel textureregions
		this.region.put("jewel", new TextureRegion(this.spriteSheet, 16, 80, 16, 16));
		this.region.put("crownPartRight", new TextureRegion(this.spriteSheet, 48, 80, 16, 16));
		this.region.put("crownPartMiddle", new TextureRegion(this.spriteSheet, 80, 80, 16, 16));
		this.region.put("crownPartLeft",  new TextureRegion(this.spriteSheet, 112, 80, 16, 16));
		//einde jewel textureregion 30-5 9:38
		
		//Characters
		this.region.put("c", new TextureRegion(this.spriteSheet, 80, 128, 16, 16));
		this.region.put("K", new TextureRegion(this.spriteSheet, 128, 112, 16, 16));
		this.region.put("O", new TextureRegion(this.spriteSheet, 32, 128, 16, 16));
		this.region.put("N", new TextureRegion(this.spriteSheet, 16, 128, 16, 16));
		this.region.put("A", new TextureRegion(this.spriteSheet, 16, 112, 16, 16));
		this.region.put("M", new TextureRegion(this.spriteSheet, 0, 128, 16, 16));
		this.region.put("I", new TextureRegion(this.spriteSheet, 112, 112, 16, 16));
		this.region.put("P", new TextureRegion(this.spriteSheet, 48, 128, 16, 16));
		this.region.put("Y", new TextureRegion(this.spriteSheet, 128, 128, 16, 16));
		this.region.put("R", new TextureRegion(this.spriteSheet, 64, 128, 16, 16));
		this.region.put("-", new TextureRegion(this.spriteSheet, 64, 112, 16, 16));
		this.region.put("D", new TextureRegion(this.spriteSheet, 48, 112, 16, 16));
		this.region.put("o", new TextureRegion(this.spriteSheet, 16, 96, 16, 16));
		this.region.put("C", new TextureRegion(this.spriteSheet, 32, 112, 16, 16));
		this.region.put("S", new TextureRegion(this.spriteSheet, 96, 128, 16, 16));
		this.region.put("E", new TextureRegion(this.spriteSheet, 80, 112, 16, 16));
		this.region.put("T", new TextureRegion(this.spriteSheet, 112, 128, 16, 16));
		this.region.put("H", new TextureRegion(this.spriteSheet, 96, 112, 16, 16));
		
		//Numbers
		this.region.put("0", new TextureRegion(this.spriteSheet, 0, 96, 16, 16));
		this.region.put("1", new TextureRegion(this.spriteSheet, 16, 96, 16, 16));
		this.region.put("2", new TextureRegion(this.spriteSheet, 32, 96, 16, 16));
		this.region.put("3", new TextureRegion(this.spriteSheet, 48, 96, 16, 16));
		this.region.put("4", new TextureRegion(this.spriteSheet, 64, 96, 16, 16));
		this.region.put("5", new TextureRegion(this.spriteSheet, 80, 96, 16, 16));
		this.region.put("6", new TextureRegion(this.spriteSheet, 96, 96, 16, 16));
		this.region.put("7", new TextureRegion(this.spriteSheet, 112, 96, 16, 16));
		this.region.put("8", new TextureRegion(this.spriteSheet, 128, 96, 16, 16));
		this.region.put("9", new TextureRegion(this.spriteSheet, 0, 112, 16, 16));
		
				
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
            case '|':
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick"), '|');
            case '}':
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick"), '}');
            case ']':
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick"), ']');
            case '{':
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick"), '{');
            case '[':
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick"), '[');
            case '=':
                return new Brick(this.game, new Vector2(x, y), this.region.get("fundament"), '=');
            case '3':
                return new Brick(this.game, new Vector2(x, y), this.region.get("emptySpace"), '3');
            case '+':
            	float speed = (KingsValley1.IsAndroid()) ? 1.5f : 1.5f;
            	this.explorer = new Explorer(this.game, new Vector2(x, y), speed, this.region);                 
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), '+');
            case 'g':
            	this.jewels.add(new Jewel(this.game, new Vector2(x, y), new Color(0.125f, 0.847f, 0.125f, 1f ), this.region));
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), 'g');
            case 'b':
            	this.jewels.add(new Jewel(this.game, new Vector2(x, y), new Color(0.125f, 0.125f, 0.968f, 1f ), this.region));
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), 'b');
            case 'r':
            	this.jewels.add(new Jewel(this.game, new Vector2(x, y), new Color(0.847f, 0.282f, 0.690f, 1f ), this.region));
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), 'r');
            case 't':
            	this.jewels.add(new Jewel(this.game, new Vector2(x, y), new Color(0.243f, 0.847f, 0.969f, 1f ), this.region));
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), 't');
            case 's':
                return new StepRight(this.game, new Vector2(x, y), this.region.get("trapTopRight01"), 's');
            case 'x':
                return new StepLeft(this.game, new Vector2(x, y), this.region.get("trapTopLeft01"), 'x');
            //Characters   
            case 'C':
            	return new Character(this.game, new Vector2(x, y), this.region.get("C"), 'C');
            case 'K':
            	return new Character(this.game, new Vector2(x, y), this.region.get("K"), 'K');
            case 'O':
            	return new Character(this.game, new Vector2(x, y), this.region.get("O"), 'O');
            case 'N':
            	return new Character(this.game, new Vector2(x, y), this.region.get("N"), 'N');
            case 'A':
            	return new Character(this.game, new Vector2(x, y), this.region.get("A"), 'A');
            case 'M':
            	return new Character(this.game, new Vector2(x, y), this.region.get("M"), 'M');
            case 'I':
            	return new Character(this.game, new Vector2(x, y), this.region.get("I"), 'I');
            case 'P':
            	return new Character(this.game, new Vector2(x, y), this.region.get("P"), 'P');
            case 'Y':
            	return new Character(this.game, new Vector2(x, y), this.region.get("Y"), 'Y');
            case 'R':
            	return new Character(this.game, new Vector2(x, y), this.region.get("R"), 'R');
            case 'D':
            	return new Character(this.game, new Vector2(x, y), this.region.get("D"), 'D');
            case '-':
            	return new Character(this.game, new Vector2(x, y), this.region.get("-"), '-');
            case 'o':
            	return new Character(this.game, new Vector2(x, y), this.region.get("o"), 'o');
            case 'c':
            	return new Character(this.game, new Vector2(x, y), this.region.get("c"), 'c');
            case 'S':
            	return new Character(this.game, new Vector2(x, y), this.region.get("S"), 'S');
            case 'E':
            	return new Character(this.game, new Vector2(x, y), this.region.get("E"), 'E');
            case 'T':
            	return new Character(this.game, new Vector2(x, y), this.region.get("T"), 'T');
            case 'H':
            	return new Character(this.game, new Vector2(x, y), this.region.get("H"), 'H');
            case '0':
            	return new Character(this.game, new Vector2(x, y), this.region.get("0"), '0');	
            case 'y':
            	return new Character(this.game, new Vector2(x, y), this.region.get("2"), '2');            	
            case 'z':
            	return new Character(this.game, new Vector2(x, y), this.region.get("3"), '3');	
            case '4':
            	return new Character(this.game, new Vector2(x, y), this.region.get("4"), '4');	
            case '5':
            	return new Character(this.game, new Vector2(x, y), this.region.get("5"), '5');	
            case '6':
            	return new Character(this.game, new Vector2(x, y), this.region.get("6"), '6');	
            case '7':
            	return new Character(this.game, new Vector2(x, y), this.region.get("7"), '7');	
            case '8':
            	return new Character(this.game, new Vector2(x, y), this.region.get("8"), '8');	
            case '9':
            	return new Character(this.game, new Vector2(x, y), this.region.get("9"), '9');		
            case '/':
            	this.score.add(new Character(this.game, new Vector2(x, y), this.region.get("0"), '/'));
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), '.');
            case '@':
            	this.time.add(new Character(this.game, new Vector2(x, y), this.region.get("0"), '@'));
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), '.');
            case '!':
            	this.highScore.add(new Character(this.game, new Vector2(x, y), this.region.get("0"), '!'));
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), '.');	
            case '^':
            	this.exitDoor = new ExitDoor(this.game, new Vector2(x, y), (HashMap<String, TextureRegion>) this.region);
            	return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), '^');	
            default:
                return new Brick(this.game, new Vector2(x, y), this.region.get("brick_transparant"), '.');
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
				if ( this.bricks[j][i].getCharacter() == '|' ||
					 this.bricks[j][i].getCharacter() == 's' ||
					 this.bricks[j][i].getCharacter() == 'x' ||
					 this.bricks[j][i].getCharacter() == '3' ||
					 this.bricks[j][i].getCharacter() == '}' ||
					 this.bricks[j][i].getCharacter() == ']' ||
					 this.bricks[j][i].getCharacter() == '{' ||
					 this.bricks[j][i].getCharacter() == '[')
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
                         if (this.bricks[horizontal][k].getCharacter() == '|')
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
                         if (this.bricks[horizontal][k].getCharacter() == '|')
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
		for (Jewel jewel : this.jewels)
		{
			jewel.Update(delta);
		}
		ExplorerManager.setLevel(this);
		if (this.jewels.size() == 0)
		{
			
		}
		this.updates++; 
		if((this.updates % 60) == 0){ //Update every second
		Time.setGameTime(Time.getGameTime() - 1); //Set time -1
		Time.AdjustTime(this); //Adjust it.
		}
		//Gdx.app.log("state: ", this.explorer.getState().toString());
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
        };
        
        for (StairsLeft stair : this.stairsLeft)
        {
            stair.Draw(delta);
        }
        
        //if (ExplorerManager.Debug())
        //{
	        for (Floor floor : this.floors)
	        {
	        	//floor.Draw(delta);
	        }
        //}
	        
	    for (Jewel jewel : this.jewels)
	    {
	    	jewel.Draw(delta);
	    }
	    
	    for (Character character : this.score)
	    {
	    	this.game.getBatch().setColor(1f,1f,1f,1f);
	    	character.Draw(delta);
	    }
	    
	    for (Character character : this.highScore)
	    {
	    	this.game.getBatch().setColor(1f,1f,1f,1f);
	    	character.Draw(delta);
    		Score.AdjustHighScore(this);
	    }
	    
	    for (Character character : this.time)
	    {
	    	this.game.getBatch().setColor(1f,1f,1f,1f);
	    	character.Draw(delta);
			Time.AdjustTime(this);
	    }
        
    
        if (this.explorer != null)
        {
            this.explorer.Draw(delta);
        }
    }
}
