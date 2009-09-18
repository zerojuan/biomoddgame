package com.biomodd.client.protocol;

import com.biomodd.client.data.GridsWrapper;
import com.biomodd.client.data.IntsWrapper;
import com.biomodd.client.data.LongsWrapper;
import com.biomodd.client.data.PositionsWrapper;
import com.biomodd.client.handler.ClientHandler;
import com.biomodd.task.ETask;
import com.biomodd.task.manager.TaskManager;

public class MessageProcessor implements IClientProcessor {
	/**
	 * The <code>ClientHandler</code> this processor is attached to.
	 */
	private final ClientHandler handler;
	/**
	 * The ID number of the local controlled player.
	 */
	private int myID;
	
	/**
	 * Constructor of <code>MessageProcessor</code>.
	 * @param handler The <code>ClientHandler</code> this processor is attached to.
	 */
	public MessageProcessor(ClientHandler handler) {
		this.handler = handler;
	}
	@Override
	public void newGame(int myId){
		this.myID = myId;
		TaskManager.getInstance().createTask(ETask.NewGame, myId);
	}
	@Override
	public void addBlock(int id, float x, float y) {
		TaskManager.getInstance().createTask(ETask.PlayerBuild, id, x, y);
	}

	@Override
	public void addPlayer(int id, float x, float y, float velocityX,
			float velocityY, int type) {
		if(id == myID) return;
		TaskManager.getInstance().createTask(ETask.AddPlayer, id, x, y, velocityX, velocityY, type);

	}

	@Override
	public void addTerritory(int id, float x, float y, long tTime, int radius) {
		TaskManager.getInstance().createTask(ETask.PlayerTerritorize, id, x, y, tTime, radius);
	}

	@Override
	public void changeType(int id, int type) {
		TaskManager.getInstance().createTask(ETask.ChangePlayerType, id, type);
	}

	@Override
	public void growPlant(int[][] newPlants) {
		TaskManager.getInstance().createTask(ETask.GrowPlant, new GridsWrapper(newPlants));
	}

	@Override
	public void initBlocks(float[][] blocks, int[] ids, int[] hp) {
		TaskManager.getInstance().createTask(ETask.InitializeBlocks, new PositionsWrapper(blocks), new IntsWrapper(ids), new IntsWrapper(hp));
	}

	@Override
	public void initEnemies(float[][] enemies, int[] ids, long[] elapsedTime) {
		TaskManager.getInstance().createTask(ETask.InitializeEnemies, new PositionsWrapper(enemies), new IntsWrapper(ids), new LongsWrapper(elapsedTime));
	}

	@Override
	public void initTerritories(float[][] territory, int[] ids,
			long[] elapsedTime) {
		TaskManager.getInstance().createTask(ETask.InitializeTerritories, new PositionsWrapper(territory),
				new IntsWrapper(ids), new LongsWrapper(elapsedTime));
	}

	@Override
	public void movePlayer(int id, float startX, float startY, float endX,
			float endY) {
		if(id == myID) return;
		TaskManager.getInstance().createTask(ETask.PlayerMove, id, startX, startY, endX, endY);
	}

	@Override
	public void removePlayer(int id) {
		TaskManager.getInstance().createTask(ETask.RemovePlayer, id);
	}
	
	@Override
	public void enemyCharge(float[][] newEnemies, int[] ids, long[] elapsedTime){
		TaskManager.getInstance().createTask(ETask.EnemyCharge, new PositionsWrapper(newEnemies), new IntsWrapper(ids), new LongsWrapper(elapsedTime));
	}

	@Override
	public void removeBlock(int id){
		TaskManager.getInstance().createTask(ETask.RemoveBlock, id+"");
	}
	
	@Override
	public void removeEnemy(int id){
		TaskManager.getInstance().createTask(ETask.RemoveEnemy, id+"");
	}
	
	@Override
	public void removeTerritory(int id){
		TaskManager.getInstance().createTask(ETask.RemoveTerritory, id+"");
	}
	
	@Override
	public void initPlayers(float[][] players, int[] ids, int[] types){
		TaskManager.getInstance().createTask(ETask.InitializePlayers, new PositionsWrapper(players), new IntsWrapper(ids), new IntsWrapper(types));
	}
}
