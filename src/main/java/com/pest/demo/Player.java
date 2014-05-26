package com.pest.demo;

import com.pest.demo.Map.Colour;

import java.util.ArrayList;

public class Player
{
	private Position startingPosition, position;
	private ArrayList<Position> trail = new ArrayList<Position>();
	private int teamNum, playerNum;
	
	/**
	 * Assigns starting position to player, tile colour must be green
	 * Adds starting position to trail
	 */	
	public Player(int playerNum)
	{
		this.teamNum = 0;
		this.playerNum = playerNum;
	}
	
	public void setTeamNum(int num)
	{
		teamNum = num;
	}
	
	public int getTeamNum()
	{
		return teamNum;
	}
	
	public int getPlayerNum()
	{
		return playerNum;
	}
	
	public void setStartingPosition(Map m)
	{
		int x, y;	
		do
		{
			x = (int)(Math.random()*(m.getSize()-1))+1;
			y = (int)(Math.random()*(m.getSize()-1))+1;
		} while (m.getTiles()[x][y] != Map.Colour.GREEN);
			
		startingPosition = new Position(x, y);		
		position = new Position(x, y);		
		
		trail.add(new Position(x, y));
	}
	
	/** 
	 * Moves Player
	 * Returns false if move out of bounds, else moves player and returns true 
	 */	
	public boolean move(char direction, int bounds)
	{
		switch(direction)
		{
			case 'U':
			case 'u':
					if((position.getR()-1) >= 0) //check bounds			
					{
						position.setR(position.getR()-1);
						return true;
					}
					else
					{
						return false;
					}					
			case 'D':
			case 'd':
					if((position.getR()+1) <= (bounds-1))
					{
						position.setR(position.getR()+1);
						return true;
					}
					else
					{
						return false;
					}					
			case 'L':
			case 'l':
					if((position.getC()-1) >= 0)
					{	
						position.setC(position.getC()-1);
						return true;			
					}	
					else
					{
						return false;
					}					
			case 'R':
			case 'r':
					if((position.getC()+1) <= (bounds-1))
					{			
						position.setC(position.getC()+1);
						return true;			
					}
					else
					{
						return false;
					}
			default:
					return false;
		}
	}	
	
	public ArrayList<Position> getTrail()
	{		
		return trail;		
	}
	
	/**
	 * If not already in trail, adds current position to trail
	 */
	public void updateTrail()
	{
		if(!trail.contains(position))
			trail.add(new Position(position.getR(), position.getC()));
	}
	
	/**
	 * Include trail of other players
	 */
	public void updateTrail(Position p)
	{
		if(!trail.contains(p))
			trail.add(p);
	}
	
	public Position getStartingPosition()
	{
		return startingPosition;
	}	
	
	/**
	 * returns the colour of current position of player	
	 */
	public Colour checkColour(Map map)
	{
		return map.getTiles()[position.getR()][position.getC()]; 					
	}		
	
	/**
	 * Moves player back to starting position
	 */
	public void moveBackToStart(int num)
	{
		System.out.println("Player " + num + " Stepped in Water! Resetting to original position...");
		position.setR(startingPosition.getR());
		position.setC(startingPosition.getC());
	}
	
	public int getPositionR()
	{
		return position.getR();
	}
	
	public int getPositionC()
	{
		return position.getC();
	}	
		
	/**
	 * Sets position to manual coordinates
	 */
	public void setPositionManual(int r, int c)
	{
		startingPosition = new Position(r, c);		
		position = new Position(r, c);	
		
		trail.add(new Position(r, c));				
	}
}
