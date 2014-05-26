package com.test.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pest.demo.Game;
import com.pest.demo.Map;
import com.pest.demo.Player;
import com.pest.demo.Position;
import com.pest.demo.SafeMap;
import com.pest.demo.Map.Colour;

public class PlayerTest {

	Map map = null;
	Player player = null;
		
	
	
	@Before
	public void before()
	{	
		player = new Player(1);
	}	
	
	/**
	 * Player Class Class Tests	 
	 */
	
	/**
	 * Checks that player cannot move up out of bounds	 
	 */
			
	@Test
	public void upperBoundTest() 
	{	
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setPositionManual(4,4);	
		assertEquals(player.move('d', 5), false);
	}
	
	/**
	 * Checks that position is set correctly after player moves up	 
	 */
	
	@Test
	public void upperMoveTest() 
	{	
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setPositionManual(2,4);
		player.move('u', 5);	
		assertEquals(player.getPositionR(), 1);
	}
	
	/**
	 * Checks that player cannot move down out of bounds	 
	 */
	
	@Test
	public void lowerBoundTest() 
	{	
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setPositionManual(0,4);
		assertEquals(player.move('u', 5), false);		
	}
	
	/**
	 * Checks that position is set correctly after player moves down	 
	 */
	
	@Test
	public void lowerMoveTest()
	{	
		map = SafeMap.getInstance();
		player.setPositionManual(2,4);
		player.move('d', 5);	
		assertEquals(player.getPositionR(), 3);
	}
	
	/**
	 * Checks that player cannot move right out of bounds	 
	 */
	
	@Test
	public void rightBoundTest() 
	{	
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setPositionManual(1,4);
		assertEquals(player.move('r', 5), false);
	}
	
	/**
	 * Checks that position is set correctly after player moves right	
	 */
	
	@Test
	public void rightMoveTest() 
	{
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setPositionManual(2,2);
		player.move('r', 5);
		assertEquals(player.getPositionC(), 3);
	}
	
	/**
	 * Checks that player cannot move left out of bounds	
	 */
	
	@Test
	public void leftBoundTest() 
	{	
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setPositionManual(1,0);
		assertEquals(player.move('l', 5), false);
	}
	
	/**
	 * Checks that position is set correctly after player moves left	
	 */
	
	@Test
	public void leftMoveTest()
	{	
		map = SafeMap.getInstance();
		player.setPositionManual(2,2);
		player.move('l', 5);
		assertEquals(player.getPositionC(), 1);
	}
	
	/**
	 * Checks that invalid moves are not allowed	
	 */
	
	@Test
	public void invalidMoveTest() 
	{
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setStartingPosition(map);			
		assertEquals(player.move('n', 5), false);
	}
	
	/**
	 * Checks that player correctly moves back to starting position when player moves over blue block	
	 */
	
	@Test
	public void moveBackToStartTest() 
	{
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setStartingPosition(map);
		player.move('u', 5);
		player.move('u', 5);
		player.move('u', 5);
		player.move('u', 5);
		player.moveBackToStart(1);
		
		assertEquals(player.getPositionC(), player.getStartingPosition().getC());
		assertEquals(player.getPositionR(), player.getStartingPosition().getR());	
	}
	
	/**
	 * Checks that player trail correctly stores tiles moved over
	 */
	
	@Test
	public void trailTest() 
	{	
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setPositionManual(0,0);
		player.move('d', 5);
		player.updateTrail();
		player.move('d', 5);
		player.updateTrail();
		player.move('d', 5);
		player.updateTrail();
		player.move('d', 5);
		player.updateTrail();
		
		for(int i = 0; i < player.getTrail().size(); i++)
		{
			assertEquals(player.getTrail().get(i).getR(), i);			
		}		
	}
	
	/**
	 * Checks that player trail correctly stores tiles moved over using overloaded function	
	 */
	
	@Test
	public void trailTest2() 
	{	
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setPositionManual(0,0);
		
		player.updateTrail(new Position(1,0));
		
		player.updateTrail(new Position(2,2));
		
		player.updateTrail(new Position(3,3));
		
		player.updateTrail(new Position(4,4));
		
		for(int i = 0; i < player.getTrail().size(); i++)
		{
			assertEquals(player.getTrail().get(i).getR(), i);			
		}			
	}
	
	/**
	 * Checks that player always starts on green tile	
	 */
	
	@Test
	public void startOnGreenTest() 
	{	
		Map map = SafeMap.getInstance();
		map.setSize(5,2);
		map.createMap();
		player.setStartingPosition(map);
		assertEquals(player.checkColour(map), Colour.GREEN);		
	}
	
	/**
	 * Checks that a players number is correctly assigned	
	 */
	
	@Test
	public void checkPlayerNo()
	{		
		assertEquals(player.getPlayerNum(), 1);		
	}
	

	/**
	 * Checks that a players team number is correctly assigned	
	 */
	
	@Test
	public void checkTeamNo()
	{
	
		Game.setNumPlayers(2);
		Game.setNumTeams(1);
		Game.assignTeams();
		assertEquals(Game.getPlayers()[0].getTeamNum(), 0);				
	}
}
