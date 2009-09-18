package com.biomodd.task.login;

import com.biomodd.game.BiomoddGame;
import com.biomodd.game.LoginState;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;
import com.biomodd.util.EInputState;

public class ResetLoginTask extends RealTimeTask{

	private String reason;
	
	public ResetLoginTask(BiomoddGame game, String reason){
		super(ETask.ResetLogin,game);
		this.reason = reason;
	}
	
	@Override
	public void execute() {
		LoginState loginState = (LoginState)(game.getState(BiomoddGame.LOGINSTATE));
		loginState.setStatMessage("Failed to login: " + reason);
		loginState.setStatus(EInputState.ACTIVE);
	}

}
