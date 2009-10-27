package com.biomodd.manager;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.biomodd.entity.Entity;
import com.biomodd.entity.Plant;
import com.biomodd.entity.PlantsGrid;
import com.biomodd.entity.Territory;
import com.biomodd.util.EImgType;
import com.biomodd.util.GameConfig;
import com.biomodd.util.TweetMessageFactory;

public class PlantsManager extends EntityManager{

	private static PlantsManager instance;
	
	private PlantsGrid plantsGrid;
	
	private long timeBeforeNextGrowth;
	
	private int marker;
	
	private PlantsManager(){
		timeBeforeNextGrowth = GameConfig.instance().GROWTH_TIME;
	}
	
	public static PlantsManager instance(){
		if(instance == null){
			instance = new PlantsManager();
		}
		return instance;
	}

	public PlantsGrid getPlantsGrid() {
		return plantsGrid;
	}

	public void setPlantsGrid(PlantsGrid plantsGrid) {
		this.plantsGrid = plantsGrid;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		
		for(Entity entity : getEntities().values()){
			entity.render(gc, sb, gr);
		}		
		drawPolygons(gr);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		timeBeforeNextGrowth -= delta;
		
		if(isTimeForPlantGrowth()){
			timeBeforeNextGrowth = GameConfig.instance().GROWTH_TIME;
			growPlants(delta);
		}
		if(marker == 0){
			if(getEntities().size() > 600){
				TweetManager.instance().updateStatus(TweetMessageFactory.generateUpdateStatus(601));
				marker = 1;			
			}
		}else if(marker == 1){
			if(getEntities().size() > 750){
				TweetManager.instance().updateStatus(TweetMessageFactory.generateUpdateStatus(751));
				marker = 2;			
			}
		}else if(marker == 2){
			if(getEntities().size() > 1000){
				TweetManager.instance().updateStatus(TweetMessageFactory.generateUpdateStatus(1001));
				marker = 3;			
			}
		}
	}
	
	private void drawPolygons(Graphics gr){		
		gr.setColor(Color.orange);
		int cols = plantsGrid.getColumns();
		int rows = plantsGrid.getRows();
		for(int x = 0; x < cols; x++){
			for(int y = 0; y < rows; y++){				
				if(plantsGrid.isEdge(x, y)){
					Point o = new Point(x,y);
					Point n = new Point(x,y-1);
					Point s = new Point(x,y+1);
					Point w = new Point(x-1,y);
					Point e = new Point(x+1,y);
					Point nw = new Point(x-1,y-1);
					Point ne = new Point(x+1,y-1);
					Point sw = new Point(x-1,y+1);
					Point se = new Point(x+1,y+1);
					
					drawEdge(gr, o, n);
					drawEdge(gr, o, s);
					drawEdge(gr, o, w);
					drawEdge(gr, o, e);
					drawEdge(gr, o, nw);
					drawEdge(gr, o, sw);
					drawEdge(gr, o, ne);
					drawEdge(gr, o, se);					
				}
			}
		}
	}
	
	private void drawEdge(Graphics gr, Point orig, Point dest){
		if(plantsGrid.isEdge(dest.x, dest.y)){
			gr.drawLine((orig.x*20)+10, (orig.y*20)+10, (dest.x*20)+10, (dest.y*20)+10);
		}
	}
	
	private boolean isTimeForPlantGrowth(){
		return timeBeforeNextGrowth <= 0;
	}
	
	private void growPlants(int delta){
		int rows = plantsGrid.getRows();
		int cols = plantsGrid.getColumns();	
		List<Point> newPlants = new ArrayList<Point>();
		HashMap<String,Entity> plants = getEntities();
		 for(int y = 0; y < rows; y++){
			for(int x = 0; x < cols; x++){
				String id = x+","+y;
				if(plantsGrid.isDestroyed(x,y)){
					plantsGrid.setValueAt(x,y, PlantsGrid.DEFAULT);
				}
				if(plantsGrid.isGrowable(x, y)){					
					newPlants.add(new Point(x,y));
					if(plants.get(id) != null){
						((Plant)plants.get(id)).grow(delta);
					}else{
						plants.put(id, new Plant(id,x*20,y*20,0,0,ArtManager.instance().getImage(EImgType.PLANT)));
					}								
				}else{
					if(plants.get(id) != null){
					   ((Plant)plants.get(id)).grow(delta);
					}
				}
			}
		}
		plantsGrid.setValuesAt(newPlants, PlantsGrid.PLANTED);
	}
	
	public void setValuesAtGameGrid(Territory territory, int value){
		int cenX = (int)Math.floor(territory.getXPos()/20);
		int cenY = (int)Math.floor(territory.getYPos()/20);
		
		plantsGrid.setValueAt(cenX, cenY, value);
		plantsGrid.setValueAt(cenX-1, cenY-1, value);
		plantsGrid.setValueAt(cenX, cenY-1, value);
		plantsGrid.setValueAt(cenX+1, cenY-1, value);
		plantsGrid.setValueAt(cenX+1, cenY, value);
		plantsGrid.setValueAt(cenX+1, cenY+1, value);		
		plantsGrid.setValueAt(cenX, cenY+1, value);
		plantsGrid.setValueAt(cenX-1, cenY+1, value);
		plantsGrid.setValueAt(cenX-1, cenY, value);
		
		plantsGrid.setValueAt(cenX-2, cenY-1, value);
		plantsGrid.setValueAt(cenX-2, cenY, value);
		plantsGrid.setValueAt(cenX-2, cenY+1, value);
		
		plantsGrid.setValueAt(cenX+2, cenY-1, value);
		plantsGrid.setValueAt(cenX+2, cenY, value);
		plantsGrid.setValueAt(cenX+2, cenY+1, value);
		
		plantsGrid.setValueAt(cenX-1, cenY-2, value);
		plantsGrid.setValueAt(cenX, cenY-2, value);
		plantsGrid.setValueAt(cenX+1, cenY-2, value);
		
		plantsGrid.setValueAt(cenX-1, cenY+2, value);
		plantsGrid.setValueAt(cenX, cenY+2, value);
		plantsGrid.setValueAt(cenX+1, cenY+2, value);
		
	}
	
	
	
}
