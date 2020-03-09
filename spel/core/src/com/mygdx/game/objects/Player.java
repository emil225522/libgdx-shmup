package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
	Texture texture;
	Vector2 position = new Vector2();
	float dy = 0;
	float dya = 2;
	long startTime = System.nanoTime();
	int score;

	public Player(Vector2 position, Texture texture) {
		this.texture = texture;
		this.position = position;
	}

	public void update() {
		/*if (Gdx.input.isKeyPressed(Keys.W)) {
			position.add(0, 5);
			// playerPos.set(new vector2(playerPos.x+20,));
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			position.add(0, -5);
			// playerPos.set(new vector2(playerPos.x+20,));
		}*/
		long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
			score++;
            startTime = System.nanoTime();
        }

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			dy += dya * 0.1f;

		}

		else {
			dy -= dya * 0.1f;
		}
		if (dy > 4)
			dy = 4;
		if (dy < -4)
			dy = -4;

		if (position.y > 420 - dy) {
			dy *= -1;
		}
		if (position.y < 0 - dy) {
			dy *= -1;
		}
		position.y += dy;
	}
	public Vector2 getPosition() {
		return position;
	}
	public Texture getTexture() {
		return texture;
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, position.x, position.y);
	}

}
