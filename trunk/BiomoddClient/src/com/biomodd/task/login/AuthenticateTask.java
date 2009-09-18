package com.biomodd.task.login;

import java.util.Properties;

import com.biomodd.game.BiomoddGame;
import com.biomodd.game.LoginState;
import com.biomodd.task.ETask;
import com.biomodd.task.RealTimeTask;
import com.biomodd.util.EInputState;

public class AuthenticateTask extends RealTimeTask {
	  /**
     * The <code>String</code> user name to login with.
     */
    private final String username;
    /**
     * The <code>String</code> password to login with.
     */
    private final String password;
    /**
     * The host to connect to
     */
    private final String host;
    /**
     * The port to connect to
     */
    private final String port;
    
    /**
     * Constructor of <code>AuthenticateTask</code>.
     * @param game The <code>Game</code> instance.
     * @param username The <code>String</code> user name to login with.
     * @param password The <code>String</code> password to login with.
     * @param host The <cod>String</code> host to connect with
     * @param port The <cod>String</code> port to connect with
     */
    public AuthenticateTask(BiomoddGame game,
                            String username,
                            String password,
                            String host,
                            String port) {
        super(ETask.Authenticate, game);
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }
    
	@Override
	public void execute() {
		LoginState loginState = (LoginState)(game.getState(BiomoddGame.LOGINSTATE));
		loginState.setStatus(EInputState.INACTIVE);
		this.game.getClient().getHandler().authenticate(this.username, this.password);
		 Properties properties = new Properties();
	     properties.setProperty("host", host);
	     properties.setProperty("port", port);
	     this.game.getClient().login(properties);
	}

}
