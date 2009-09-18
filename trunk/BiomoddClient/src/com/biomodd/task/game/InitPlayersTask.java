package com.biomodd.task.game;

import com.biomodd.client.data.IntsWrapper;
import com.biomodd.client.data.PositionsWrapper;
import com.biomodd.entity.Player;
import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.PlayerManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class InitPlayersTask extends RealTimeTask{

	private float[][] positions;
	private int[] ids;
	private int[] types;
	
	public InitPlayersTask(BiomoddGame game, PositionsWrapper positions, IntsWrapper ids, IntsWrapper type){
		super(ETask.InitializePlayers, game);
		this.positions = positions.getPositions();
		this.ids = ids.getInts();
		this.types = ids.getInts();		
	}
	
	@Override
	public void execute(){
		for(int i = 0; i < positions.length; i++){
			Player p = new Player(ids[i]+"", positions[i][0], positions[i][1], 0,0, types[i]);
				
			PlayerManager.instance().addEntity(ids[i]+"", p);
		}
	}
}
