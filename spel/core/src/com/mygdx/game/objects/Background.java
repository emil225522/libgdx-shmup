package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
	Texture texture;
	Vector2 position = new Vector2();
	int speed;

	public Background(Vector2 position, Texture texture) {
		this.position = position;
		this.texture = texture;
		speed = -2;
		
	}

	public void update() {
		if (position.x <= -texture.getWidth()) {
			position.x = texture.getWidth() + speed ;
		}
		position.x += speed;
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture,position.x,position.y);
	}
}
