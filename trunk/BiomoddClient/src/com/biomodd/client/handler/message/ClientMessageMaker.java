package com.biomodd.client.handler.message;

import java.nio.ByteBuffer;

public class ClientMessageMaker {

	public static ByteBuffer createMovePkt(int id, float x, float y){
		byte[] bytes = new byte[1 + 4 + 8];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.put((byte)EOPCODE.MOVEME.ordinal());
		buffer.putInt(id);
		buffer.putFloat(x);
		buffer.putFloat(y);
		
		buffer.flip();
		return buffer;
	}
	
	public static ByteBuffer createChangeTypePkt(int id, int type){
		byte[] bytes = new byte[1 + 4 + 4];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.put((byte)EOPCODE.CHANGEMYTYPE.ordinal());
		buffer.putInt(id);
		buffer.putInt(type);		
		
		buffer.flip();
		return buffer;
	}
	
	public static ByteBuffer createAddBlockPkt(float x, float y){
		byte[] bytes = new byte[1 + 8 + 4];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.put((byte)EOPCODE.BUILD.ordinal());
		buffer.putFloat(x);		
		buffer.putFloat(y);
		buffer.putInt(100);
		
		buffer.flip();
		return buffer;
	}
	
	public static ByteBuffer createTerritoryPkt(float x, float y, int radius){
		byte[] bytes = new byte[1 + 8 + 4];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.put((byte)EOPCODE.TERRITORIZE.ordinal());
		buffer.putFloat(x);		
		buffer.putFloat(y);
		buffer.putInt(radius);
		
		buffer.flip();
		return buffer;
	}
	
	public static ByteBuffer createRemoveEnemyPkt(int id){
		byte[] bytes = new byte[1 + 4];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.put((byte)EOPCODE.REMOVE_ENEMY.ordinal());
		buffer.putInt(id);		
		
		buffer.flip();
		return buffer;
	}
	
	public static ByteBuffer createDamageBlockPkt(int id, int damage){
		byte[] bytes = new byte[1 + 4 + 4];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.put((byte)EOPCODE.REMOVE_ENEMY.ordinal());
		buffer.putInt(id);		
		buffer.putInt(damage);
		
		buffer.flip();
		return buffer;
	}
	
	public static ByteBuffer createRemoveBlockPkt(int id){
		byte[] bytes = new byte[1 + 4];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.put((byte)EOPCODE.REMOVE_BLOCK.ordinal());
		buffer.putInt(id);		
		
		buffer.flip();
		return buffer;
	}
	
	public static ByteBuffer createRemoveTerritoryPkt(int id){
		byte[] bytes = new byte[1 + 4];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.put((byte)EOPCODE.REMOVE_TERRITORY.ordinal());
		buffer.putInt(id);		
		
		buffer.flip();
		return buffer;
	}
}
