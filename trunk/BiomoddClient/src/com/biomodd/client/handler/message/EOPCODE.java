package com.biomodd.client.handler.message;

public enum EOPCODE {
	NEW_GAME,
	
	/**
     * Client to server opcodes.
     */
    MOVEME, BUILD, TERRITORIZE, FIX, CHANGEMYTYPE,
    
    /**
     * Server to client opcodes.
     */
    ADD_PLAYER, REMOVE_PLAYER, PLAYER_MOVED, 
    PLAYER_BUILD, 
    PLAYER_TERRITORIZED,
    PLANT_GROW,
    ENEMY_CHARGE,
    
    /**
     * Initializer opcodes
     */
    INIT_TERRITORIZE,
    INIT_ENEMIES,
    INIT_BLOCKS,
    INIT_PLAYERS,
    
    /**
     * Common opcodes.
     */
    READY, CHAT, REMOVE_BLOCK, REMOVE_PLANT, 
    REMOVE_ENEMY, REMOVE_TERRITORY
}
