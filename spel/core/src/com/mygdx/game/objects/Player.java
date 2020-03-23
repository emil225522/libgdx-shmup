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
import com.mygdx.game.MyGame;
import com.mygdx.game.TextureManager;

public final class Player {
	static Texture texture;
	static Vector2 position = new Vector2();
	static Rectangle hitBox = new Rectangle();
	static float dy = 0;
	static float dya = 2;
	static int health;
	static int maxHealth;
	static int fireRate;

	static long startTime = System.nanoTime();
	static ArrayList<Bullet> bullets;
	Random rnd;
	static int score;

	static int bulletTimer = 0;
	static boolean isDamaged;
	static boolean blinkRed;
	static int damageTimer;
	
	static int gun = 0;

	public Player(Vector2 position, Texture texture, ArrayList<Bullet> bullets) {
		Player.texture = texture;
		Player.position = position;
		Player.bullets = bullets;
		Player.hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
		Player.score = 0;
		Player.health = Player.maxHealth = 5;
		Player.fireRate = 0;
		this.rnd = new Random();
	}

	public void update() {
		Player.hitBox.set(position.x, position.y, texture.getWidth(), texture.getHeight());
		updateScore();
		handleMove();
		handleShoot();
		handleBlink();

	}

	public static Vector2 getCenter() {
		return new Vector2(Player.position.x + (Player.texture.getWidth() / 2),
				Player.position.y + (Player.texture.getHeight() / 2));
	}

	private void handleShoot() {
		
		if(Gdx.input.isKeyJustPressed(Keys.Z)) {
			switchGun();
		}
		
		if (Gdx.input.isKeyPressed(Keys.X)) {
			bulletTimer++;
			if(gun == 0) {
				if (bulletTimer > (10 - fireRate)) {
					bulletTimer = 0;
					float offSet = rnd.nextFloat() - 0.5f;
					bullets.add(
							new Bullet(new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight() / 2),
									TextureManager.BULLET_TEXTURE, offSet, new Vector2(8, 0)));
//				bullets.add(new SuperBullet(
//						new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight() / 2),
//						TextureManager.BULLET_TEXTURE, offSet, 0, 7, bullets));
				}
				
			}else {
				if (bulletTimer > (6 - fireRate)) {
					bulletTimer = 0;
					float offSet = rnd.nextFloat() - 0.5f;
					bullets.add(
							new Bullet(new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight() / 2),
									TextureManager.BULLET_TEXTURE, offSet*5, new Vector2(4, 0)));
//				bullets.add(new SuperBullet(
//						new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight() / 2),
//						TextureManager.BULLET_TEXTURE, offSet, 0, 7, bullets));
				}
			}

		}
	}

	private void switchGun() {
		if(gun == 0) {
			gun = 1;
		}else {
			gun = 0;
		}
	}

	private void updateScore() {
		// vill g�ra n�got h�r sen
		long elapsed = (System.nanoTime() - startTime) / 1000000;
	}

	private void handleMove() {

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			dy += dya * 0.15f;
		} else {
			dy -= dya * 0.15f;
		}

		if (dy > 4) {
			dy = 4;
		}
		if (dy < -4) {
			dy = -4;
		}

		if (position.y > MyGame.WINDOW_HEIGHT - Player.texture.getHeight() - TextureManager.UI_TEXTURE.getHeight() - dy
				|| position.y < 0 - dy) {
			dy *= -0.9f;
		}

		position.y += dy;
	}

	private void handleBlink() {
		if (isDamaged) {
			if (damageTimer > 100) {
				isDamaged = false;
				damageTimer = 0;
				blinkRed = false;
			} else {
				damageTimer++;
				if (damageTimer % 10 == 0) {
					blinkRed = !blinkRed;
				}
			}
		}
	}

	public static void doDamage(int damage) {
		if (isDamaged == false) {
			health -= damage;
			isDamaged = true;
		}
	}

	public static float getVelocity() {
		return dy;
	}

	public static Vector2 getPosition() {
		return position;
	}

	public static Texture getTexture() {
		return texture;
	}

	public static Rectangle getHitBox() {
		return hitBox;
	}

	public static int getScore() {
		return score;
	}

	public static int getHealth() {
		return health;
	}
	public static int getMaxHealth() {
		return maxHealth;
	}

	public static void addToScore(int scoreToAdd) {
		score += scoreToAdd;
	}

	public static void upgrade() {
		fireRate++;
	}

	public static void heal() {
		if (health < maxHealth) {
			health++;
		}
	}

	public void draw(SpriteBatch spriteBatch) {
		if (blinkRed) {
			spriteBatch.setColor(Color.RED);
			spriteBatch.draw(texture, position.x, position.y);
			spriteBatch.setColor(Color.WHITE);
		} else
			spriteBatch.draw(texture, position.x, position.y);
	}

	public static int getGun() {
		return gun;
	}

}
