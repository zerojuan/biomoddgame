package com.biomodd.input;

/**
 * This is a holder class that contains the input keys of a player
 * @author Julius
 *
 */
public class Controller {

	public int UP_KEY;
	public int DOWN_KEY;
	public int LEFT_KEY;
	public int RIGHT_KEY;
	
	public int CHANGE_KEY;
	public int DO_SKILL_KEY;
	
	public Controller(int up, int down, int left, int right, int change, int doSkill){
		this.UP_KEY = up;
		this.DOWN_KEY = down;
		this.LEFT_KEY = left;
		this.RIGHT_KEY = right;
		
		this.CHANGE_KEY = change;
		this.DO_SKILL_KEY = doSkill;
	}
	
}
