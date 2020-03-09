package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	Texture texture;
	Vector2 position = new Vector2();
	
	public Bullet(Vector2 position, Texture texture) {
		this.texture = texture;
		this.position = position;
	}
	
	public void update() {
			position.add(5, 0);

	}
	
	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture,position.x,position.y);
	}
}
