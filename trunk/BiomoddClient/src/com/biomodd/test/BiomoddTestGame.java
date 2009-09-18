package com.biomodd.test;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BiomoddTestGame extends StateBasedGame{
	
	Image backgroundImage;
	
	public BiomoddTestGame(){
		super("BiomoddGame Test");
	}
	
	public static void main(String args[]){
		try{
			AppGameContainer app = new AppGameContainer(new BiomoddTestGame());
			app.setDisplayMode(1280, 800, true);
			app.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}	

}
