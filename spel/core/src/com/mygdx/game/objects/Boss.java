package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Boss extends Enemy{

	public Boss(Vector2 position, Texture texture, int life) {
		super(position, texture, life);
		velocity.y = 2;
		// TODO Auto-generated constructor stub
	}

}
