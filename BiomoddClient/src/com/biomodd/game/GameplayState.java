package com.biomodd.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.biomodd.entity.Enemy;
import com.biomodd.entity.PlantsGrid;
import com.biomodd.entity.Player;
import com.biomodd.entity.WarningSign;
import com.biomodd.input.Controller;
import com.biomodd.manager.ArtManager;
import com.biomodd.manager.BlocksManager;
import com.biomodd.manager.EnemyManager;
import com.biomodd.manager.ParticleEffectsManager;
import com.biomodd.manager.PlantsManager;
import com.biomodd.manager.TerritoryManager;
import com.biomodd.manager.TweetManager;
import com.biomodd.task.manager.TaskManager;
import com.biomodd.util.EImgType;
import com.biomodd.util.ESndType;
import com.biomodd.util.GameConfig;
import com.biomodd.util.GameConfigIO;
import com.biomodd.util.MathUtil;
import com.biomodd.util.PointF;

public class GameplayState extends BasicGameState{

	private int stateID = -1;
	
	
	private Image backgroundImage;
	private Image centerImage;
	
	private Player player1;
	private Player player2;
	private PlantsGrid plantsGrid;
	private WarningSign warningSign;


	private long timeBeforeNextWave;
	
	private float waveDirection;	
	private float rotationCenter;
	
	private boolean isWaveSet;
	
	private ParticleSystem explosionSystem;
	private ConfigurableEmitter explosionEmitter;
	
	private float angryWorld = 0f;
	
	public GameplayState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public int getID() {		
		return stateID;
	}
	
	public void setPlayerId(int id){
		if(player1 == null){
			player1 = new Player(id+"", 500f,500f,0f,0f,1);			
		}
		player1.setId(""+id);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {		
		PlantsManager.instance();
		EnemyManager.instance();
		BlocksManager.instance();
		TerritoryManager.instance();
		ParticleEffectsManager.instance();
		//We no longer initialize our task manager because it is already initialized on the login state
		GameConfigIO.loadConfig("assets/config.xml");			
		
		
		timeBeforeNextWave = GameConfig.instance().WAVE_TIME;			
		
		
		rotationCenter = 0;
		
		isWaveSet = false;

		Controller player1Controller = new Controller(Input.KEY_I, Input.KEY_K, Input.KEY_J, Input.KEY_L, Input.KEY_Z, Input.KEY_X);
		Controller player2Controller = new Controller(Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_RSHIFT, Input.KEY_RCONTROL);
		
		
		player1 = new Player("0", 250f, 250f, 0f, 0f, 0);
		player2 = new Player("1", 500f,250f,0f,0f,1);
		player1.setController(player1Controller);
		player2.setController(player2Controller);
		
		warningSign = new WarningSign("warning", 0, 0,ArtManager.instance().getImage(EImgType.WARNING));
		backgroundImage = ArtManager.instance().getImage(EImgType.BACKGROUND);
		centerImage = ArtManager.instance().getImage(EImgType.CENTER);
		PlantsManager.instance().setPlantsGrid(new PlantsGrid(40,30));
	
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr)
			throws SlickException {
		
		backgroundImage.draw(0,0, gc.getWidth(), gc.getHeight());
		gr.setColor(new Color(.5f, .5f, .5f, angryWorld));
		gr.fill(new Rectangle(0,0, gc.getWidth(), gc.getHeight()));
		TerritoryManager.instance().render(gc, sb, gr);
		
		PlantsManager.instance().render(gc, sb, gr);
		/*
		PlantsGrid plantsGrid = PlantsManager.instance().getPlantsGrid();
		int rows = plantsGrid.getRows();
		int cols = plantsGrid.getColumns();
		int[][] grid = plantsGrid.getGrid();
		gr.setColor(Color.gray);
		 for(int y = 0; y < rows; y++){
			for(int x = 0; x < cols; x++){
				if(grid[x][y] == PlantsGrid.PLANTED){
					gr.drawString("P", x*20, y*20);					
				}else if(grid[x][y] == PlantsGrid.TERRITORIZED){
					gr.drawString("T", x*20, y*20);
				}else if(grid[x][y] == PlantsGrid.DEFAULT){
					gr.drawString("X", x*20,y*20);
				}else if(grid[x][y] == PlantsGrid.DESTROYED){
					//particleSystem.render(x*20, y*20);
				}
			}
		}*/
		ParticleEffectsManager.instance().render(gc, sb, gr);
		centerImage.drawCentered(gc.getWidth()/2, gc.getHeight()/2);
		centerImage.rotate(.5f);
						
		BlocksManager.instance().render(gc, sb, gr);
		
		EnemyManager.instance().render(gc, sb, gr);
		
		player1.render(gc, sb, gr);
		player2.render(gc, sb, gr);
		//g.fill(player.getBounds());
		if(warningSign.visible()){			
			warningSign.render(gc, sb, gr);
		}		
		
		gr.setColor(Color.white);
		/*
		g.drawString("Time Since Skill Move: " + timeSinceSkillMove, 10, 60);
		g.drawString("Time Since Type Change: " + timeSinceTypeChange, 10, 100);
		g.drawString("Time Before Next Wave: " + timeBeforeNextWave, 10, 140);*/
		
		//gr.drawString("Can Do Skill: " + ((player1.getTimeSinceSkillMove() >= GameConfig.instance().SKILL_MOVE) ? "Yes":"No"), 10, 60);
		//gr.drawString("Can Change Type: " + ((player1.getTimeSinceTypeChange() >= GameConfig.instance().TYPE_CHANGE) ? "Yes":"No"), 10, 80);
		TweetManager.instance().render(gc, sb, gr);
		gr.drawString("Time Before Next Wave: " + (timeBeforeNextWave/1000), 10, 20);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)
			throws SlickException {
		
		timeBeforeNextWave -= delta;
		
		if(TweetManager.instance().isMad()){
			if(angryWorld < .7){
				angryWorld += .05f;
				if(angryWorld > .7f)
					angryWorld = .7f;
			}
		}else{
			if(angryWorld > 0f){
				angryWorld -= .05f;
				if(angryWorld < 0f){
					angryWorld = 0f;
				}
			}
		}
		//particleSystem.update(delta);			
		
		if(isWaveNear()){
			setNextWave(gc);
		}
		
		if(isTimeForNextWave()){
			timeBeforeNextWave = GameConfig.instance().WAVE_TIME;
			warningSign.setVisible(false);
			isWaveSet = false;
			sendWave(gc);
		}
		
		Input in = gc.getInput();
		
		
		if(in.isKeyPressed(Input.KEY_ESCAPE)){
			System.exit(0);
		}
		
		player1.update(gc, sb, delta);
		player2.update(gc, sb, delta);
		TaskManager.getInstance().update();
		TweetManager.instance().update(gc, sb, delta);
		PlantsManager.instance().update(gc, sb, delta);
		EnemyManager.instance().update(gc, sb, delta);
		BlocksManager.instance().update(gc, sb, delta);
		TerritoryManager.instance().update(gc, sb, delta);
		ParticleEffectsManager.instance().update(gc, sb, delta);
		
		PlantsManager.instance().cleanUpEntities();
		EnemyManager.instance().cleanUpEntities();
		BlocksManager.instance().cleanUpEntities();
		TerritoryManager.instance().cleanUpEntities();
		ParticleEffectsManager.instance().cleanUp();
	}
	
	
	
	private void setNextWave(GameContainer gc){
		if(!isWaveSet){
			waveDirection = (float)(Math.random() * 360);
			PointF polarCoord = new PointF(300f, waveDirection);
			PointF cartesianPoint = MathUtil.polarToCartesian(polarCoord);
			Vector2f center = new Vector2f(gc.getWidth()/2, gc.getHeight()/2);
			warningSign.setPosition(center.x + cartesianPoint.x, center.y -  cartesianPoint.y);
			warningSign.setVisible(true);
			Sound effect = ArtManager.instance().getSound(ESndType.WARNING);
			effect.play();
			warningSign.setRotation((int)(waveDirection));
			isWaveSet = true;
		}		
	}
	
	private void sendWave(GameContainer gc){
		Vector2f center = new Vector2f(gc.getWidth()/2, gc.getHeight()/2);
		Image enemyImage = ArtManager.instance().getImage(EImgType.ENEMY);
		//Vector2f center = new Vector2f(gc.getWidth(),gc.getHeight());
		PointF polarCoord = null;
		PointF cartesianPoint = null;
		for(int i = 0; i < 5; i++){
			String id = System.currentTimeMillis() + "" + i;
			polarCoord = new PointF(900f + (float)(Math.random()*100), waveDirection + (i*5));
			cartesianPoint = MathUtil.polarToCartesian(polarCoord);
			EnemyManager.instance().addEntity(id, new Enemy(id, center.x + cartesianPoint.x, center.y - cartesianPoint.y, 0,0, enemyImage));
		}
		
		for(int i = 0; i < 5; i++){
			String id = System.currentTimeMillis() + "" + i + "x";
			polarCoord = new PointF(900f + (float)(Math.random()*100), waveDirection - (i*5));
			cartesianPoint = MathUtil.polarToCartesian(polarCoord);
			EnemyManager.instance().addEntity(id, new Enemy(id, center.x + cartesianPoint.x, center.y - cartesianPoint.y, 0,0, enemyImage));
		}
		if(angryWorld > 0f){
			for(int i = 0; i < 5; i++){
				String id = System.currentTimeMillis() + "" + i + "x";
				polarCoord = new PointF(900f + (float)(Math.random()*100), waveDirection - (i*5));
				cartesianPoint = MathUtil.polarToCartesian(polarCoord);
				EnemyManager.instance().addEntity(id, new Enemy(id, center.x + cartesianPoint.x, center.y - cartesianPoint.y, 0,0, enemyImage));
			}
		}		
	}
	
	private boolean isWaveNear(){
		return timeBeforeNextWave <= GameConfig.instance().NEAR_TIME;
	}
	
	private boolean isTimeForNextWave(){
		return timeBeforeNextWave <= 0;
	}
				
	
	
}
