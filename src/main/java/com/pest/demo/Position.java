package com.pest.demo;

public class Position 
{
	private int row;
	private int column;
	
	public Position(int r, int c)
	{
		row = r;
		column = c;
	}
	
	public int getR()
	{
		return row;
	}
	
	public void setR(int r)
	{
		row = r;
	}
	
	public int getC()
	{
		return column;
	}
	
	public void setC(int c)
	{
		column = c;
	}
}
