package com.biomodd.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Properties;

import org.newdawn.slick.util.Log;

import com.biomodd.client.handler.ClientHandler;
import com.biomodd.exception.MissingComponentException;
import com.biomodd.unit.Component;
import com.biomodd.unit.IComponent;
import com.sun.sgs.client.simple.SimpleClient;

public class Client extends Component{
	/**
	 * The <code>ClientHandler</code> <code>Component</code>.
	 */
	private ClientHandler handler;
	/**
	 * The <code>SimpleClient</code> instance.
	 */
	private SimpleClient connection;
	/**
	 * Constructor of <code>Client</code>.
	 */
	public Client() {}
	@Override
	public boolean validate() throws MissingComponentException {
		if(this.handler == null) {
			throw new MissingComponentException(ClientHandler.class.toString());
		}
		return true;
	}

	@Override
	public void initialize() {
		this.connection = new SimpleClient(this.handler.getListener());
	}

	@Override
	public void connect(IComponent component) {
		if(component instanceof ClientHandler) {
			this.handler = (ClientHandler)component;
		}
	}
	public void login(Properties properties) {
		try {
			Log.debug("Logging in..");
			Log.debug(properties.toString());
			this.connection.login(properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        
    public void logout() {
            this.connection.logout(false);
        }
	
	public void send(ByteBuffer message) {
		try {
			this.connection.send(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieve the <code>ClientHandler</code> instance.
	 * @return The <code>ClientHandler</code> instance.
	 */
	public ClientHandler getHandler() {
		return this.handler;
	}
}
