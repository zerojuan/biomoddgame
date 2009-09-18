package com.biomodd.task.game;

import com.biomodd.client.data.IntsWrapper;
import com.biomodd.client.data.LongsWrapper;
import com.biomodd.client.data.PositionsWrapper;
import com.biomodd.entity.Enemy;
import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.ArtManager;
import com.biomodd.manager.EnemyManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;
import com.biomodd.util.EImgType;

public class InitEnemiesTask extends RealTimeTask{
	private float[][] positions;
	private int[] ids;
	private long[] elapsedTime;
	
	public InitEnemiesTask(BiomoddGame game, PositionsWrapper positions, IntsWrapper ids, LongsWrapper elapsedTime){
		super(ETask.InitializeEnemies, game);
		this.positions = positions.getPositions();
		this.ids = ids.getInts();
		this.elapsedTime = elapsedTime.getlongs();
	}
	
	@Override
	public void execute(){
		for(int i = 0; i < positions.length; i++){
			Enemy t = new Enemy(ids[i]+"", elapsedTime[i], positions[i][0], positions[i][1], 0f, 0f, ArtManager.instance().getImage(EImgType.ENEMY));
			
			EnemyManager.instance().addEntity(ids[i]+"", t);
		}
	}

}
