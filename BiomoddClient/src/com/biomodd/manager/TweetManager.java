package com.biomodd.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.biomodd.util.GameConfig;
import com.biomodd.util.TweetMessageFactory;

public class TweetManager extends EntityManager{
	private static final Logger logger = Logger.getLogger(TweetManager.class.getName());
	private static TweetManager instance;	
	private static Twitter twitter;
	
	private long timeSinceLastTweet = 0;
	private List<Status> timeLine = null;
	private List<Status> mentions; 
	
	private String statusMessage = "";
	
	private boolean isAngry = false;
	
	private TweetManager(){
		twitter = new Twitter("maria_makiling", "biomoddlba2");
		if(!GameConfig.instance().HTTP_PROXY.equals("none")){
			twitter.setHttpProxy(GameConfig.instance().HTTP_PROXY, GameConfig.instance().HTTP_PROXY_PORT);			
		}
		try{			
			twitter.updateStatus(TweetMessageFactory.generateWelcomeScreen(GameConfig.instance().PLAYER_NAME1 , GameConfig.instance().PLAYER_NAME2));
			logger.severe("Starting twitter");
			mentions = twitter.getMentions();
			getAppeasement();
			
			timeLine = twitter.getUserTimeline();
		}catch(TwitterException te){
			Log.error(te);
			logger.severe(te.getMessage());
		}
		
	}		
	private void getAppeasement(){
		if(mentions.size() > 0){
			Status msg = mentions.get(0);
			if(messageIsFromToday(msg.getCreatedAt())){				
				if(isPositiveMessage(msg.getText())){
					statusMessage = "I feel glad"; 
				}else{
					isAngry = true;
				}
			}
		}
	}
	private boolean isPositiveMessage(String message){
		if(message.contains("Thanks") || message.contains("Salamat") || message.contains("Mabuhay") || message.contains("!APRUB!")){
			return true;
		}else if(message.contains("!Angry!") || message.contains("!HATE!")){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean isMad(){
		return isAngry;
	}
	
	private boolean messageIsFromToday(Date date){
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		Calendar when = Calendar.getInstance();
		when.setTime(date);
		return when.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH);			//
	}
	
	public static TweetManager instance(){
		if(instance == null){
			instance = new TweetManager();
		}
		return instance;
	}
	
	public void updateStatus(String message){
		try {
			twitter.updateStatus(message);
		} catch (TwitterException e) {
			Log.error(e);
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		//gr.drawString(statusMessage, 600,500);		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		timeSinceLastTweet += delta;
		if(timeSinceLastTweet >= (60000)*2){
			timeSinceLastTweet = 0;
			getAppeasement();
			
			try{
				timeLine = twitter.getUserTimeline();
				statusMessage = timeLine.get(0).getText();
			}catch(TwitterException te){
				Log.error(te);
			}
		}				
	}

}
