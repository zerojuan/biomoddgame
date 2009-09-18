package com.biomodd.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class WarningSign extends Entity{

	private Image warningImg;
	
	private float scale;
	private float direction;
	
	private boolean visible;
	
	public WarningSign(String id, float x, float y, Image warningImage) {
		super(id, new Vector2f(x,y));
		setBounds(new Rectangle(x,y,20,20));		
		warningImg = warningImage;		
		scale = 1f;
		direction = 1;
		visible = false;
	}
	
	public boolean visible(){
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	
	public void setRotation(int waveDirection){
		warningImg.setRotation(0);
		warningImg.setRotation(-waveDirection);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		warningImg.drawCentered(position.x,position.y);
		warningImg.setAlpha(scale);
		if(scale > 1){
			direction*=-1;
		}
		if(scale < .5f){
			direction *=-1;
		}
		
		scale+=(direction)*.1f;
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		// TODO Auto-generated method stub
		
	}

}
