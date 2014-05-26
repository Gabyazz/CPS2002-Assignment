package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import com.test.demo.Game;
import com.test.demo.Map;
import com.test.demo.Position;
import com.test.demo.SafeMap;
import com.test.demo.HazardousMap;
import com.test.demo.Player;
import com.test.demo.Map.Colour;

public class GameTest
{
	Game game = null;
	Map map = null;
	Player player = null;		
	
	
	@Before
	public void before()
	{	
		game = new Game();
		player = new Player(1);
	}	
	
	/**
	 * Game Class Tests	 
	 */
	
	/**
	 * Checks that amount of players matches map size boundaries	 
	 */
	
	@Test
	public void amntOfPlayersBounds() 
	{		
		assertEquals(Game.setNumPlayers(1), false);
		assertEquals(Game.setNumPlayers(9), false);
		assertEquals(Game.setNumPlayers(2), true);
		assertEquals(Game.setNumPlayers(5), true);				
	}
	
	/**
	 * Checks that team size matches amount of players boundaries	  
	 */
	
	@Test
	public void setNumTeamSize()
	{
		Game.setNumPlayers(4);
		assertEquals(Game.setNumTeams(0), false);
		assertEquals(Game.setNumTeams(5), false);
		assertEquals(Game.setNumTeams(3), true);		
	}
	
	/**
	 * Checks that amount of teams is correct	  
	 */
	
	@Test
	public void testAmountTeams()
	{		
		Game.setNumPlayers(2);
		Game.setNumTeams(2);
		Game.assignTeams();
		assertEquals(Game.getTeams().length, 2);					
	}
	
	/**
	 * Checks that all players are assigned to a team
	 */
	
	@Test
	public void testTeams()
	{		
		Game.setNumPlayers(4);
		Game.setNumTeams(3);
		Game.assignTeams();
		assertEquals(Game.getTeams()[0].getPlayers().size() + Game.getTeams()[1].getPlayers().size() + Game.getTeams()[2].getPlayers().size(), 4);		
	}
	
	/**
	 * Checks that team balance is correct when amount of teams is odd 
	 */
	
	@Test
	public void testTeamsOdd()
	{		
		Game.setNumPlayers(8);
		Game.setNumTeams(3);
		Game.assignTeams();		
		
		assertTrue(Game.getTeams()[0].getPlayers().size() < 4);
		assertTrue(Game.getTeams()[1].getPlayers().size() < 4);
		assertTrue(Game.getTeams()[2].getPlayers().size() < 4);
		
		assertTrue(Game.getTeams()[0].getPlayers().size() < 3 || Game.getTeams()[1].getPlayers().size() < 3 || Game.getTeams()[2].getPlayers().size() < 3);
	}
	
	/**
	 * Checks that team balance is correct when amount of teams is even  
	 */
	
	@Test
	public void testTeamsEven()
	{		
		Game.setNumPlayers(4);
		Game.setNumTeams(2);
		Game.assignTeams();
		assertEquals(Game.getTeams()[0].getPlayers().size(), 2);
		assertEquals(Game.getTeams()[1].getPlayers().size(), 2);
	}	
	
	/**
	 * Checks that map is equal for all players	 
	 */
	
	@Test
	public void htmlMapTest() throws IOException
	{	
		Map map = SafeMap.getInstance();
		Game.setNumPlayers(2);
		map.setSize(5,2);
		map.createMap();
		Player[] players = Game.getPlayers();
		
		for (int i=0; i < players.length; i++)
		{
			players[i].setPositionManual(0, 0);
		}				
		
		Game.generateHTMLFiles(map);
		File map1 = new File("player1_map.html");
		File map2 = new File("player2_map.html");		
		
		assertEquals(FileUtils.readFileToString(map1, "utf-8"), FileUtils.readFileToString(map2, "utf-8"));
	}	
}
