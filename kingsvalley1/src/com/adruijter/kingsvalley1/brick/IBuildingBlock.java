package com.adruijter.kingsvalley1.brick;

public interface IBuildingBlock {
	
	char getCharacter();
    String getImageName();
    void setImageName(String imageName);
    void Draw(float delta);

}
