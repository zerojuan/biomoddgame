package com.biomodd.task.game;

import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.EnemyManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class RemoveEnemyTask extends RealTimeTask{
	
	private String id;
	
	public RemoveEnemyTask(BiomoddGame game, String id){
		super(ETask.RemoveTerritory, game);
		this.id = id;
	}
	
	@Override
	public void execute(){
		EnemyManager.instance().markForRemoval(id);
	}

}