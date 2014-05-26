package com.pest.demo;

import java.util.Random;

public class TemplateMap extends Map
{
	private TemplateMap() {}
	
	public static Map getInstance()
	{	 
		if(map == null)
			map = new TemplateMap();
		return map;
	}
	
	@Override	
	public void createMap()
	{		
		
		Colour[][] tiles = new Colour[getSize()][getSize()];
		
		/*
		 * 
		 * Assign tile colours to green or blue
		 * Change amount of blue tiles for varying levels of difficulty
		 * 
		 * At the end add a yellow (victory tile)
		 * 
		 */
		
		setTiles(tiles);
	}
}
