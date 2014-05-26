package com.pest.demo;

import java.util.Random;

public class HazardousMap extends Map
{
	private HazardousMap() {}
	
	public static Map getInstance()
	{	
		if(Map.map == null)
			map = new HazardousMap();
		return map;
	}
		
	@Override	
	public void createMap()
	{		
		int random;
		Colour[][] tiles = new Colour[getSize()][getSize()];
		int waterCount = 0, area = getSize() * getSize();
		int waterLimit = (int)(area * 0.35);
		int x, y;		
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
		x = (int)(Math.random()*(getSize()-1))+1;
		y = (int)(Math.random()*(getSize()-1))+1;
		tiles[x][y] = Colour.YELLOW;
		
		while (waterCount < (area * 0.25))
		{		
			x = (int)(Math.random()*(getSize()-1))+1;
			y = (int)(Math.random()*(getSize()-1))+1;
			if (tiles[x][y] != Colour.BLUE && tiles[x][y] != Colour.YELLOW)
			{
				tiles[x][y] = Colour.BLUE;
				waterCount ++;
			}
		}	
		setTiles(tiles);
	}
}
