package com.biomodd.util;

import org.newdawn.slick.Image;

public class GameConfig {

	private static GameConfig instance = null;
	
	private Image centerImage;
	
	public GameConfig(){}
	
	public Image getCenterImage() {
		return centerImage;
	}
	
	public void setCenterImage(Image centerImage) {
		this.centerImage = centerImage;
	}



	public static GameConfig instance(){
		if(instance == null){
			instance = new GameConfig();			
		}
		return instance; 
	}
	
	public long WAVE_TIME = 10000;	
	public long GROWTH_TIME = 2000;
	public long NEAR_TIME = 3000;
	public long SKILL_MOVE = 100;
	public long TYPE_CHANGE = 100;
	
	public long BLOCK_LIFE = 100;
	
	public int ENEMY_SPEED = 1000;
	public int ENEMY_DAMAGE = 20;
	
	public int TERRITORY_LIFE = 5000;
	
	
	
	
}
