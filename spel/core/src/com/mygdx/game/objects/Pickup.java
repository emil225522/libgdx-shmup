package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TextureManager;

public class Pickup {
	Texture texture;
	Vector2 position;
	public int type = 0;
	public Vector2 velocity = new Vector2();
	public boolean isDead = false;
	Rectangle hitBox = new Rectangle();
	
	public Pickup(Vector2 position, Texture texture,int type) {
		this.texture = texture;		
		this.position = position;
		this.type = type;
	}
	public void update() {
		position.x-= 3;
		if (position.x < -100)
			isDead = true;
		hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
	}
	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, position.x, position.y);
	}
	public Rectangle getHitBox() {
		return hitBox;
	}
	public Vector2 getPosition() {
		return position;
	}

}
