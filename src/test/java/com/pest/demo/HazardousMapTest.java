package com.test.demo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pest.demo.HazardousMap;
import com.pest.demo.Map;
import com.pest.demo.Map.Colour;

public class HazardousMapTest {

	
	Map map = null;
	/**
	 * Check that percentage of blue tiles is correct for hazardous map	  
	 */
	
	@Test
	public void checkHazardousmap()
	{
		
		map = HazardousMap.getInstance();
		Boolean isGood = false;
		map.setSize(10,5);
		map.createMap();
		int amntOfBlue = 0;
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				if(map.getTiles()[i][j] == Colour.BLUE)
					amntOfBlue++;
			}
		}
		
		if(amntOfBlue <= 35 || amntOfBlue >= 25)
			isGood = true;
				
		assertEquals(isGood, true);		
	}
}
