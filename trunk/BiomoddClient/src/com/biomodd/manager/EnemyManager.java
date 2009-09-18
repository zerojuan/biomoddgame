package com.biomodd.manager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import com.biomodd.client.handler.message.ClientMessageMaker;
import com.biomodd.entity.Block;
import com.biomodd.entity.Enemy;
import com.biomodd.entity.Entity;
import com.biomodd.entity.PlantsGrid;
import com.biomodd.game.BiomoddGame;
import com.biomodd.util.EImgType;
import com.biomodd.util.ESndType;

public class EnemyManager extends EntityManager{
	private static EnemyManager instance;
	
	private EnemyManager(){}
	
	public static EnemyManager instance(){
		if(instance == null){
			instance = new EnemyManager();
		}
		return instance;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		for(Entity enemy : getEntities().values()){
			enemy.render(gc, sb, gr);
		}		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		BiomoddGame game = (BiomoddGame)sb;
		Sound effect = ArtManager.instance().getSound(ESndType.BOMB);
		for(Entity enemy: getEntities().values()){
			enemy.update(gc, sb, delta);
			if(CollisionManager.instance().isCollideWithMountain(gc, enemy)){
				ParticleEffectsManager.instance().addParticlesAt(ArtManager.instance().getParticleSystem(EImgType.EXPLOSION), enemy.getXPos(), enemy.getYPos());
				markForRemoval(enemy.getId());
				effect = ArtManager.instance().getSound(ESndType.BOMB);
				effect.play();				
				//game.getClient().send(ClientMessageMaker.createRemoveEnemyPkt(Integer.parseInt(enemy.getId())));
			}else if(CollisionManager.instance().isCollideWithBlock((Enemy)enemy) != null){
				Block b = CollisionManager.instance().isCollideWithBlock((Enemy)enemy);
				b.hit(((Enemy)enemy).getDamage());
				markForRemoval(enemy.getId());
				effect = ArtManager.instance().getSound(ESndType.ENEMY_DESTROY);
				effect.play();
				//game.getClient().send(ClientMessageMaker.createRemoveEnemyPkt(Integer.parseInt(enemy.getId())));
			}else if(CollisionManager.instance().isCollideWithPlant((Enemy)enemy, PlantsManager.instance().getPlantsGrid())){
				int cenX = (int)Math.floor(enemy.getXPos()/20);
				int cenY = (int)Math.floor(enemy.getYPos()/20);
				effect = ArtManager.instance().getSound(ESndType.BOMB);
				effect.play();
				markForRemoval(enemy.getId());		
				//game.getClient().send(ClientMessageMaker.createRemoveEnemyPkt(Integer.parseInt(enemy.getId())));
				ParticleEffectsManager.instance().addParticlesAt(ArtManager.instance().getParticleSystem(EImgType.EXPLOSION), enemy.getXPos(), enemy.getYPos());								
				PlantsManager.instance().markForRemoval(cenX+","+cenY);
				PlantsManager.instance().getPlantsGrid().setValueAt(cenX, cenY, PlantsGrid.DESTROYED);
			}
		}
		
	}
}
