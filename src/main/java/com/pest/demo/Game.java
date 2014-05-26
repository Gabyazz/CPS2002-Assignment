package com.pest.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import com.pest.demo.Map.Colour;
import java.util.Random;

public class Game 
{
	private static int turns = 0;
	private static Player[] players, winningPlayers;
	private static Map map;
	private static Team[] teams = null, winningTeams;
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int numOfPlayers, mapSize, mapType = 0, numOfTeams = 0;
		char mode;

		do
		{
			System.out.println("Please Enter the Number of Players(2-8): ");
			numOfPlayers = sc.nextInt();
		} 
		while (!setNumPlayers(numOfPlayers));
		
		if(numOfPlayers > 1)
		{
			do
			{
				System.out.println("Play in collaborative mode (Y/N): ");
				mode = sc.next().charAt(0);
			}
			while (mode != 'y' && mode != 'Y' && mode != 'n' && mode != 'N');
		
			if (mode == 'y' || mode == 'Y')
			{
				do
				{
					System.out.println("Please Enter the Number of Teams(1-" + players.length + "):");
					numOfTeams = sc.nextInt();
				} 
				while (!setNumTeams(numOfTeams));
			}
		}
				
		do
		{
			System.out.println("Please enter the type of map:");
			System.out.println("1: Safe");
			System.out.println("2: Hazardous");
			mapType = sc.nextInt();
		}
		while (mapType != 1 && mapType != 2);
		
		switch(mapType)
		{
			case 1:
				map = SafeMap.getInstance();
				break;
			case 2:
				map = HazardousMap.getInstance();
				break;
			default:
				break;
		}
		
		if(numOfPlayers <= 4)
		{				
			do
			{
				System.out.println("Please Enter the Size of the Map ([5x5]-[50x50]): ");
				mapSize = sc.nextInt();
			} 
			while (!map.setSize(mapSize, players.length));						
		}
		
		else
		{									
			do
			{
				System.out.println("Please Enter the Size of the Map ([8x8]-[50x50]): ");
				mapSize = sc.nextInt();
			} 
			while (!map.setSize(mapSize, players.length));			
		}
		map.createMap();
		
		if (numOfTeams != 0)
		{
			assignTeams();
			printTeams();
		}		
		
		startGame();		
		sc.close();
	}
	
	/**   
	 * Initializes the players
	 * Returns true if conditions are satisfied
	 */	
	public static boolean setNumPlayers(int n)
	{
		if((n >= 2) && (n <= 8))
		{
			players = new Player[n];
			
			//initializing the players
			for (int i=0; i < n; i++)
			{
				players[i] = new Player(i);
			}			
			return true;
		}
		else
			return false;
	}	
	
	/**   
	 * Initializes the teams
	 * Returns true if conditions are satisfied
	 */	
	public static boolean setNumTeams(int n) 
	{
		if ((n >= 1) && (n <= players.length))
		{
			teams = new Team[n];
	
			//initializing the teams
			for (int i = 0; i < n; i++) 
			{
				teams[i] = new Team();
			}
			return true;
		}
		else 
			return false;
	}	
	
	/**   
	 * assign each player to a team
	 */	
	public static void assignTeams() 
	{		
		Random rand = new Random();
		int random, p = players.length, bigTeam = 0;
		
		for (int i = 0; i < p; i++) 
		{			
			random = rand.nextInt(teams.length);
			if(teams[random].getPlayers().size() < (p / teams.length))	//balance out teams
			{
				teams[random].register(players[i]);
				players[i].setTeamNum(random);
			}
			else if((teams[random].getPlayers().size() < ((p / teams.length) + 1 )) && bigTeam < (p % teams.length))	//balance out teams when odd number of players
			{
				bigTeam++;
				teams[random].register(players[i]);
				players[i].setTeamNum(random);
			}
			else
			{
				i--;	//if player was not assigned to a team
			}
		}
	}
	
	/**   
	 * print the teams showing the players
	 */		
	private static void printTeams()
	{
		System.out.print("\n------------------------------------------");
		for (int i = 0; i < teams.length; i++)
		{			
			System.out.println("\nTeam " + (i+1) + ":");
			for (int j = 0; j < teams[i].getPlayers().size(); j++)
			{
				System.out.println("Player " + (teams[i].getPlayers().get(j).getPlayerNum()+1));
			}
		}
		System.out.println("------------------------------------------\n");
	}
	
	private static void startGame()
	{		
		Scanner sc = new Scanner(System.in); 
		boolean valid; //to check if move is within map bounds
		boolean won = false; //to see if anyone won
		char move;
		winningPlayers = new Player[players.length];
		Position p;
		
		//assign starting position to each player
		for (int i = 0; i < players.length; i++)
		{				
			players[i].setStartingPosition(map);
			if (teams != null)
			{
				p = players[i].getStartingPosition();
				teams[players[i].getTeamNum()].updateTrail(p.getR(), p.getC()); //add the starting positions to the team trail	
			}
		}
		generateHTMLFiles(map);			
		do
		{			
			for (int i = 0; i < players.length; i++) //running turn for each player. 
			{								
				do 
				{ 
					System.out.println("Player " + (i+1) + " Enter Move: (U)p, (D)own, (L)eft, (R)ight");
					move = sc.next().charAt(0);
					valid = players[i].move(move, map.getSize());	
					if (valid == false) 
						System.out.println("Invalid move!!!");
					
				} while(!valid);					
			}
			
			for (int i = 0; i < players.length; i++) //running turn for each player. 
			{	
				players[i].updateTrail();	//add position to trail
				if (teams != null)
				{
					teams[players[i].getTeamNum()].updateTrail(players[i].getPositionR(), players[i].getPositionC());
				}
				
				if(players[i].checkColour(map) == Colour.YELLOW) //adding players into winners array
				{	
					winningPlayers[i] = new Player(players[i].getPlayerNum());
					winningPlayers[i] = players[i];					
					won = true;		
					for (int j = 0; j < players.length; j++)
					{
						players[j].updateTrail(new Position(players[i].getPositionR(), players[i].getPositionC())); //add the treasure position to everyone's trail
					}
				}
				
				//check if player hits water tile
				else if(players[i].checkColour(map) == Colour.BLUE)
				{			
					players[i].moveBackToStart(i+1);						
				}
			}	
			turns++;
			generateHTMLFiles(map);
		} while(won == false);
		
		System.out.println("\n------------------------------------------");
		System.out.println("After " + turns + " turn(s), Winner(s):");
		if (teams != null)
		{
			int teamNum;
			winningTeams = new Team[teams.length];
			for (int i = 0; i < winningPlayers.length; i++)
			{
				if(winningPlayers[i] != null)
				{
					teamNum = winningPlayers[i].getTeamNum();
					winningTeams[teamNum] = new Team();
					winningTeams[teamNum] = teams[teamNum];
				}
			}
			for (int i = 0; i < winningTeams.length; i++) //running turn for each player.
			{
				if(winningTeams[i] == null)
					continue;
				else
					System.out.println("Team " + (i+1));
			}	
			System.out.println("\nTreasure was found by: ");
		}
		for (int i = 0; i < winningPlayers.length; i++) //running turn for each player.
		{
			if(winningPlayers[i] == null)
				continue;
			else
				System.out.println("Player " + (i+1));
		}		
		sc.close();		
	}
	
	/**
	 * Create an html file for each player 
	 */
	public static void generateHTMLFiles(Map map)
	{
		File file = null;
		Colour colour;		
		Colour[][] tiles = map.getTiles();
		int found = 0, inTrail = 0;	//to check if treasure was found .... and.... to check if a position is already in the trail
		
		try
		{
			for (int i = 0; i < players.length; i++)	//for each player
			{
				file = new File("player" + (i+1) + "_map.html");
				PrintWriter writer = new PrintWriter(file.getPath(), "UTF-8");
			
				writer.println("<html>");
				writer.println("<head>");
				writer.println("<META HTTP-EQUIV=\"refresh\" content=\"3\">");
				writer.println("</head>");
				writer.println("<body>");
				writer.println("<table style=\"border-collapse: collapse;\">");
			
				for (int j = 0; j < tiles.length; j++)	//for each row
				{
					writer.println("<tr>");
					for (int k = 0; k < tiles[j].length; k++)	//for each column
					{	
						
						for(int l = 0; l < players[i].getTrail().size(); l++)	//for each item in the list
						{							 
							if((players[i].getTrail().get(l).getR() == j) && (players[i].getTrail().get(l).getC() == k))	//if position in trail
							{	
								inTrail = 1;
								break;
							}
						}
						
						if (inTrail == 1)	//if position in trail add its colour to file
						{
							colour = tiles[j][k];
							
							if(colour == Colour.GREEN)
								writer.println("<td style=\"background-color:#00CC00;width:50px;height:50px;border:1px solid black;\">");							
							
							else if(colour == Colour.BLUE)
								writer.println("<td style=\"background-color:#00CCFF;width:50px;height:50px;border:1px solid black;\">");	
							
							else if(colour == Colour.YELLOW)
							{
								found = 1;
								writer.println("<td style=\"background-color:#C0C0C0;width:50px;height:50px;border:1px solid black;\">");
								writer.println("<img src=\"images/treasure.jpg\" width=\"50px\" height=\"50px\">");
							}							
						}
						else 
						{				
							writer.println("<td style=\"background-color:#C0C0C0;width:50px;height:50px;border:1px solid black;\">");
						}			
						
						if ((j == players[i].getPositionR()) && (k == players[i].getPositionC()) && (found==0))	//if player is at this position and treasure is not
						{
							writer.println("<img src=\"images/player.jpg\" width=\"50px\" height=\"48px\"");
						}
						else
						{
							found = 0;	//set back to 0 as other players haven't found it yet
						}
						inTrail = 0;
						writer.println("</td>");				
					}					
					writer.println("</tr>");		
				}		
				writer.println("</table>");
				writer.println("</body>");
				writer.print("</html>");
				writer.close();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}	

	/**
	 * Returns the players array
	 */
	public static Player[] getPlayers()
	{	
		return players;	
	}
	
	/**
	 * Returns the teams array
	 */
	public static Team[] getTeams()
	{	
		return teams;	
	}

}