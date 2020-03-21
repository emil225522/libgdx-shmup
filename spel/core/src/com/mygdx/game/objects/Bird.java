package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bird extends Enemy {

	public Bird(Vector2 position, Texture texture, int health) {
		super(position, texture, health);
		maxSpeed = -3;
		velocity.x = maxSpeed;
	}

	public void update() {
		super.update();

	}
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

}
