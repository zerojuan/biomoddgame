package com.biomodd.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.biomodd.util.GameConfig;

public class Enemy extends Entity{

	private Long timeCreated;
	private int speedRandomizer;
	
	private Image enemyImg;
	
	private int damage;
	
	private Vector2f startPos;	
	
	public Enemy(String id, float x, float y, float speedX, float speedY, Image img) {
		super(id, new Vector2f(x, y), new Vector2f(speedX,speedY));
		enemyImg = img.copy();
		startPos = getPosition().copy();
		damage = GameConfig.instance().ENEMY_DAMAGE;
		speedRandomizer = (int)(Math.random() * 1000);
		setTimeCreated(System.currentTimeMillis());
	}
	
	/**
	 * In preparation for server side scripts
	 * @param id
	 * @param timeStamp time the enemy was supposed to be created
	 * @param x
	 * @param y
	 * @param speedX
	 * @param speedY
	 */
	public Enemy(String id, long timeStamp, float x, float y, float speedX, float speedY, Image img) {
		super(id, new Vector2f(x, y), new Vector2f(speedX,speedY));
		enemyImg = img.copy();
		startPos = getPosition().copy();
		damage = GameConfig.instance().ENEMY_DAMAGE;
		speedRandomizer = (int)(Math.random() * 1000);
		setTimeCreated(timeStamp);
	}
		
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setTimeCreated(Long timeCreated){
		this.timeCreated = timeCreated;
	}

	public void move(float destX, float destY, int delta){
		int speed = GameConfig.instance().ENEMY_SPEED;
		float incX = (globalToLocal(startPos.x, destX)) / (speed + speedRandomizer);
		float incY = (globalToLocal(startPos.y, destY)) / (speed + speedRandomizer);			
		
		//xPos = startX + (incX * (elapsedTime / 100));
		//yPos = startY + (incY * (elapsedTime / 100));
		setPosition(position.x+incX, position.y +incY);
		
		/*
		float incX = position.x;
		float incY = position.y;
		
		PointF polarCoord = MathUtil.cartesianToPolar(new PointF(incX, incY));
		if(polarCoord.x > 0){
			polarCoord.x--;
		}
		PointF cartCoord = MathUtil.polarToCartesian(polarCoord);
		setPos(cartCoord.x, -cartCoord.y);*/
		
	}	
	
	public float globalToLocal(float local, float dest){
		return dest - local;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		enemyImg.drawCentered(position.x, position.y);
		enemyImg.rotate(1);		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		move(gc.getWidth()/2, gc.getHeight()/2, delta);
	}
}
