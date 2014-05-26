package com.pest.demo;

public abstract class Map 
{
	private int size;
	public enum Colour {GREEN, BLUE, YELLOW}; 
	private Colour[][] tiles;
	protected static Map map = null;
	
	/**
	 * Set the size of the map depending on the number of players
	 * Returns true if map was successfully created
	 */
	public boolean setSize(int mapSize, int numOfPlayers)
	{				
		if ((mapSize < 5) || (mapSize > 50))
			return false;		
		
		if ((5 <= numOfPlayers) && (numOfPlayers <= 8))
		{			
			if (mapSize < 8)
				return false;			
		}		
		size = mapSize;
		return true;
	}
	
	public abstract void createMap();	
	
	public int getSize()
	{
		return size;
	}
	
	public Colour[][] getTiles()
	{
		return tiles;
	}	
	
	public void setTiles(Colour[][] t)
	{		
		tiles = t;		
	}
}
