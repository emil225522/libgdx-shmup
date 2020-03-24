package com.mygdx.game.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TextureManager;

public class Explosion {
	Vector2 position;
	Vector2 velocity;
	public boolean isDead;
	ArrayList<Particle> particles = new ArrayList<Particle>();
	int speed = 2;
	Random random = new Random();
	int aliveTimer;

	public Explosion(Vector2 position, Vector2 velocity) {
		this.position = position;
		this.velocity = velocity;
		for (double i = 0; i < Math.PI * 2; i += Math.PI / 24) {
			Vector2 direction = new Vector2((float)Math.cos(i)*speed*(random.nextInt(5)+1),(float)Math.sin(i)*speed*(random.nextInt(5)+1));
			particles.add(new Particle(new Vector2(position), TextureManager.PARTICLE_TEXTURE,
					direction));
		}

	}
	// particles.add(new Particle(position,TextureManager.PARTICLE_TEXTURE,new
	// Vector2(2,2)));

	public void update() {
		aliveTimer++;
		if (aliveTimer > 120)
			isDead = true;

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}

	}

	public void draw(SpriteBatch spriteBatch) {
		for (int i = 0; i < particles.size(); i++) {
			if(!particles.get(i).isDead) {
				particles.get(i).draw(spriteBatch);				
			}
		}
	}
}
