package com.adruijter.kingsvalley1.explorer;



import com.adruijter.kingsvalley1.KingsValley1;
import com.adruijter.kingsvalley1.animatedsprite.AnimatedSprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Explorer
{	
	//Fields
	private KingsValley1 game;
	private Vector2 position;
	private float speed;
	private float pixelsThroughFloor;
	private float pixelsInWallRight;
	private Texture texture;
	private AnimatedSprite state;
	private ExplorerWalkRight walkRight;
	private ExplorerIdleRight idleRight;
	private ExplorerIdleLeft idleLeft;
	private ExplorerWalkLeft walkLeft;
	private ExplorerJumpRight jumpRight;
	private ExplorerJumpLeft jumpLeft;
	private ExplorerJumpIdleRight jumpIdleRight;
	private ExplorerJumpIdleLeft jumpIdleLeft;
	private ExplorerWalkUpStairsRight walkUpStairsRight;
	private ExplorerIdleUpStairsRight idleUpStairsRight;
	private ExplorerIdleDownStairsRight idleDownStairsRight;
	private ExplorerWalkDownStairsRight walkDownStairsRight;
	private ExplorerWalkUpStairsLeft walkUpStairsLeft;
	private ExplorerIdleUpStairsLeft idleUpStairsLeft;
	private ExplorerIdleDownStairsLeft idleDownStairsLeft;
	private ExplorerWalkDownStairsLeft walkDownStairsLeft;
	private ExplorerFallOfFloorLeft fallOfFloorLeft;
	private ExplorerFallOfFloorRight fallOfFloorRight;
	private Rectangle collisionRectStairs;
	private Texture collisionText;
	
	
	//Properties
	public Vector2 getPosition()
	{
		return this.position;
	}
	public void setPosition(Vector2 position)
	{
		this.position = position;
		this.collisionRectStairs.x = this.position.x;
		this.collisionRectStairs.y = this.position.y + 16;
	}
	public float getSpeed()
	{
		return this.speed;
	}
	public Texture getTexture()
	{
		return this.texture;
	}
	public KingsValley1 getGame()
	{
		return this.game;
	}
	public AnimatedSprite getState()
	{
		return this.state;
	}
	public void setState(AnimatedSprite state)
	{
		this.state = state;
	}
	public ExplorerIdleRight getIdleRight()
	{
		return this.idleRight;
	}
	public void setIdleRight(ExplorerIdleRight idleRight)
	{
		this.idleRight = idleRight;
	}
	public ExplorerWalkRight getWalkRight()
	{
		return this.walkRight;
	}
	public void setWalkRight(ExplorerWalkRight walkRight)
	{
		this.walkRight = walkRight;
	}
	public ExplorerIdleLeft getIdleLeft()
	{
		return this.idleLeft;
	}
	public void setIdleLeft(ExplorerIdleLeft idleLeft)
	{
		this.idleLeft = idleLeft;
	}	
	public ExplorerWalkLeft getWalkLeft() {
		return this.walkLeft;
	}
	public void setWalkLeft(ExplorerWalkLeft walkLeft) {
		this.walkLeft = walkLeft;
	}	
	public ExplorerJumpRight getJumpRight() {
		return jumpRight;
	}
	public void setJumpRight(ExplorerJumpRight jumpRight) {
		this.jumpRight = jumpRight;
	}
	public ExplorerJumpLeft getJumpLeft() {
		return jumpLeft;
	}
	public void setJumpLeft(ExplorerJumpLeft jumpLeft) {
		this.jumpLeft = jumpLeft;
	}
	public ExplorerJumpIdleRight getJumpIdleRight() {
		return jumpIdleRight;
	}
	public void setJumpIdleRight(ExplorerJumpIdleRight jumpIdleRight) {
		this.jumpIdleRight = jumpIdleRight;
	}
	public ExplorerJumpIdleLeft getJumpIdleLeft() {
		return jumpIdleLeft;
	}
	public void setJumpIdleLeft(ExplorerJumpIdleLeft jumpIdleLeft) {
		this.jumpIdleLeft = jumpIdleLeft;
	}
	public Rectangle getCollisionRectStairs() {
		return collisionRectStairs;
	}
	public void setCollisionRectStairs(Rectangle collisionRectStairs) {
		this.collisionRectStairs = collisionRectStairs;
	}
	public Texture getCollisionText() {
		return collisionText;
	}
	public void setCollisionText(Texture collisionText) {
		this.collisionText = collisionText;
	}
	public ExplorerWalkUpStairsRight getWalkUpStairsRight() {
		return walkUpStairsRight;
	}
	public void setWalkUpStairsRight(ExplorerWalkUpStairsRight walkUpStairsRight) {
		this.walkUpStairsRight = walkUpStairsRight;
	}
	public ExplorerIdleUpStairsRight getIdleUpStairsRight() {
		return idleUpStairsRight;
	}
	public void setIdleUpStairsRight(ExplorerIdleUpStairsRight idleUpStairsRight) {
		this.idleUpStairsRight = idleUpStairsRight;
	}
	public ExplorerIdleDownStairsRight getIdleDownStairsRight() {
		return idleDownStairsRight;
	}
	public void setIdleDownStairsRight(ExplorerIdleDownStairsRight idleDownStairsRight) {
		this.idleDownStairsRight = idleDownStairsRight;
	}
	public ExplorerWalkDownStairsRight getWalkDownStairsRight() {
		return walkDownStairsRight;
	}
	public void setWalkDownStairsRight(ExplorerWalkDownStairsRight walkDownStairsRight) {
		this.walkDownStairsRight = walkDownStairsRight;
	}
	public ExplorerWalkUpStairsLeft getWalkUpStairsLeft() {
		return walkUpStairsLeft;
	}
	public void setWalkUpStairsLeft(ExplorerWalkUpStairsLeft walkUpStairsLeft) {
		this.walkUpStairsLeft = walkUpStairsLeft;
	}
	public ExplorerIdleUpStairsLeft getIdleUpStairsLeft() {
		return idleUpStairsLeft;
	}
	public void setIdleUpStairsLeft(ExplorerIdleUpStairsLeft idleUpStairsLeft) {
		this.idleUpStairsLeft = idleUpStairsLeft;
	}
	public ExplorerIdleDownStairsLeft getIdleDownStairsLeft() {
		return idleDownStairsLeft;
	}
	public void setIdleDownStairsLeft(ExplorerIdleDownStairsLeft idleDownStairsLeft) {
		this.idleDownStairsLeft = idleDownStairsLeft;
	}
	public ExplorerWalkDownStairsLeft getWalkDownStairsLeft() {
		return walkDownStairsLeft;
	}
	public void setWalkDownStairsLeft(ExplorerWalkDownStairsLeft walkDownStairsLeft) {
		this.walkDownStairsLeft = walkDownStairsLeft;
	}	
	public ExplorerFallOfFloorLeft getFallOfFloorLeft() {
		return fallOfFloorLeft;
	}
	public void setFallOfFloorLeft(ExplorerFallOfFloorLeft fallOfFloorLeft) {
		this.fallOfFloorLeft = fallOfFloorLeft;
	}
	public float getPixelsThroughFloor() {
		return pixelsThroughFloor;
	}
	public void setPixelsThroughFloor(float pixelsThroughFloor) {
		this.pixelsThroughFloor = pixelsThroughFloor;
	}	
	public ExplorerFallOfFloorRight getFallOfFloorRight() {
		return fallOfFloorRight;
	}
	public void setFallOfFloorRight(ExplorerFallOfFloorRight fallOfFloorRight) {
		this.fallOfFloorRight = fallOfFloorRight;
	}
	
	public float getPixelsInWallRight() {
		return pixelsInWallRight;
	}
	public void setPixelsInWallRight(float pixelsInWallRight) {
		this.pixelsInWallRight = pixelsInWallRight;
	}
	
	//Constructor
	public Explorer(KingsValley1 game, Vector2 position, float speed)
	{
		this.game = game;
		this.position = position;
		this.collisionRectStairs = new Rectangle(this.position.x, this.position.y + 16, 16, 17);
		this.speed = speed;	
		this.texture = new Texture("data/Explorer/explorer.png");
		this.collisionText = new Texture("data/Explorer/collision_text.png");
		this.walkRight = new ExplorerWalkRight(this);
		this.idleRight = new ExplorerIdleRight(this);
		this.idleLeft = new ExplorerIdleLeft(this);
		this.walkLeft = new ExplorerWalkLeft(this);
		this.jumpRight = new ExplorerJumpRight(this, 20, 32);
		this.jumpLeft = new ExplorerJumpLeft(this, -20, 32);
		this.jumpIdleRight = new ExplorerJumpIdleRight(this, 20, 32);
		this.jumpIdleLeft = new ExplorerJumpIdleLeft(this, 20, 32);
		this.walkUpStairsRight = new ExplorerWalkUpStairsRight(this);
		this.idleUpStairsRight = new ExplorerIdleUpStairsRight(this);
		this.idleDownStairsRight = new ExplorerIdleDownStairsRight(this);
		this.walkDownStairsRight = new ExplorerWalkDownStairsRight(this);
		this.walkUpStairsLeft = new ExplorerWalkUpStairsLeft(this);
		this.idleUpStairsLeft = new ExplorerIdleUpStairsLeft(this);
		this.idleDownStairsLeft = new ExplorerIdleDownStairsLeft(this);
		this.walkDownStairsLeft = new ExplorerWalkDownStairsLeft(this);
		this.fallOfFloorLeft = new ExplorerFallOfFloorLeft(this, -1, 1);
		this.fallOfFloorRight = new ExplorerFallOfFloorRight(this, 1, 1);
		this.state = this.idleRight;
	}
	
	
	//Update
	float timer = 0;
	public void Update(float delta)
	{
		ExplorerManager.setExplorer(this);
		this.timer = this.timer + delta;
		if ( this.timer > 1)
		{
			//Gdx.app.log("x:", this.position.toString());
			this.timer = 0;
		}
		this.state.Update(delta);
	}
	
	
	//Draw
	public void Draw(float delta)
	{
		this.getGame().getBatch().setColor(1f, 0f, 1f, 0.5f);
		//this.game.getBatch().draw(this.collisionText, this.collisionRectStairs.x, this.collisionRectStairs.y, 
		//		this.collisionRectStairs.getWidth(), this.collisionRectStairs.getHeight());
		this.getGame().getBatch().setColor(1f, 1f, 1f, 1f);
		this.state.Draw(delta);		
	}
}
