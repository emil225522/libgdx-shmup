package com.mygdx.game.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TextureManager;

public class Player {
	Texture texture;
	static Vector2 position = new Vector2();
	float dy = 0;
	float dya = 2;
	long startTime = System.nanoTime();
	static int score;
	int health;
	int bulletTimer = 0;
	ArrayList<Bullet> bullets;
	Random rnd;
	Rectangle hitBox = new Rectangle();
	boolean isDamaged;
	boolean blinkRed;
	int damageTimer;
	int blinkingTimer;
	int shootState = 0;

	public Player(Vector2 position, Texture texture, ArrayList<Bullet> bullets) {
		this.texture = texture;
		this.position = position;
		this.bullets = bullets;
		this.hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
		this.score = 0;
		this.health = 5;
		this.rnd = new Random();
	}

	public void update() {
		this.hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
		updateScore();
		handleMove();
		handleShoot();
		handleBlink();

	}

	public Vector2 getCenter() {
		return new Vector2(this.position.x + (this.texture.getWidth() / 2),
				this.position.y + (this.texture.getHeight() / 2));
	}

	private void handleShoot() {
		if (Gdx.input.isKeyPressed(Keys.X)) {

			bulletTimer++;
			if (bulletTimer > 10 - shootState) {
				bulletTimer = 0;
				float offSet = rnd.nextFloat() - 0.5f;
				bullets.add(
						new Bullet(new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight() / 2),
								TextureManager.BULLET_TEXTURE, offSet, new Vector2(7, 0)));
//				bullets.add(new SuperBullet(
//						new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight() / 2),
//						TextureManager.BULLET_TEXTURE, offSet, 0, 7, bullets));
			}
		}
	}

	private void updateScore() {
		// vill göra något här sen
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
			dy *= -0.9f;
		}

		position.y += dy;
	}

	private void handleBlink() {
		if (isDamaged) {
			if (damageTimer > 100) {
				isDamaged = false;
				damageTimer = 0;
				blinkingTimer = 0;
				blinkRed = false;
			} else {
				damageTimer++;
				blinkingTimer++;
				if (blinkingTimer < 10) {
					blinkRed = true;
				} else if (blinkingTimer > 10 && blinkingTimer < 20) {
					blinkRed = false;
				} else {
					blinkingTimer = 0;
					blinkRed = false;
				}
			}
		}
	}

	public void doDamage(int damage) {
		if (isDamaged == false) {
			health -= damage;
			if (health < 1)
				System.exit(1);
			isDamaged = true;
		}
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

	public int getHealth() {
		return health;
	}

	public static void addToScore(int scoreToAdd) {
		score += scoreToAdd;
	}

	public void upgrade() {
		shootState++;
	}
	public void heal() {
		health++;
	}

	public void draw(SpriteBatch spriteBatch) {
		if (blinkRed) {
			spriteBatch.setColor(Color.RED);
			spriteBatch.draw(texture, position.x, position.y);
			spriteBatch.setColor(Color.WHITE);
		} else
			spriteBatch.draw(texture, position.x, position.y);
	}

}
