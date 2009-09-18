package com.biomodd.manager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.biomodd.entity.Entity;
import com.biomodd.entity.PlantsGrid;
import com.biomodd.entity.Territory;

public class TerritoryManager extends EntityManager{
	private static TerritoryManager instance;
	
	private TerritoryManager(){}
	
	public static TerritoryManager instance(){
		if(instance == null){
			instance = new TerritoryManager();
		}
		return instance;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		for(Entity entity : getEntities().values()){
			entity.render(gc, sb, gr);
		}		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for(Entity entity: getEntities().values()){
			Territory territory = (Territory)entity;
			territory.update(gc, sb, delta);
			if(territory.getLife() <= 0){
				markForRemoval(territory.getId());
				PlantsManager.instance().setValuesAtGameGrid(territory, PlantsGrid.DEFAULT);
			}
		}
	}
}
