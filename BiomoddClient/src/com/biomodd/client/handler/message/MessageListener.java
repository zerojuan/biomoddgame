package com.biomodd.client.handler.message;

import java.net.PasswordAuthentication;
import java.nio.ByteBuffer;

import org.newdawn.slick.util.Log;

import com.biomodd.client.handler.ClientHandler;
import com.biomodd.task.ETask;
import com.biomodd.task.manager.TaskManager;
import com.sun.sgs.client.ClientChannel;
import com.sun.sgs.client.ClientChannelListener;
import com.sun.sgs.client.simple.SimpleClientListener;

public class MessageListener implements SimpleClientListener, ClientChannelListener{
	/**
	 * The <code>ClientHandler</code> this listener is attached to.
	 */
	private final ClientHandler handler;
	/**
	 * Constructor of <code>MessageListener</code>.
	 * @param handler The <code>ClientHandler</code> this listener is attached to.
	 */
	public MessageListener(ClientHandler handler) {
		this.handler = handler;
	}
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return this.handler.getAuthentication();
	}

	@Override
	public void loggedIn() {
        TaskManager.getInstance().createTask(ETask.LoginSuccess);
    }

	@Override
	public void loginFailed(String reason) {
		TaskManager.getInstance().createTask(ETask.ResetLogin, reason);
	}

	@Override
	public void disconnected(boolean graceful, String reason) {
		Log.info("Player has been disconnected");
		TaskManager.getInstance().createTask(ETask.ResetLogin,reason);
            //this.handler.getGame().finish();
			//TODO: set an end game for players who where disconnected
	}

	@Override
	public ClientChannelListener joinedChannel(ClientChannel channel) {
		return this;
	}

	@Override
	public void receivedMessage(ByteBuffer message) {
		MessageHandler.getInstance().parseClientPacket(message, this.handler.getProcessor());
	}

	@Override
	public void reconnected() {
		// TODO Auto-generated method stub
	}

	@Override
	public void reconnecting() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftChannel(ClientChannel channel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivedMessage(ClientChannel channel, ByteBuffer message) {
		//SingletonRegistry.getMessageHandler().parseClientPacket(message, this.handler.getProcessor());
		MessageHandler.getInstance().parseClientPacket(message, this.handler.getProcessor());
	}
}
