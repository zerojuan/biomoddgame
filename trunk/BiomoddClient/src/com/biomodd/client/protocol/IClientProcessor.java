package com.biomodd.client.protocol;

public interface IClientProcessor {
	/**
	 * Signals a newgame packet to let the client know that he has been accepted
	 * @param myId
	 */
	public void newGame(int myId);
	
	/**
	 * Move the player 
	 * @param id the id of the player
	 * @param startX the xCoordinate of the starting position
	 * @param startY the yCoordinate of the starting position
	 * @param endX the xCoordinate of the end position
	 * @param endY the yCoordinate of the end position
	 */
	public void movePlayer(int id, float startX, float startY, float endX, float endY);
	/**
	 * Add a player with the given velocity and position and type
	 * @param id the id of the player
	 * @param x the start position
	 * @param y the start position
	 * @param velocityX the speed x of this player
	 * @param velocityY the speed y of this player
	 * @param type the type of player
	 */
	public void addPlayer(int id, float x, float y, float velocityX, float velocityY, int type);
	/**
	 * Removes the player from the queue
	 * @param id the player id
	 */
	public void removePlayer(int id);
	/**
	 * Grow an array of new plants on the given coordinates
	 * @param newPlants the x,y coordinate of the new plants
	 */
	public void growPlant(int[][] newPlants);
	/**
	 * Initialize the enemies
	 * @param enemies the array of enemy positions
	 * @param ids the ids of the enemies
	 * @param elapsedTime the elapsedTime since these enemies where spawned
	 */
	public void initEnemies(float[][] enemies, int[] ids, long[] elapsedTime);
	/**
	 * Initialize the blocks
	 * @param blocks the array of block positions
	 * @param ids the ids of the blocks
	 * @param hp the health of each block
	 */
	public void initBlocks(float[][] blocks, int[] ids, int[] hp);
	/**
	 * Initialize the territories
	 * @param territory the array of territory positions
	 * @param ids the ids of the territories
	 * @param elapsedTime the elapsed time since the territory where created
	 */
	public void initTerritories(float[][] territory, int[] ids, long[] elapsedTime);
	/**
	 * Add a block at the given position we don't need to add the hp
	 * @param id the object id
	 * @param x the xposition
	 * @param y the yposition
	 */
	public void addBlock(int id, float x, float y);
	/**
	 * Add a territory 
	 * @param id the object id
	 * @param x the xposition
	 * @param y the yposition
	 * @param tTime 
	 * @param radius
	 */
	public void addTerritory(int id, float x, float y, long tTime, int radius);
	/**
	 * Change the type of this player
	 * @param id the object id
	 * @param type the type of player
	 */
	public void changeType(int id, int type);

	/**
	 * Sends a new wave
	 * @param newEnemies enemy positions
	 * @param ids enemy ids
	 * @param elapsedTime enemy elapsed time
	 */
	public void enemyCharge(float[][] newEnemies, int[] ids, long[] elapsedTime);
	
	public void removeEnemy(int id);
	public void removeBlock(int id);
	public void removeTerritory(int id);
	public void initPlayers(float[][] players, int[] ids, int[] types);
}
