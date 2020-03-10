package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	Texture texture;
	Vector2 position;
	float ofSet;

	public Bullet(Vector2 position, Texture texture, float ofSet) {
		this.texture = texture;
		this.position = position;
		this.ofSet = ofSet;
	}

	public void update() {
		position.add(5, ofSet);

	}

	public void draw(SpriteBatch spriteBatch) {
		// spriteBatch.draw(texture,position.x,position.y);
		spriteBatch.draw(texture, position.x, position.y,
				texture.getWidth() - (Math.abs(ofSet) * texture.getWidth() / 1.5f),
				texture.getHeight() - (Math.abs(ofSet) * texture.getHeight()) / 1.5f);
	}
}
