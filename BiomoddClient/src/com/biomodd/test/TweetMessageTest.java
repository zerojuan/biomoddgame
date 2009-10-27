package com.biomodd.test;

import org.junit.Test;

import com.biomodd.util.TweetMessageFactory;

public class TweetMessageTest {
	@Test
	public void statusMessageTest(){
		for(int i = 0; i < 20; i++){
			System.out.println(TweetMessageFactory.generateUpdateStatus(601));
		}
	}
	
	@Test
	public void welcomeMessageTest(){
		String player1 = null;
		String player2 = null;
		TweetMessageFactory.generateWelcomeScreen(player1, player2);
		player1 = "something";
		TweetMessageFactory.generateWelcomeScreen(player1, player2);
		player2 = "something else";
		player1 = null;
		TweetMessageFactory.generateWelcomeScreen(player1, player2);
		player1 = "player1";
		player2 = "player2";
		TweetMessageFactory.generateWelcomeScreen(player1, player2);
	}
}
