package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bird extends Enemy {

	public Bird(Vector2 position, Texture texture, int life) {
		super(position, texture, life);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		super.update();
		System.out.println(position.x +" "+ position.y);
		position.x -= 2;
	}
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

}
