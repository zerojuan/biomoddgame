package com.biomodd.task;

public enum ETask {
	/**
	 * The task used to authenticate the user inputs with the server
	 */
	Authenticate(ETaskType.RealTime),	
	/**
	 * The task used to reset the login state after login attempt failed.
	 */
	ResetLogin(ETaskType.RealTime),	
    /**
	 * The task used to notify of a login success
	 */
	LoginSuccess(ETaskType.RealTime),
	/**
	 * The task used to create a new game.
	 */
	NewGame(ETaskType.RealTime),
	/**
	 * The task used to add a player
	 */
	AddPlayer(ETaskType.RealTime),
	/**
	 * The task used to remove a player
	 */
	RemovePlayer(ETaskType.RealTime),
	/**
	 * Change type of player
	 */
	ChangePlayerType(ETaskType.RealTime),
	/**
	 * The task used to move a player
	 */
	PlayerMove(ETaskType.RealTime),
	/**
	 * The task used when another player builds a wall
	 */
	PlayerBuild(ETaskType.RealTime),
	/**
	 * The task used when a player built a territory
	 */
	PlayerTerritorize(ETaskType.RealTime),
	/**
	 * The task used to signal plant growth
	 */
	GrowPlant(ETaskType.RealTime),
	/**
	 * The task used to signal an enemy charge
	 */
	EnemyCharge(ETaskType.RealTime),
	/**
	 * The task used to initialize the territories
	 */
	InitializeTerritories(ETaskType.RealTime),
	/**
	 * The task used to initialize the enemies
	 */
	InitializeEnemies(ETaskType.RealTime),
	/**
	 * The task used to initialize the blocks
	 */
	InitializeBlocks(ETaskType.RealTime),
	/**
	 * The task used to initialize the players
	 */
	InitializePlayers(ETaskType.RealTime),
	/**
	 * The task used to remove block
	 */
	RemoveBlock(ETaskType.RealTime),
	/**
	 * The task used to remove plants
	 */
	RemovePlant(ETaskType.RealTime),
	/**
	 * The task used to remove enemy
	 */
	RemoveEnemy(ETaskType.RealTime),
	/**
	 * The task used to remove a territory
	 */
	RemoveTerritory(ETaskType.RealTime);
	
	/**
	 * The <code>ETaskType</code> enumeration.
	 */
	private final ETaskType type;
	
	private ETask(ETaskType type) {
		this.type = type;
	}	
	/**
	 * Retrieve the type of this task.
	 * @return The <code>ETaskType</code> enumeration.
	 */
	public ETaskType getType() {
		return this.type;
	}
	
	public enum ETaskType{
		RealTime,
		Certified
	}
}
