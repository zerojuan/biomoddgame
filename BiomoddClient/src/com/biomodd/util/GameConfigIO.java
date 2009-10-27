package com.biomodd.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class GameConfigIO {

	public static void loadConfig(String ref){		
		loadConfig(ResourceLoader.getResourceAsStream(ref));
	}
	
	private static void loadConfig(InputStream ref){
		try{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(ref);
			
			
			for(Node node = document.getFirstChild().getFirstChild();
				node != null; node = node.getNextSibling()){
				Log.info("Loging");
				setConfigValue(node);
			}
			
		}catch(SAXException se){
			Log.error(se);
		}
		catch(ParserConfigurationException pce){
			Log.error(pce);
		}
		catch(IOException ioe){
			Log.error(ioe);
			//throw e;
		}
	}
	
	private static void setConfigValue(Node n){
		Log.info("Parsing Node: " + n.getNodeName());
		if(n.getNodeName().equals("waveTime")){			
			GameConfig.instance().WAVE_TIME = Long.parseLong(n.getTextContent().trim());
		}else if(n.getNodeName().equals("growthTime")){
			GameConfig.instance().GROWTH_TIME = Long.parseLong(n.getTextContent().trim());
		}else if(n.getNodeName().equals("warnTime")){
			GameConfig.instance().NEAR_TIME = Long.parseLong(n.getTextContent().trim());
		}else if(n.getNodeName().equals("skillCooldown")){
			GameConfig.instance().SKILL_MOVE = Long.parseLong(n.getTextContent().trim());
		}else if(n.getNodeName().equals("typeCooldown")){
			GameConfig.instance().TYPE_CHANGE = Long.parseLong(n.getTextContent().trim());
		}else if(n.getNodeName().equals("avgEnemySpeed")){
			GameConfig.instance().ENEMY_SPEED = Integer.parseInt(n.getTextContent().trim());
		}else if(n.getNodeName().equals("enemyDamage")){
			GameConfig.instance().ENEMY_DAMAGE = Integer.parseInt(n.getTextContent().trim());
		}else if(n.getNodeName().equals("blockHp")){
			GameConfig.instance().BLOCK_LIFE = Integer.parseInt(n.getTextContent().trim());
		}else if(n.getNodeName().equals("territoryLife")){
			GameConfig.instance().TERRITORY_LIFE = Integer.parseInt(n.getTextContent().trim());
		}else if(n.getNodeName().equals("httpProxy")){
			GameConfig.instance().HTTP_PROXY = (n.getTextContent().trim());
		}else if(n.getNodeName().equals("httpPort")){
			if(!n.getTextContent().trim().equals("none"))
				GameConfig.instance().HTTP_PROXY_PORT= Integer.parseInt(n.getTextContent().trim());
		}
	}
}
