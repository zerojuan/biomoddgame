package com.biomodd.client.data;

public class GridsWrapper {
	private int[][] grid;
	
	public GridsWrapper(int[][] grid){
		this.grid = grid; 
	}
	
	public int[][] getGrid(){
		return grid;
	}
}
