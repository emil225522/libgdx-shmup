package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	Texture texture;
	Vector2 position;
	float offSet;
	Vector2 dir;
	public boolean isDead = false;
	Rectangle hitBox = new Rectangle();
	
	public Bullet(Vector2 position, Texture texture, float offSet, Vector2 dir) {
		this.texture = texture;
		this.position = position;
		this.offSet = offSet;
		this.dir = dir;
	}

	public void update() {
		this.hitBox = new Rectangle(position.x, position.y, 50, 50);
		position.add(dir);
		
	}

	public void draw(SpriteBatch spriteBatch) {
		// spriteBatch.draw(texture,position.x,position.y);
		spriteBatch.draw(texture, position.x, position.y,
				texture.getWidth() - (Math.abs(offSet) * texture.getWidth() / 1.5f),
				texture.getHeight() - (Math.abs(offSet) * texture.getHeight()) / 1.5f);
	}
	public Rectangle getHitBox() {
		return hitBox;
	}
}
