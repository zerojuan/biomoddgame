package com.biomodd.client.handler;

import java.net.PasswordAuthentication;

import com.biomodd.client.handler.message.MessageListener;
import com.biomodd.client.protocol.MessageProcessor;
import com.biomodd.exception.MissingComponentException;
import com.biomodd.game.BiomoddGame;
import com.biomodd.unit.Component;
import com.biomodd.unit.IComponent;

public class ClientHandler extends Component{
	/**
	 * The single <code>MessageListener</code> instance.
	 */
	private final MessageListener listener;
	/**
	 * The <code>MessageProcessor</code> instance
	 */
	private final MessageProcessor processor;
	/**
	 * The <code>PasswordAuthentication</code> instance.
	 */
	private PasswordAuthentication authentication;
	/**
	 * The <code>Game</code> instance.
	 */
	private BiomoddGame game;
	/**
	 * Constructor of <code>ClientHandler</code>.
	 */
	public ClientHandler() {
		this.listener = new MessageListener(this);
		this.processor = new MessageProcessor(this);
	}
	/**
	 * Create the authentication object for authentication purpose.
	 * @param username The <code>String</code> user name.
	 * @param password The <code>String</code> password.
	 */
	public void authenticate(String username, String password) {
		this.authentication = new PasswordAuthentication(username ,password.toCharArray());
	}
	/**
	 * Retrieve the <code>Game</code> <code>Component</code>.
	 * @return The <code>Game</code> instance.
	 */
	public BiomoddGame getGame() {
		return this.game;
	}
	
	/**
	 * Retrieve the <code>MessageListener</code> sub-component.
	 * @return The <code>MessageListener</code> instance.
	 */
	public MessageListener getListener() {
		return this.listener;
	}
	
	/**
	 * Retrieve the <code>MessageProcessor</code> sub-component.
	 * @return The <code>MessageProcessor</code> instance.
	 */
	public MessageProcessor getProcessor() {
		return this.processor;
	}
	
	/**
	 * Retrieve the <code>PasswordAuthentication</code> instance.
	 * @return The <code>PasswordAuthentication</code> instance.
	 */
	public PasswordAuthentication getAuthentication() {
		return this.authentication;
	}
	
	@Override
	public boolean validate() throws MissingComponentException {
		if(this.game == null) {
			throw new MissingComponentException(BiomoddGame.class.toString());
		}
		return true;
	}

	@Override
	public void initialize() {		
	}

	@Override
	public void connect(IComponent component) {
		if(component instanceof BiomoddGame) {
			this.game = (BiomoddGame)component;
		}
	}
}
