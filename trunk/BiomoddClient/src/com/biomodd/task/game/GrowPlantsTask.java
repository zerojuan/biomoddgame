package com.biomodd.task.game;

import com.biomodd.client.data.GridsWrapper;
import com.biomodd.entity.Plant;
import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.ArtManager;
import com.biomodd.manager.PlantsManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;
import com.biomodd.util.EImgType;

public class GrowPlantsTask extends RealTimeTask{
	private int[][] grid;
	
	public GrowPlantsTask(BiomoddGame game, GridsWrapper plantsGrid){
		super(ETask.GrowPlant, game);
		this.grid = plantsGrid.getGrid();
	}
	
	@Override
	public void execute(){
		for(int i = 0; i < grid.length; i++){
			Plant plant = new Plant("",grid[i][0]*20, grid[i][1]*20,0,0,ArtManager.instance().getImage(EImgType.PLANT));
			plant.setId(plant.getXPos()+","+plant.getYPos());
			PlantsManager.instance().addEntity(plant.getId(), plant);
		}
	}

}
