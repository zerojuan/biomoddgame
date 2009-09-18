package com.biomodd.manager;

import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.util.Log;

import com.biomodd.util.EImgType;
import com.biomodd.util.ESndType;

public class ArtManager {
	
	private Image backgroundImage;
	private Image builderImage;
	private Image terroristImage;
	private Image blockImage;
	private Image centerImage;
	private Image enemyImage;
	private Image plantImage;
	private Image warningImage;
	private Image particleImage;
	
	private Sound blastSound;
	private Sound fizzySound;
	private Sound hollowSound;
	private Sound sploshSound;
	private Sound warningSound;
	
	private ParticleSystem explosionSystem;
	
	private static ArtManager instance;
	
	private ArtManager() throws SlickException{
		backgroundImage = new Image("assets/background.jpg");
		builderImage = new Image("assets/builder.png");
		terroristImage = new Image("assets/terrorist.png");
		blockImage = new Image("assets/block.png");
		centerImage = new Image("assets/spiral.png");
		enemyImage = new Image("assets/evil.png");
		plantImage = new Image("assets/plant.png");
		warningImage = new Image("assets/warning.png");
		particleImage = new Image("assets/plant.png");	
		
		blastSound = new Sound("assets/sounds/blast_sound.wav");
		fizzySound = new Sound("assets/sounds/fizzy.wav");
		hollowSound = new Sound("assets/sounds/hollow.wav");
		sploshSound = new Sound("assets/sounds/splosh.wav");
		warningSound = new Sound("assets/sounds/warning.wav");
		
		
		try{		
			explosionSystem = ParticleIO.loadConfiguredSystem("assets/mini-explosion.xml");
			explosionSystem.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
		}catch(IOException ie){			
			Log.error(ie);
		}
	}
	
	public Image getImage(EImgType imgType){
		switch(imgType){
			case BACKGROUND: return backgroundImage;
			case BUILDER: return builderImage;
			case TERRORIST: return terroristImage;
			case BLOCK: return blockImage;
			case CENTER: return centerImage;
			case PLANT: return plantImage;
			case WARNING: return warningImage;
			case PARTICLE: return particleImage;
			case ENEMY: return enemyImage;
		}
		return null;
	}
	
	public Sound getSound(ESndType sndType){
		switch(sndType){
			case ENEMY_DESTROY: return sploshSound;
			case BOMB: return blastSound;
			case TERRITORIZE: return fizzySound;
			case BUILD: return hollowSound;
			case WARNING: return warningSound;
		}
		return null;
	}
	
	public ParticleSystem getParticleSystem(EImgType imgType){
		switch(imgType){
			case EXPLOSION: return explosionSystem;
			default: return explosionSystem;
		}
	}
	
	public static ArtManager instance(){
		if(instance == null){
			try{
				instance = new ArtManager();
			}catch(SlickException se){
				Log.error(se);
			}
		}
		return instance;
	}
	

	
	
}
