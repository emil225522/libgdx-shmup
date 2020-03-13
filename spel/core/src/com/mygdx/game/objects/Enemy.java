package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy {
	Texture texture;
	Vector2 position;
	Rectangle hitBox = new Rectangle();
	int life = 0;
	public boolean isDead = false;

	public Enemy(Vector2 position, Texture texture, int life) {
		this.texture = texture;
		this.position = position;
		this.life = life;
		this.hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
	}

	public void update() {
		this.hitBox = new Rectangle(position.x, position.y, 50, 50);
		if (life <= 0) {
			isDead = true;
		}
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, position.x, position.y);
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

}
