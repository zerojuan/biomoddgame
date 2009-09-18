package com.biomodd.client.data;

/**
 * Wrapper class for the positions of objects
 * @author Julius
 *
 */
public class PositionsWrapper {

	private float[][] positions;
	
	public PositionsWrapper(float[][] positions){
		this.positions = positions; 
	}
	
	public float[][] getPositions(){
		return positions;
	}
}
