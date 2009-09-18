package com.biomodd.task.game;

import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.PlayerManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class RemoveMOBTask extends RealTimeTask{
	private int id;
	
	public RemoveMOBTask(BiomoddGame game, int id){
		super(ETask.RemovePlayer, game);
		this.id = id;
	}
	
	@Override
	public void execute(){
		PlayerManager.instance().markForRemoval(id+"");
	}
	
	@Override
	public boolean equals(Object object) {
		if(!super.equals(object)) return false;
		RemoveMOBTask given = (RemoveMOBTask)object;
		return ((this.id == given.id) && (this.enumn == given.enumn));
	}

}
