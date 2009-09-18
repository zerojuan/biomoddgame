package com.biomodd.manager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.biomodd.entity.Block;
import com.biomodd.entity.Entity;

public class BlocksManager extends EntityManager{
	private static BlocksManager instance;
	
	private BlocksManager(){}
	
	public static BlocksManager instance(){
		if(instance == null){
			instance = new BlocksManager();
		}
		return instance;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		for(Entity blocks : getEntities().values()){
			blocks.render(gc, sb, gr);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for(Entity block : getEntities().values()){
			if(((Block)block).isDead()){
				markForRemoval(block.getId());
			}
		}
		
	}
}
