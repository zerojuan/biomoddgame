package com.biomodd.task.game;

import com.biomodd.client.data.IntsWrapper;
import com.biomodd.client.data.LongsWrapper;
import com.biomodd.client.data.PositionsWrapper;
import com.biomodd.entity.Block;
import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.ArtManager;
import com.biomodd.manager.BlocksManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;
import com.biomodd.util.EImgType;

public class InitBlocksTask extends RealTimeTask{
	private float[][] positions;
	private int[] ids;
	//private long[] elapsedTime;
	private int[] healths;
	
	public InitBlocksTask(BiomoddGame game, PositionsWrapper positions, IntsWrapper ids, IntsWrapper healths){
		super(ETask.InitializeTerritories, game);
		this.positions = positions.getPositions();
		this.ids = ids.getInts();
	
		this.healths = healths.getInts();
	}
	
	@Override
	public void execute(){
		for(int i = 0; i < positions.length; i++){
			Block t = new Block(ids[i]+"", positions[i][0], positions[i][1], ArtManager.instance().getImage(EImgType.BLOCK));
			t.setLife(healths[i]);			
			BlocksManager.instance().addEntity(ids[i]+"", t);
		}
	}

}
