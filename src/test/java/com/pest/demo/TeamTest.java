package com.test.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.test.demo.Game;
import com.test.demo.Map;
import com.test.demo.Player;
import com.test.demo.SafeMap;

public class TeamTest {
	
	
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
	 * Team Class Tests	  
	 */
	

	/**
	 * Checks that every team members trail is updated when the teams trail is updated	  
	 */
	
	@Test
	public void checkTeamTrail()
	{		
		Game.setNumPlayers(2);
		Game.setNumTeams(1);
		Game.assignTeams();
		
		Map map = SafeMap.getInstance();
		
		map.setSize(5,2);
		map.createMap();
		
		Game.getPlayers()[0].setPositionManual(2,3);
		Game.getPlayers()[1].setPositionManual(1,4);				
		
		Game.getTeams()[0].updateTrail(2, 3);
		Game.getTeams()[0].updateTrail(1, 4);
		
		assertEquals(Game.getPlayers()[1].getTrail().get(1).getC(), 3);
		assertEquals(Game.getPlayers()[1].getTrail().get(1).getR(), 2);		
		assertEquals(Game.getPlayers()[0].getTrail().get(2).getC(), 4);
		assertEquals(Game.getPlayers()[0].getTrail().get(2).getR(), 1);
	}	

}
