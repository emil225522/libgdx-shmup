package com.mygdx.game.objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;

public class Bullet {
	Texture texture;
	Vector2 position;
	float offSet;
	Vector2 dir;
	public boolean isDead = false;
	Rectangle hitBox = new Rectangle();
	
//angle in radians
	public Bullet(Vector2 position, Texture texture, float offSet,double angle, int speed) {
		this.texture = texture;
		this.position = position;
		this.offSet = offSet;
		this.dir = new Vector2((float)Math.cos(angle)*speed,(float)Math.sin(angle)*speed);
	}
	public Bullet(Vector2 position, Texture texture, float offSet, Vector2 dir) {
		this.texture = texture;
		this.position = position;
		this.offSet = offSet;
		this.dir = dir;
	}

	public void update() {
		this.hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
		position.add(dir);
		position.add(0, offSet);
		if(position.y <= 0 || position.y + texture.getHeight() >= MyGame.WINDOW_HEIGHT) {
			dir.y *= -1;
		}
		if (position.x > 1100 || position.x < 0)
			isDead = true;
		
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, position.x, position.y,
				texture.getWidth() - (Math.abs(offSet) * texture.getWidth() / 1.5f),
				texture.getHeight() - (Math.abs(offSet) * texture.getHeight()) / 1.5f);
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
}
