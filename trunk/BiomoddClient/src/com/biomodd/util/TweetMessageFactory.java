package com.biomodd.util;

public class TweetMessageFactory {

		private static String[] lowLevelMessage = {"Great, they have expanded more than a half now.",
													"We have grown to more than a half now",
													"They are starting out great",
													"More than half, if they keep this up..."};
		private static String[] midLevelMessage = {"Almost all of the world has grown back thanks to them.",
												    "I feel glad about the way they are helping each other.",
												    "Together the world turns green",
												    "How wonderful.. the rate that they have worked together"};
		private static String[] highLevelMessage = {"Thank you for helping me. I am forever grateful.",
													"The game isn't really just about nature, it's about cooperation",
													"In a way, they have shared more than a computer game",
													"You have created the world that I can hardly envision"
													};
		private static String[] unknownPeople = {"Someone has started the game, and they wish to remain nameless.",
												 "Once again, someone is anonymously playing in my world",
												 "I have awoken. And hmm. A nameless couple are we?",
												 "Maligayang bati.. kahit na wala kayong mga pangalan.."};		
		
		public static String generateUpdateStatus(int level){
			int choice = (int)Math.floor((Math.random() * 4));
			if(level > 600){
				return lowLevelMessage[choice];
			}else if(level > 750){
				return midLevelMessage[choice];
			}else if(level > 1000){
				return highLevelMessage[choice];
			}
			return null;
		}
		
		public static String generateWelcomeScreen(String player1, String player2){
			int choice = (int)Math.floor((Math.random() * 4));
			if(player1 == null && player2 == null){
				return unknownPeople[choice];
			}else{
				if(player1 == null){
					player1 = "Unknown player";
				}else if(player2 == null){
					player2 = "Unknown player";					
				}
				String[] somePeople = {"@"+player1+ " and @" + player2 + " are helping me grow right now.",
										"@"+player1+" and @" + player2 + " started playing the game",
										"Welcome, @"+player1+" and @"+player2+" this is my world..",
										"Who dared to help me in this world? @"+player1+" and @"+player2};
				return somePeople[choice];
			}
			
		}
}
