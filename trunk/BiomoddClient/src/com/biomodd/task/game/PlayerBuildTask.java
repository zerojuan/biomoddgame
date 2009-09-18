package com.biomodd.task.game;

import org.newdawn.slick.util.Log;

import com.biomodd.entity.Block;
import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.ArtManager;
import com.biomodd.manager.BlocksManager;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;
import com.biomodd.util.EImgType;

public class PlayerBuildTask extends RealTimeTask {

	private int id;
	private float x;
	private float y;
	private int health;
	
	public PlayerBuildTask(BiomoddGame game, int id, float x, float y, int health){
		super(ETask.PlayerBuild, game);
		this.id = id;
		this.x = x;
		this.y = y;
		this.health = health;
	}
	
	@Override
	public void execute(){
		Block block = new Block(id+"", x, y, ArtManager.instance().getImage(EImgType.BLOCK));
		Log.info("Adding block.. " + id);
		BlocksManager.instance().addEntity(id+"", block);
	}
}
