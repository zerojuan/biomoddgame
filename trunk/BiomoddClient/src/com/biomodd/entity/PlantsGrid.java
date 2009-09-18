package com.biomodd.entity;

import java.awt.Point;
import java.util.List;
import java.util.logging.Level;

public class PlantsGrid {

	private int rows;
	private int columns;
	
	private int[][] grid;
	
	public static int DEFAULT = 0;
	public static int PLANTED = 1;
	public static int TERRITORIZED = 2;
	public static int DESTROYED = 3;
	
	public PlantsGrid(int columns, int rows){
		setRows(rows);
		setColumns(columns);		
		
		grid = new int[columns][rows];
		initGrid();
	}
	
	private void initGrid(){
		for(int x = 0; x < columns; x++){
			for(int y = 0; y < rows; y++){
				grid[x][y] = DEFAULT;
			}
		}
		int cenX = columns/2;
		int cenY = rows/2;
		grid[cenX][cenY] = PLANTED;
	}
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public int[][] getGrid() {
		return grid;
	}
	public void setValueAt(int x, int y, int value){
		if(x<0 || x>=columns){
			return;
		}
		if(y<0 || y>=rows){
			return;
		}
		if(value == TERRITORIZED && grid[x][y] == PLANTED){
			return;
		}
		if(value == DEFAULT && grid[x][y] == PLANTED){
			return;
		}
		if(grid[x][y] == value){
			return;
		}
		grid[x][y] = value;		
	}
	/**
	 * Checks if this point can grow
	 * Condition: If the point is already territorized and is near a planted grid
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isGrowable(int x, int y){		
		if(grid[x][y] == TERRITORIZED){
			if(isNearAPlantedGrid(x,y)){
				return true;
			}
		}
		return false;
	}
	public boolean isDestroyed(int x, int y){
		return grid[x][y] == DESTROYED;		
	}
	/**
	 * Checks if this point already has a plant
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isPlanted(int x, int y){
		if(grid[x][y] == PLANTED){
			return true;
		}
		return false;
	}
	/**
	 * Checks NewPlants with the value
	 * @param newPlants
	 * @param value
	 */
	public void setValuesAt(int[][] newPlants, int value){
		for(int i = 0; i < newPlants.length; i++){			
			setValueAt(newPlants[i][0], newPlants[i][1], value);			
		}
	}
	/**
	 * Checks newplants with value
	 * @param newPlants
	 * @param value
	 */
	public void setValuesAt(List<Point> newPlants, int value){
		for(int i = 0; i < newPlants.size(); i++){			
			setValueAt(newPlants.get(i).x, newPlants.get(i).y, value);			
		}
	}
	private boolean isNearAPlantedGrid(int x, int y){
		//check top
		if(y-1 >= 0){
			if(isPlanted(x, y-1)){
				return true;
			}
		}
		//check bottom
		if(y+1 < rows){
			if(isPlanted(x,y+1)){
				return true;
			}
		}
		//check left
		if(x-1 >=0){
			if(isPlanted(x-1, y)){
				return true;
			}
		}
		//check right
		if(x+1 < columns){
			if(isPlanted(x+1, y)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Check if this coordinate is an edge
	 * (Checks perpendicular only)
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isEdge(int x, int y){
		int neighborCount = 0;
		
		if(x < 0 || x >= columns){
			return false;
		}
		
		if(y < 0 || y >= rows){
			return false;
		}
		
		if(!isPlanted(x,y)){
			return false;
		}
		
		//check top
		if(y-1 >= 0){
			if(isPlanted(x, y-1)){
				neighborCount++;
			}
		}
		//check bottom
		if(y+1 < rows){
			if(isPlanted(x,y+1)){
				neighborCount++;
			}
		}
		//check left
		if(x-1 >=0){
			if(isPlanted(x-1, y)){
				neighborCount++;
			}
		}
		//check right
		if(x+1 < columns){
			if(isPlanted(x+1, y)){
				neighborCount++;
			}
		}

		return !(neighborCount == 4 || neighborCount == 0);
	}

}
