package com.biomodd.gui;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;

public class ClickableTextField extends MouseOverArea{
	
	private TextField textField;
	private Font font;
	private Rectangle rect;
	
	private int txtWidth = 100;
	private int txtHeight = 20;
	
	public ClickableTextField(GameContainer gc, Image image, Font font, int x, int y){
		super(gc, image, x, y);
		rect = new Rectangle(x, y, txtWidth, txtHeight);
	}

	
}
