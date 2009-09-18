package com.biomodd.task.game;

import com.biomodd.client.data.IntsWrapper;
import com.biomodd.client.data.LongsWrapper;
import com.biomodd.client.data.PositionsWrapper;
import com.biomodd.entity.Territory;
import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.TerritoryManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class InitTerritoriesTask extends RealTimeTask{
	private float[][] positions;
	private int[] ids;
	private long[] elapsedTime;
	
	public InitTerritoriesTask(BiomoddGame game, PositionsWrapper positions, IntsWrapper ids, LongsWrapper elapsedTime){
		super(ETask.InitializeTerritories, game);
		this.positions = positions.getPositions();
		this.ids = ids.getInts();
		this.elapsedTime = elapsedTime.getlongs();
	}
	
	@Override
	public void execute(){
		for(int i = 0; i < positions.length; i++){
			Territory t = new Territory(ids[i]+"", positions[i][0], positions[i][1], 0f, 0f);
			t.timeStamp = elapsedTime[i];
			TerritoryManager.instance().addEntity(ids[i]+"", t);
		}
	}
		

}
