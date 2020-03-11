package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class vapeMormon extends Enemy {
	Vector2 dir;
	double spincounter = 0;

	public vapeMormon(Vector2 position, Texture texture, int life) {
		super(position, texture, life);
		// TODO Auto-generated constructor stub
		dir = new Vector2(0,0);
	}
	
	public void update() {
		position.add(-1, 0);
		
		spincounter+=0.1;
		
		dir = new Vector2((float)Math.cos(spincounter)*2,(float)Math.sin(spincounter)*3);
		
		position.add(dir);
	}
	//Borde snurra i draw

}
