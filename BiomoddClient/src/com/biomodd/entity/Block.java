package com.biomodd.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.biomodd.util.GameConfig;

public class Block extends Entity {

	private Image blockImg;
	
	private int life;
	
	public Block(String id, float x, float y, Image blockImg) {
		super(id, new Vector2f(x,y));
		setId(id);
		setBounds(new Rectangle(x-10,y-10,20,20));
		this.blockImg = blockImg.copy();
		life = (int)(GameConfig.instance().BLOCK_LIFE);
	}
	
	public void setLife(int life){
		this.life = life;
	}
	
	public void hit(int damage){
		life -= damage;
	}
	
	public boolean isDead(){
		return life <= 0;
	}	

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		blockImg.setAlpha(life/GameConfig.instance().BLOCK_LIFE);
		blockImg.drawCentered(position.x, position.y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		// TODO Auto-generated method stub
		
	}
	
	

}
