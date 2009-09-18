package com.biomodd.task.game;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.biomodd.entity.Entity;
import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.PlayerManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class MovePlayerTask extends RealTimeTask{
	private static final Logger logger = Logger.getLogger(MovePlayerTask.class.getName());
	
	private int playerId;
	private float x;
	private float y;
	
	public MovePlayerTask(BiomoddGame game, int playerId, float x, float y){
		super(ETask.PlayerMove, game);
		this.x = x;
		this.y = y;
		this.playerId = playerId;
	}
	
	@Override
	public void execute(){	
		logger.log(Level.FINE, "Move player task ID: " + playerId);
		Entity player = PlayerManager.instance().getEntity(playerId+"");
		player.setPosition(x, y);
	}

}
