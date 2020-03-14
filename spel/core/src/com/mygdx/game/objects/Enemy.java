package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy {
	Texture texture;
	public Vector2 position;
	Rectangle hitBox = new Rectangle();
	int life = 0;
	public boolean isDead = false;

	public Enemy(Vector2 position, Texture texture, int life) {
		this.texture = texture;
		this.position = position;
		this.life = life;
	}

	public void update() {
		hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
		//System.out.println(texture.getWidth()  +"  enemy  " +  texture.getHeight());
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
	public void doDamage(int damage) {
		life-= damage;
	}

}
