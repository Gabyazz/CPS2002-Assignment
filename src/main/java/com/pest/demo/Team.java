package com.pest.demo;

import java.util.ArrayList;

public class Team 
{
	private ArrayList<Player> players = new ArrayList<Player>();	//stores players in team
	private ArrayList<Position> trail = new ArrayList<Position>();	//stores trail of all players in team
	    	   
	/**
	 * adds player to team
	 */
    public void register(Player player)
    {
    	players.add(player);
    }
    
    /**
     * adds new positions to the current team trail
     * updates the trail of rest of players in team
     */
    public void updateTrail(int row, int column)
	{
    	Position position = new Position(row, column);
		if(!trail.contains(position))
		{			
			trail.add(position);
			for (int i = 0; i < players.size(); i++)
			{
				players.get(i).updateTrail(position);
			}
		}		
	} 
    
    public ArrayList<Player> getPlayers()
    {
    	return players;
    }
}
