package com.biomodd.task.game;

import com.biomodd.game.BiomoddGame;
import com.biomodd.game.GameplayState;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class NewGameTask extends RealTimeTask{

	private int id;
	
	public NewGameTask(BiomoddGame game, int id){
		super(ETask.NewGame, game);
		this.id = id;
	}
	
	@Override
	public void execute(){
		GameplayState gamez = (GameplayState)(game.getState(BiomoddGame.GAMEPLAYSTATE));
		gamez.setPlayerId(id);
		
	}
}
