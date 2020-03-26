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
	static int shield = 0;
	static int maxShield = 0;
	static int shieldTimer = 0;

	static long startTime = System.nanoTime();
	static ArrayList<Bullet> bullets;
	Random rnd;
	static int score;

	static int bulletTimer;
	static boolean isDamaged;
	static boolean blinkRed;
	static int damageTimer;
	
	static int fireRate;
	static int gun = 0;
	static int damage = 1;
	

	public Player(Vector2 position, Texture texture, ArrayList<Bullet> bullets) {
		Player.texture = texture;
		Player.position = position;
		Player.bullets = bullets;
		Player.hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
		Player.score = 0;
		Player.health = Player.maxHealth = 5;
		Player.shield = Player.maxShield = 0;
		//maxShield =1;
		Player.shieldTimer = Player.bulletTimer = 0;
		Player.fireRate = 1;
		Player.gun = 0;
		Player.damage = 1;
		this.rnd = new Random();
	}

	public void update() {
		Player.hitBox.set(position.x, position.y, texture.getWidth(), texture.getHeight());
		updateScore();
		handleMove();
		handleShoot();
		handleBlink();
		handleShield();

	}

	private void handleShield() {
		if(shieldTimer == 0) {
			if(shield < maxShield) {
				shield++;
			}
		}else {
			shieldTimer--;
		}
		
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
				if (bulletTimer > (30f / (Math.log(((double)fireRate)*Math.E)))) {
					bulletTimer = 0;
					float offSet = rnd.nextFloat() - 0.5f;
					bullets.add(
							new Bullet(2*damage,new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight() / 2),
									TextureManager.BULLET_TEXTURE, offSet, new Vector2(8, 0)));
				}
				
			}else {
				if (bulletTimer > (30f * (1f/fireRate))) {
					bulletTimer = 0;
					float offSet = rnd.nextFloat() - 0.5f;
					bullets.add(
							new Bullet(damage, new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight() / 2),
									TextureManager.BULLET_TEXTURE, (rnd.nextFloat() - 0.5f)*5, new Vector2(4, 0), 0.9f));
					
					bullets.add(
							new Bullet(damage, new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight() / 2),
									TextureManager.BULLET_TEXTURE, (rnd.nextFloat() - 0.5f)*5, new Vector2(4, 0), 0.9f));
					
					if(rnd.nextInt(1000) > 990) {
				bullets.add(new SuperBullet(5,
						new Vector2(position.x + texture.getWidth(), position.y + texture.getHeight() / 2),
						TextureManager.HEALTHBAR_TEXTURE, offSet, 0, 7, bullets));
						
					}
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
			if (damage <= shield) {
				shield -= damage;
			}else {
				damage -= shield;
				shield = 0;
				health -= damage;
			}
			shieldTimer = 600;
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
	public static int getShield() {
		return shield;
	}
	public static int getMaxShield() {
		return maxShield;
	}
	public static int getFireRate() {
		return fireRate;
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

	public static void setMaxShield(int newShieldValue) {
		maxShield = newShieldValue;
		
	}

}
