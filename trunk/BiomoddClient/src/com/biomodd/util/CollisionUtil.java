package com.biomodd.util;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;

import com.biomodd.entity.Block;
import com.biomodd.entity.Enemy;
import com.biomodd.entity.Entity;
import com.biomodd.entity.PlantsGrid;
import com.biomodd.entity.Territory;
import com.biomodd.manager.ArtManager;

public class CollisionUtil {
	
	public static boolean isInsideCircle(float x, float y, HashMap<String, Territory> territories){
		for(Territory territory : territories.values()){
			double terriX = territory.getXPos();
			double terriY = territory.getYPos();
			int radius = territory.getRadius();
			double dist = Math.sqrt((terriX - x)*(terriX - x) + (terriY - y)*(terriY - y));
			if(Math.floor(dist) < radius){
				return true;
			}				
		}
		return false;
	}
	
	public static Block isCollideWithBlock(Enemy e, HashMap<String,Block> blocks){
		for(Block block : blocks.values()){
			if(e.getBounds().intersects(block.getBounds())){
				return block;				
			}
		}
		
		return null;
	}
	
	public static boolean isCollideWithPlant(Enemy e, PlantsGrid plantsGrid){
		int cenX = (int)Math.floor(e.getXPos()/20);
		int cenY = (int)Math.floor(e.getYPos()/20);
		if(cenX < 0 || cenX >= plantsGrid.getColumns())
			return false;
		if(cenY < 0 || cenY >= plantsGrid.getRows()){
			return false;
		}
		return (plantsGrid.getGrid()[cenX][cenY] == PlantsGrid.PLANTED);			
	}
	
	public static boolean isCollideWithMountain(GameContainer gc, Entity e){
		float cenX = gc.getWidth()/2;
		float cenY = gc.getHeight()/2;
		int radius = ArtManager.instance().getImage(EImgType.CENTER).getWidth()/2 - 20;
		Circle mountain = new Circle(cenX, cenY, radius);
		
		return (mountain.intersects(e.getBounds()));		
	}
}
