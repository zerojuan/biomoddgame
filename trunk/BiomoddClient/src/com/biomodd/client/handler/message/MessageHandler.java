package com.biomodd.client.handler.message;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.biomodd.client.protocol.IClientProcessor;

public class MessageHandler {
	private static final Logger logger = Logger.getLogger(MessageHandler.class.getName());
	
	private static MessageHandler instance;
	
	public MessageHandler(){	
	}	
	public static MessageHandler getInstance(){
		if(instance == null){
			instance = new MessageHandler();			
		}
		return instance;
	}
	
	public void parseClientPacket(ByteBuffer packet, IClientProcessor unit){
		EOPCODE opCode = getOpCode(packet);
		parseClientPacket(opCode, packet, unit);
	}
	
	private void parseClientPacket(EOPCODE opCode, ByteBuffer packet, IClientProcessor unit){
		logger.log(Level.INFO, "Decoding server message {0}..", new Object[]{opCode});
		int length;    	
    	int[] ids;
    	long[] elapsedTime;
    	switch(opCode){
    		case NEW_GAME:
    			int myID = packet.getInt();
    			logger.log(Level.INFO, "Processing {0} packet : {1}", new Object[]{opCode, myID});
    			unit.newGame(myID);
    			break;
	    	case PLAYER_MOVED:  
				int id = packet.getInt();
				float x = packet.getFloat();
				float y = packet.getFloat();
				logger.log(Level.INFO, "Processing {0} packet : {1}, {2}", new Object[]{opCode, x, y});
				unit.movePlayer(id, x, y,0,0);
				break;
			case ADD_PLAYER:
				int newId = packet.getInt();
				float initX = packet.getFloat();
				float initY = packet.getFloat();
				int initType = packet.getInt();
				logger.log(Level.INFO, "Processing {0} packet : id:{1}, {2}, {3}", new Object[]{opCode, newId, initX, initY});
				unit.addPlayer(newId, initX, initY, 0,0, initType);
				break;
			case REMOVE_PLAYER:
				int playerId = packet.getInt();
				logger.log(Level.INFO, "Processing {0} packet : {1}", new Object[]{opCode, playerId});
				unit.removePlayer(playerId);
				break;
			case PLANT_GROW:
				length = packet.getInt();
				int[][] newPlants = new int[length][2]; 
				for(int i = 0; i < newPlants.length; i++){
					newPlants[i][0] = packet.getInt();
					newPlants[i][1] = packet.getInt();
				}    			
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.growPlant(newPlants);
				break;
			case INIT_TERRITORIZE:
				length = packet.getInt();
				float[][] territories = new float[length][2];
				ids = new int[length];
				elapsedTime = new long[length];
				int radius[] = new int[length];
				for(int i = 0; i < territories.length; i++){
					ids[i] = packet.getInt();
					territories[i][0] = packet.getFloat();
					territories[i][1] = packet.getFloat();
					elapsedTime[i] = packet.getLong();
					radius[i] = packet.getInt();    				
				}
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.initTerritories(territories, ids, elapsedTime);
				break;
			case INIT_BLOCKS:
				length = packet.getInt();
				float[][] blocks = new float[length][2];
				ids = new int[length];
				elapsedTime = new long[length];
				int[] health = new int[length];
				for(int i = 0; i < blocks.length; i++){
					ids[i] = packet.getInt();
					blocks[i][0] = packet.getFloat();
					blocks[i][0] = packet.getFloat();    
					health[i] = packet.getInt();
				}
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.initBlocks(blocks, ids, health);
				break;
			case INIT_ENEMIES:
				length = packet.getInt();
				float[][] enemies = new float[length][2];
				ids = new int[length];
				elapsedTime = new long[length];
				for(int i = 0; i < enemies.length; i++){
					ids[i] = packet.getInt();
					enemies[i][0] = packet.getFloat();
					enemies[i][0] = packet.getFloat();
					elapsedTime[i] = packet.getLong();
				}
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.initEnemies(enemies, ids, elapsedTime);
				break;
			case PLAYER_BUILD:
				int bId = packet.getInt();
				float bX = packet.getFloat();
				float bY = packet.getFloat();
				
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.addBlock(bId, bX, bY);
				break;
			case PLAYER_TERRITORIZED:
				int tId = packet.getInt();
				float tX = packet.getFloat();
				float tY = packet.getFloat();
				long tTime = packet.getLong();
				int tRadius = packet.getInt();
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.addTerritory(tId, tX, tY, tTime, tRadius);
				break;
			case CHANGEMYTYPE:
				int cId = packet.getInt();
				int cType = packet.getInt();
				unit.changeType(cId, cType);
				break;
			case ENEMY_CHARGE:
				length = packet.getInt();
				float[][] newEnemies = new float[length][2];
				ids = new int[length];
				elapsedTime = new long[length];
				for(int i = 0; i < newEnemies.length; i++){
					ids[i] = packet.getInt();
					newEnemies[i][0] = packet.getFloat();
					newEnemies[i][1] = packet.getFloat();
					elapsedTime[i] = packet.getLong();
				}
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.enemyCharge(newEnemies, ids, elapsedTime);
				break; 
			case REMOVE_BLOCK:
				int bR= packet.getInt();
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.removeBlock(bR);
				break;
			case REMOVE_TERRITORY:
				int tR = packet.getInt();
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.removeTerritory(tR);
				break;
			case REMOVE_ENEMY:
				int eR = packet.getInt();
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.removeEnemy(eR);
				break;
			case INIT_PLAYERS:
				length = packet.getInt();
				float[][] players = new float[length][2];
				int[] playerTypes = new int[length];
				ids = new int[length];				
				for(int i = 0; i < players.length; i++){
					ids[i] = packet.getInt();
					players[i][0] = packet.getFloat();
					players[i][0] = packet.getFloat();
					playerTypes[i] = packet.getInt();
				}
				logger.log(Level.INFO, "Processing{0} packet..", new Object[]{opCode});
				unit.initPlayers(players, ids, playerTypes);
			default:
				logger.log(Level.FINE, "Unable to decipher message.. ");
	    	}
	}
	
	
	
	private EOPCODE getOpCode(ByteBuffer packet) 
    {
        byte opbyte = packet.get();
        if ((opbyte < 0) || (opbyte > EOPCODE.values().length - 1)) {
            logger.severe("Unknown op value: " + opbyte);
            return null;
        }
        EOPCODE code = EOPCODE.values()[opbyte];
        
        return code;
    }
}
