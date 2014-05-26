package com.pest.demo;

import java.util.Random;

public class SafeMap extends Map
{
	private SafeMap() {}
	
	public static Map getInstance()
	{	 
		if(map == null)
			map = new SafeMap();
		return map;
	}
	
	@Override	
	public void createMap()
	{		
		int random;
		Colour[][] tiles = new Colour[getSize()][getSize()];
		int waterCount = 0, area = getSize() * getSize();
		int waterLimit = (int)(area * 0.1);
		Random rand = new Random();
		
		for (int i = 0; i < tiles.length; i++)
		{
			for (int j = 0; j < tiles[i].length; j++)
			{
				random = rand.nextInt(7);				
				if(random == 0 && waterCount < waterLimit)	
				{
					tiles[i][j] = Colour.BLUE;
					waterCount ++;
				}
				else
				{
					tiles[i][j] = Colour.GREEN;	
				}
			}					
		}		
		//assign random coordinates to treasure
		int x = (int)(Math.random()*(getSize()-1))+1;
		int y = (int)(Math.random()*(getSize()-1))+1;
		tiles[x][y] = Colour.YELLOW;	
		
		setTiles(tiles);
	}
}
