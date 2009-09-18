package com.biomodd.game;


import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import com.biomodd.task.ETask;
import com.biomodd.task.manager.TaskManager;
import com.biomodd.util.EInputState;

public class LoginState extends BasicGameState{

	private TextField host;
	private TextField port;
	private TextField userName;
	private TextField password;	
	
	private int stateID = -1;
	
	private Font textFont;
	
	private int cycler = 0;
	private int currentActive = 2;
	private int timePassedSinceLastInput = 0;
	
	private EInputState status;
	
	private String statMessage;
	
	
	
	public LoginState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public int getID() {		
		return stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {		
		textFont = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12),true);
		
		status = EInputState.ACTIVE;		
		statMessage = "Press enter to connect..";
		
		userName = new TextField(container, textFont, container.getWidth()/2, container.getHeight()/2, 100, 20);
		password = new TextField(container, textFont, container.getWidth()/2, container.getHeight()/2+30, 100, 20);
		host = new TextField(container, textFont, container.getWidth()/2, container.getHeight()/2-60,100,20);
		port = new TextField(container, textFont, container.getWidth()/2, container.getHeight()/2-30,50,20);
		
		userName.setTextColor(Color.black);
		userName.setBackgroundColor(Color.white);
		
		password.setTextColor(Color.black);
		password.setBackgroundColor(Color.white);
		
		host.setTextColor(Color.black);
		host.setBackgroundColor(Color.white);
		host.setText("localhost");
		
		port.setTextColor(Color.black);
		port.setBackgroundColor(Color.white);
		port.setText("8070");
		
		userName.setAcceptingInput(true);
		password.setAcceptingInput(true);
		host.setAcceptingInput(true);
		port.setAcceptingInput(true);
		userName.setFocus(true);		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		textFont.drawString(container.getWidth()/2 - 70, container.getHeight()/2-60, "Host:", Color.white);
		textFont.drawString(container.getWidth()/2 - 70, container.getHeight()/2-30, "Port:", Color.white);
		textFont.drawString(container.getWidth()/2 - 70, container.getHeight()/2, "Username:", Color.white);
		textFont.drawString(container.getWidth()/2 - 70, container.getHeight()/2+30, "Password:", Color.white);
		
		userName.render(container, g);
		password.render(container, g);
		host.render(container, g);
		port.render(container, g);
		
		textFont.drawString(container.getWidth()/2 - 70, container.getHeight()/2+60, statMessage, Color.gray);
		
		g.drawString("If you can read this the game works.", 200, 100);
		g.drawString("Astig. But there is still a lot to do young padawan.", 200, 120);
		g.drawString("This is not the kewlest game yet. This is just a phase.", 200, 140);
		g.drawString("Help this become beautiful..", 200, 160);
		g.drawString("Suggestions and feedback are important to success.", 230, 200);
		g.drawString("-- The Management", 550, 220);
		
		g.drawString("Seriously though. Feedback!", 600, 500);
		g.drawString("juliuscebreros@gmail.com",620, 520);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		timePassedSinceLastInput += delta;
		Input in = container.getInput();	
		if(status == EInputState.ACTIVE){
			if(in.isKeyDown(Input.KEY_TAB)){
				if(timePassedSinceLastInput > 300){				
					changeFocus();				
					timePassedSinceLastInput = 0;
				}
				
			}
			
			if(in.isKeyDown(Input.KEY_ENTER)){
				TaskManager.getInstance().createTask(ETask.Authenticate, 
						userName.getText(),
						password.getText(),
						host.getText(),
						port.getText());
				game.enterState(BiomoddGame.GAMEPLAYSTATE);
			}
		}else{						
			cycler = cycler > 2 ? 0 : cycler+1;
			String connecting = "Connecting ";
			String dot = ".";
			for(int i = 0; i < cycler; i++){
				dot += ".";
			}
			setStatMessage(connecting+dot);
		}
		TaskManager.getInstance().update();
		
	}
	
	public void setStatus(EInputState state){
		status = state;
	}
	
	public void setStatMessage(String message){
		this.statMessage = message;
	}
	
	private void changeFocus(){
		currentActive++;
		if(currentActive > 3){
			currentActive = 0;
		}
		switch(currentActive){
			case 0: host.setFocus(true);
					password.setFocus(false);
					break;
			case 1: host.setFocus(false);
					port.setFocus(true);
					break;
			case 2: port.setFocus(false);
					userName.setFocus(true);
					break;
			case 3: userName.setFocus(false);
					password.setFocus(true);
					break;
		}
	}

	
	

}
