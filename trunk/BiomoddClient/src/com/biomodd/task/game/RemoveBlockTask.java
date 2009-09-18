package com.biomodd.task.game;

import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.BlocksManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class RemoveBlockTask extends RealTimeTask{
	
	private String id;
	
	public RemoveBlockTask(BiomoddGame game, String id){
		super(ETask.RemoveBlock, game);
		this.id = id;
	}
	
	@Override
	public void execute(){
		BlocksManager.instance().markForRemoval(id);
	}

}
