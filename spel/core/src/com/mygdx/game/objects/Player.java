package com.mygdx.game.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	Texture texture;
	Vector2 position = new Vector2();
	float dy = 0;
	float dya = 2;
	long startTime = System.nanoTime();
	static int score;
	int life;
	int bulletTimer = 0;
	ArrayList<Bullet> bullets;
	Random rnd;
	Rectangle hitBox = new Rectangle();

	public Player(Vector2 position, Texture texture, ArrayList<Bullet> bullets) {
		this.texture = texture;
		this.position = position;
		this.bullets = bullets;
		this.hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
		this.score = 0;
		this.life = 1000;
		this.rnd = new Random();
	}

	public void update() {
		this.hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
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
				bullets.add(new SuperBullet(new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight()/2),
						new Texture("bullet.png"), offSet, new Vector2(7, 0),bullets));
			}
		}
	}

	private void updateScore() {
		//vill göra något här sen
		long elapsed = (System.nanoTime() - startTime) / 1000000;
	}

	private void handleMove() {
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			dy += dya * 0.1f;
		} else {
			dy -= dya * 0.1f;
		}

		if (dy > 4) {
			dy = 4;
		}
		if (dy < -4) {
			dy = -4;
		}

		if (position.y > 700 - dy || position.y < 0 - dy) {
			dy *= -1;
		}

		position.y += dy;
	}
	public void doDamage(int damage) {
		life-= damage;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Texture getTexture() {
		return texture;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
	public int getScore() {
		return score;
	}
	public static void addToScore(int scoreToAdd) {
		score+= scoreToAdd;
	}
	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, position.x, position.y);

	}

}
