package com.biomodd.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.biomodd.entity.Entity;

/**
 * Class that manages instances of the entities of the game
 * @author Julius
 *
 */
public abstract class EntityManager {

	private HashMap<String,Entity> entities;
	
	private List<String> garbageList;
	
	public EntityManager(){
		entities = new HashMap<String,Entity>();		
		garbageList = new ArrayList<String>();
	}
	
	public HashMap<String,Entity> getEntities(){
		return entities;
	}
	
	public Entity getEntity(String id){
		return entities.get(id);
	}
	
	public void addEntity(String id, Entity entity){
		entities.put(id, entity);
	}
	
	private void removeEntity(String id){
		entities.remove(id);
	}
	
	public void markForRemoval(String id){
		garbageList.add(id);
	}
	
	public void cleanUpEntities(){
		for(String id : garbageList){
			removeEntity(id);
		}
		garbageList.clear();
	}
	
	public abstract void update(GameContainer gc, StateBasedGame sb, int delta);
 
    public abstract void render(GameContainer gc, StateBasedGame sb, Graphics gr);
}
