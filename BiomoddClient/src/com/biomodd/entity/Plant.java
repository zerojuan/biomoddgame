package com.biomodd.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class Plant extends Entity{

	private float life;	
	private Image plantImg;	
	
	public static final int REVOLVE_SPEED = 5;
	public static final float MATURITY = 50;
	public static final float LIFE_STEP = 10;
	
	
	public Plant(String id, float x, float y, float speedX, float speedY, Image plantImage) {
		super(id, new Vector2f(x, y), new Vector2f(speedX, speedY));
		Rectangle bounds = new Rectangle(x, y, 20, 20);
		setBounds(bounds);
		life = 0;		
		plantImg = plantImage.copy();
	}
	
	public boolean canReproduce(){
		if(isMature()){			
			return true;
		}
		return false;
	}
	
	public void grow(int delta){
		if(!isMature()){								
			life += LIFE_STEP;
		}else{		
			life = MATURITY;		
			return;
		}				
	}
	
	private boolean isMature(){
		return life >= MATURITY;
	}	

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
				
		plantImg.draw(position.x, position.y, life/MATURITY);
		if(!isMature()){
			plantImg.rotate(1);
		}else{
			plantImg.rotate(0.25f);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		// TODO Auto-generated method stub
		
	}

}
