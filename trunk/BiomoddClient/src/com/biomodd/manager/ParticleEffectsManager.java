package com.biomodd.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class ParticleEffectsManager{
	private HashMap<String,ParticleSystem> entities;
	
	private List<String> garbageList;
	
	private static ParticleEffectsManager instance;

	private ParticleEffectsManager(){
		entities = new HashMap<String, ParticleSystem>();
		garbageList = new ArrayList<String>();
	}
	
	public static ParticleEffectsManager instance(){
		if(instance == null){
			instance = new ParticleEffectsManager();
		}
		return instance;
	}
	
	private HashMap<String,ParticleSystem> getEntities(){
		return entities;
	}
	
	public void addParticle(String id, ParticleSystem entity){
		entities.put(id, entity);
	}
	
	private void removeParticle(String id){
		entities.remove(id);
	}
	
	public void markForRemoval(String id){
		garbageList.add(id);
	}
	
	public void cleanUp(){
		for(String id : garbageList){
			removeParticle(id);
		}
		garbageList.clear();
	}
		
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		for(ParticleSystem p : getEntities().values()){
			p.render();
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for(ParticleSystem p : getEntities().values()){
			p.update(delta);
			if(p.getEmitter(0).completed()){				
				markForRemoval(p.getPositionX()+","+p.getPositionY());
			}
		}	
		
	}
	
	public void addParticlesAt(ParticleSystem particleSystem, float x, float y){
		try{
			ParticleSystem myP = particleSystem.duplicate();
			myP.reset();
			myP.setPosition(x, y);
			addParticle(x+","+y, myP);
		}catch(SlickException se){
			Log.error(se);
		}
	}
	
	
}
