package com.biomodd.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.biomodd.client.handler.message.ClientMessageMaker;
import com.biomodd.game.BiomoddGame;
import com.biomodd.manager.ArtManager;
import com.biomodd.manager.BlocksManager;
import com.biomodd.manager.CollisionManager;
import com.biomodd.manager.PlantsManager;
import com.biomodd.manager.TerritoryManager;
import com.biomodd.util.EImgType;
import com.biomodd.util.ESndType;
import com.biomodd.util.GameConfig;

public class Player extends Entity{

	public static final int BUILDER = 0;
	public static final int TERRORIST = 1;
	
	
	private long timeSinceTypeChange;
	private long timeSinceSkillMove;
	
	private Image builderImage;
	private Image terroristImage;
	
	/**
	 * The type of player
	 */
	private int type;
	
	
	public Player(String id, float x, float y, float speedX, float speedY, int type) {
		super(id);		
		Rectangle bounds = new Rectangle(x, y, 20, 20);
		this.setBounds(bounds);
		setPosition(x, y);
		timeSinceTypeChange = 0;
		timeSinceSkillMove = 0;
		this.type = type;
		builderImage = ArtManager.instance().getImage(EImgType.BUILDER);
		terroristImage = ArtManager.instance().getImage(EImgType.TERRORIST);
		this.type = BUILDER;
	}
	
	public long getTimeSinceTypeChange() {
		return timeSinceTypeChange;
	}

	public void setTimeSinceTypeChange(long timeSinceTypeChange) {
		this.timeSinceTypeChange = timeSinceTypeChange;
	}

	public long getTimeSinceSkillMove() {
		return timeSinceSkillMove;
	}

	public void setTimeSinceSkillMove(long timeSinceSkillMove) {
		this.timeSinceSkillMove = timeSinceSkillMove;
	}

	public void move(float x, float y){
		setPosition(x, y);
	}

	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		if(getType() == Player.BUILDER){
			builderImage.drawCentered(getXPos(), getYPos());
		}else{
			terroristImage.drawCentered(getXPos(), getYPos());
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		timeSinceTypeChange += delta;
		timeSinceSkillMove += delta;
		
		float playerX = getXPos();
		float playerY = getYPos();
		float tempX = playerX;
		float tempY = playerY;
		
		Input in = gc.getInput();
		BiomoddGame game = (BiomoddGame)sb;	
		
		boolean changedPosition = false;
		if(in.isKeyDown(Input.KEY_UP) && playerY > 0){
			playerY -= 3;
			changedPosition = true;
		}
		if(in.isKeyDown(Input.KEY_DOWN) && playerY < gc.getHeight() - 5){
			playerY += 3;
			changedPosition = true;
		}
		if(in.isKeyDown(Input.KEY_RIGHT) && playerX < gc.getWidth() - 5){
			playerX += 3;
			changedPosition = true;
		}
		if(in.isKeyDown(Input.KEY_LEFT) && playerX > 12){
			playerX -= 3;
			changedPosition = true;
		}		
				
		move(playerX, playerY);
		if(CollisionManager.instance().isCollideWithMountain(gc, this)){
			move(tempX,tempY);
				
		}
		if(changedPosition){				
			//game.getClient().send(ClientMessageMaker.createMovePkt(Integer.parseInt(id), this.getXPos(), this.getYPos()));
		}
		
		
		if(in.isKeyPressed(Input.KEY_ENTER)){
			if(timeSinceTypeChange >= GameConfig.instance().TYPE_CHANGE){
				if(getType() == Player.BUILDER){
					setType(Player.TERRORIST);
				}else{
					setType(Player.BUILDER);
				}
				timeSinceTypeChange = 0;
				//game.getClient().send(ClientMessageMaker.createChangeTypePkt(Integer.parseInt(id), getType()));
			}						
		}
		
		if(in.isKeyPressed(Input.KEY_SPACE)){			
			if(timeSinceSkillMove >= GameConfig.instance().SKILL_MOVE){
				Long id = System.currentTimeMillis();
				if(getType() == Player.BUILDER){
					if(CollisionManager.instance().isInsideCircle(playerX, playerY)){
						BlocksManager.instance().addEntity(id.toString(), new Block(id.toString(),playerX, playerY, ArtManager.instance().getImage(EImgType.BLOCK)));
						//game.getClient().send(ClientMessageMaker.createAddBlockPkt(playerX, playerY));
						Sound effect = ArtManager.instance().getSound(ESndType.BUILD);
						effect.play();
						timeSinceSkillMove = 0;
					}					
				}else{					
					Territory t = new Territory(id.toString(), playerX, playerY, 0,0);
					TerritoryManager.instance().addEntity(id.toString(), t);
					//game.getClient().send(ClientMessageMaker.createTerritoryPkt(playerX, playerY, 50));
					PlantsManager.instance().setValuesAtGameGrid(t, PlantsGrid.TERRITORIZED);
					Sound effect = ArtManager.instance().getSound(ESndType.TERRITORIZE);
					effect.play();
					timeSinceSkillMove = 0;
				}
				
			}
		}
		
	}
	
	
}
