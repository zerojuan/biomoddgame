package com.biomodd.task.login;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;

import com.biomodd.game.BiomoddGame;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;

public class LoginSuccessTask extends RealTimeTask{

	public LoginSuccessTask(BiomoddGame game) {
	   super(ETask.LoginSuccess, game);
	}
	
	@Override
	public void execute() {	
		game.enterState(BiomoddGame.GAMEPLAYSTATE, new EmptyTransition(), new FadeInTransition(Color.black, 2000));
	}

}
