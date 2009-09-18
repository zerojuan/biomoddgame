package com.biomodd.manager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.biomodd.entity.Entity;

public class PlayerManager extends EntityManager{
	private static PlayerManager instance;
	
	private PlayerManager(){	
	}
	
	public static PlayerManager instance(){
		if(instance == null){
			instance = new PlayerManager();
		}
		return instance;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		for(Entity player: getEntities().values()){
			player.render(gc, sb, gr);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		// TODO Auto-generated method stub
		
	}
	
	

}
