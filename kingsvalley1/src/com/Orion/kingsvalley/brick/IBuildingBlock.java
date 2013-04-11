package com.Orion.kingsvalley.brick;

public interface IBuildingBlock {
	
	char getCharacter();
	void setCharacter(char character);
    String getImageName();
    void setImageName(String imageName);
    void Draw(float delta);

}
