package com.test.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pest.demo.Game;
import com.pest.demo.HazardousMap;
import com.pest.demo.Map;
import com.pest.demo.Player;
import com.pest.demo.SafeMap;
import com.pest.demo.Map.Colour;

public class SafeMapTest {
	
	
	Map map = null;
	
	/**
	 * Map Class Tests	 
	 */
	
	/**
	 * Checks that map size adheres to amount of player boundaries	 
	 */
	
	@Test
	public void amntOfPlayersBounds1() 
	{		
		Map map = SafeMap.getInstance();
		assertEquals(map.setSize(4, 3), false);
		assertEquals(map.setSize(51, 3), false);
		assertEquals(map.setSize(6, 3), true);
		assertEquals(map.setSize(25, 3), true);
	}
	
	/**
	 * Checks that map size adheres to amount of player boundaries	 
	 */
	
	@Test
	public void amntOfPlayersBounds2() 
	{		
		Map map = SafeMap.getInstance();
		assertEquals(map.setSize(4, 8), false);
		assertEquals(map.setSize(51, 8), false);
		assertEquals(map.setSize(6, 8), false);
		assertEquals(map.setSize(25, 8), true);						
	}
	
	/**
	 * Check that map size is correct	  
	 */
	
	@Test
	public void generateTestSize1()
	{
		Map map = SafeMap.getInstance();
		map.setSize(5, 2);
		map.createMap();

		assertEquals(map.getTiles().length, 5);
		assertEquals(map.getTiles()[1].length, 5);
		assertEquals(map.getSize(), 5);
	}
	
	/**
	 * Check that map size is correct	 
	 */
	
	@Test
	public void generateTestSize2() 
	{
		Map map = SafeMap.getInstance();
		map.setSize(8, 6);
		map.createMap();

		assertEquals(map.getTiles().length, 8);
		assertEquals(map.getTiles()[1].length, 8);
		assertEquals(map.getSize(), 8);		
	}
	
	/**
	 * Check that percentage of blue tiles is correct for safe map	 
	 */
	
	@Test
	public void checkSafeMap()
	{
		map = SafeMap.getInstance();
		Boolean isGood = false;
		map.setSize(5,2);
		map.createMap();
		int amntOfBlue = 0;
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				if(map.getTiles()[i][j] == Colour.BLUE)
					amntOfBlue++;
			}
		}
		
		if(amntOfBlue <= 2.5)
			isGood = true;
		
		assertEquals(isGood, true);		
	}
	
	
}
