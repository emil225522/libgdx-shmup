package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Particle {
	Texture texture;
	Vector2 position;
	Vector2 velocity;
	boolean isDead;
	int speed;
	int aliveTimer;

	public Particle(Vector2 position, Texture texture, Vector2 velocity) {
		this.position = position;
		this.texture = texture;
		this.velocity = velocity;

	}

	public void update() {
		position.x += velocity.x;
		position.y += velocity.y;
		aliveTimer++;
		if (aliveTimer > 120)
			isDead = true;
		
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, position.x, position.y);
	}
}
