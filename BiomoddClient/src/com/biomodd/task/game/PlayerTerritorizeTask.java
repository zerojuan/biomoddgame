package com.biomodd.task.game;

import com.biomodd.entity.Territory;
import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.TerritoryManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class PlayerTerritorizeTask extends RealTimeTask{
	private int id;
	private float x;
	private float y;
	private Long timeStamp;
	
	public PlayerTerritorizeTask(BiomoddGame game, int id, float x, float y, Long timeStamp){
		super(ETask.PlayerTerritorize, game);
		this.id = id;
		this.x = x;
		this.y = y;
		this.timeStamp = timeStamp;
	}
	
	@Override
	public void execute(){
		Territory territory = new Territory(id+"", x, y, 0, 0);
		territory.timeStamp = timeStamp;
		TerritoryManager.instance().addEntity(id+"", territory);
	}

}
