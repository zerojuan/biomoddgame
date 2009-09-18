package com.biomodd.task.game;

import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.TerritoryManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class RemoveTerritoryTask extends RealTimeTask{
	
	private String id;
	
	public RemoveTerritoryTask(BiomoddGame game, String id){
		super(ETask.RemoveTerritory, game);
		this.id = id;
	}
	
	@Override
	public void execute(){
		TerritoryManager.instance().markForRemoval(id);
	}

}
