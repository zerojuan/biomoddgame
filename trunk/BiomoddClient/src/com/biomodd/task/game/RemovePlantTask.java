package com.biomodd.task.game;

import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.PlantsManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class RemovePlantTask extends RealTimeTask{
	
	private String id;
	
	public RemovePlantTask(BiomoddGame game, String id){
		super(ETask.RemoveTerritory, game);
		this.id = id;
	}
	
	@Override
	public void execute(){
		PlantsManager.instance().markForRemoval(id);
	}

}
