package com.biomodd.task.game;

import com.biomodd.entity.Player;
import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.PlayerManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class AddMOBTask extends RealTimeTask{

	private int id;
	private float x;
	private float y;
	
	private int type;
	
	/**
	 * Constructor of <code>AddMOBTask</code>.
	 * @param game The <code>Game</code> instance.
	 * @param id The ID number of the entity to be added.	
	 * @param x The x coordinate of the initial position.
	 * @param y The y coordinate of the initial position.
	 * @param local The flag indicates if this mob is controlled locally.
	 */
	public AddMOBTask(BiomoddGame game, int id, float x, float y, int type) {
		super(ETask.AddPlayer, game);
		this.id = id;
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	@Override
	public void execute(){
		Player enemy = new Player(id+"", x, y, 0, 0, type);
		
		PlayerManager.instance().addEntity(id+"", enemy);		
	}
	
	@Override
	public boolean equals(Object object) {
		if(!super.equals(object)) return false;
		AddMOBTask given = (AddMOBTask)object;
		return ((this.id == given.id) && (this.enumn == given.enumn));
	}
}
