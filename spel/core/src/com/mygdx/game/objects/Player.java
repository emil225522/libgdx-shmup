package com.mygdx.game.objects;

import java.util.ArrayList;
import java.util.Random;

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
	int bulletTimer = 0;
	ArrayList<Bullet> bullets;
	Random rnd;

	public Player(Vector2 position, Texture texture, ArrayList<Bullet> bullets) {
		this.texture = texture;
		this.position = position;
		this.bullets = bullets;
		
		this.score = 0;
		this.rnd = new Random();
	}

	public void update() {

		updateScore();
		handleMove();
		handleShoot();

	}

	private void handleShoot() {
		if (Gdx.input.isKeyPressed(Keys.X)) {

			bulletTimer++;
			if (bulletTimer > 10) {
				bulletTimer = 0;
				float offSet = rnd.nextFloat() - 0.5f;
				bullets.add(new Bullet(new Vector2(position.x + texture.getWidth(), position.y),
						new Texture("bullet.png"), offSet, new Vector2(5,0)));
			}
		}
	}

	private void updateScore() {
		long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
			score++;
            startTime = System.nanoTime();
        }
	}

	private void handleMove() {
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			dy += dya * 0.1f;
		}else {
			dy -= dya * 0.1f;
		}
		
		if (dy > 4) {dy = 4;}
		if (dy < -4){dy = -4;}

		if (position.y > 420 - dy || position.y < 0 - dy) {dy *= -1;}
		
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
