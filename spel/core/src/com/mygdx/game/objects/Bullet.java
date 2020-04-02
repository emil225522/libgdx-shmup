package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;

public class Bullet {
	Texture texture;
	float scale;
	Vector2 position;
	float offSet;
	Vector2 direction;
	public boolean isDead = false;
	Rectangle hitBox = new Rectangle();
	int damage;

	// angle in radians
	public Bullet(int damage, Vector2 position, Texture texture, float offSet, double angle, int speed) {
		this.damage = damage;
		this.texture = texture;
		this.position = position;
		this.offSet = offSet;
		this.direction = new Vector2((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);
		this.scale = 1;
	}

	public Bullet(int damage, Vector2 position, Texture texture, float offSet, Vector2 direction) {
		this.damage = damage;
		this.texture = texture;
		this.position = position;
		this.offSet = offSet;
		this.direction = direction;
		this.scale = 1;
	}

	public Bullet(int damage, Vector2 position, Texture texture, float offSet, Vector2 direction, float scale) {
		this.damage = damage;
		this.texture = texture;
		this.position = position;
		this.offSet = offSet;
		this.direction = direction;
		this.scale = scale;
	}
	public Bullet(int damage, Vector2 position, Texture texture, float offSet, double angle, int speed, float scale) {
		this.damage = damage;
		this.texture = texture;
		this.position = position;
		this.offSet = offSet;
		this.direction = new Vector2((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);
		this.scale = scale;
	}

	public void update() {
		hitBox.set(position.x, position.y, texture.getWidth() * scale, texture.getHeight() * scale);
		position.add(direction);
		position.add(0, offSet);

		if (position.y <= 0 || position.y + texture.getHeight() >= MyGame.GAME_HEIGHT) {
			direction.y *= -1;
		}
		if (position.x > 1100 || position.x < 0) {
			isDead = true;
		}

	}

	public int getDamage() {
		return this.damage;
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, position.x, position.y, texture.getWidth() * scale, texture.getHeight() * scale);
		// spriteBatch.draw(texture, position.x, position.y,
		// texture.getWidth() - (Math.abs(offSet) * texture.getWidth() / 1.5f),
		// texture.getHeight() - (Math.abs(offSet) * texture.getHeight()) / 1.5f);
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
}
