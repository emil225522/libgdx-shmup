package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	Texture texture;
	Vector2 position;
	float offSet;

	public Bullet(Vector2 position, Texture texture, float offSet) {
		this.texture = texture;
		this.position = position;
		this.offSet = offSet;
	}

	public void update() {
		position.add(5, offSet);

	}

	public void draw(SpriteBatch spriteBatch) {
		// spriteBatch.draw(texture,position.x,position.y);
		spriteBatch.draw(texture, position.x, position.y,
				texture.getWidth() - (Math.abs(offSet) * texture.getWidth() / 1.5f),
				texture.getHeight() - (Math.abs(offSet) * texture.getHeight()) / 1.5f);
	}
}
