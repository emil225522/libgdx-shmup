package com.mygdx.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bird extends Enemy {

	public Bird(Vector2 position, Texture texture, int health,ArrayList<Pickup> pickups) {
		super(position, texture, health, pickups);
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
