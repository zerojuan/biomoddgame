package com.biomodd.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.util.Log;

import com.biomodd.client.Client;
import com.biomodd.client.handler.ClientHandler;
import com.biomodd.exception.MissingComponentException;
import com.biomodd.task.manager.TaskManager;
import com.biomodd.unit.IComponent;

public class BiomoddGame extends StateBasedGame implements IComponent{
	public static final int LOGINSTATE = 0;
	public static final int GAMEPLAYSTATE = 1;
	
	private GameplayState gamePlayState;
	private LoginState loginState;
	
	private Client client;
	/**
	 * The flag indicates the activeness of this <code>Component</code>.
	 */
	private boolean active;
	
	public BiomoddGame(){
		super("Biomodd Game");
		
		TaskManager.create(this);
		
		loginState = new LoginState(LOGINSTATE);
		gamePlayState = new GameplayState(GAMEPLAYSTATE);
		
		this.addState(loginState);
		this.addState(gamePlayState);			
		
		this.enterState(LOGINSTATE, new EmptyTransition(), new FadeInTransition(Color.black, 1000));
		
		Log.setVerbose(false);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(LOGINSTATE).init(container, this);
		this.getState(GAMEPLAYSTATE).init(container, this);	
	}	
		
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * This is the main method
	 */
	public static void main(String args[]){
		try{
			 // Construct components.
	        ClientHandler handler = new ClientHandler();
	        Client client = new Client();
	        BiomoddGame game = new BiomoddGame();
			
	        // Establish component connections.
	        handler.connect(game);
	        client.connect(handler);
	        game.connect(client);
	        
	        // Initialize components.
	        try {
	            handler.activate();
	            client.activate();
	            game.activate();
	        } catch (MissingComponentException e) {
	            e.printStackTrace();
	        }
	        
			AppGameContainer app = new AppGameContainer(game);
			app.setDisplayMode(800, 600, false);
			app.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void activate() throws MissingComponentException {
		if(this.validate()) {
			this.active = true;
			this.initialize();			
		}
	}

	@Override
	public void connect(IComponent component) {
		if(component instanceof Client) {
			this.client = (Client)component;
		}
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validate() throws MissingComponentException {
		if(this.client == null) {
			throw new MissingComponentException(Client.class.toString());
		}
		return true;
	}

}
