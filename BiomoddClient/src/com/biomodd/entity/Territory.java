package com.biomodd.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import com.biomodd.util.GameConfig;

public class Territory extends Entity{

	private int life;
	private int radius;
	
	public Color territoryColor;
	
	public Circle myTerritory;
	
	public Long timeStamp;
	
	public Territory(String id, float x, float y, float speedX, float speedY) {
		super(id, new Vector2f(x, y), new Vector2f(speedX, speedY));
		setId(id);
		life = GameConfig.instance().TERRITORY_LIFE;
		radius = 50;
		myTerritory = new Circle(x, y, radius);
		territoryColor = new Color(0,200,0,80);
	}
	
	public void fade(int delta){
		Log.debug("Life: " + life);
		life-=delta;
		float lifePercentage = (((float)life/(float)GameConfig.instance().TERRITORY_LIFE));
		Log.debug("Life Percentage: " + lifePercentage);
		territoryColor.a = lifePercentage - 0.2f;
	}
	
	public int getLife(){
		return life;
	}
	
	public int getRadius(){
		return radius;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {		
		gr.setColor(territoryColor);
		gr.fill(myTerritory);
		
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		fade(delta);
	}

}
