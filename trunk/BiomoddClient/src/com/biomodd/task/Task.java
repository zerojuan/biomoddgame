package com.biomodd.task;

import com.biomodd.game.BiomoddGame;

public abstract class Task implements ITask{

	/**
	 * The ETask enumeration of the task
	 */
	protected final ETask enumn;
	
	/**
	 * The instance of the game
	 */
	protected final BiomoddGame game;
	
	/**
	 * Constructor of <code>Task</code>.
	 * @param enumn The <code>ETask</code> of this </code>Task</code>.
	 * @param game The <code>BiomoddGame</code> instance.
	 */
	public Task(ETask enumn, BiomoddGame game) {
		this.enumn = enumn;
		this.game = game;
	}
	
	@Override
	public ETask getEnumn(){
		return this.enumn;
	}
}
