package com.mygdx.game.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;
import com.mygdx.game.TextureManager;

public abstract class Enemy {
	Texture texture;
	Vector2 position;
	public Vector2 velocity = new Vector2();
	Rectangle hitBox = new Rectangle();
	int maxSpeed;
	int health = 0;
	public boolean isDead = false;
	public ArrayList<Pickup> pickups;
	static Random random = new Random();
	int maxHealth;
	Sound enemyDeath = Gdx.audio.newSound(Gdx.files.internal("nice.mp3"));
	Sound bossDeath = Gdx.audio.newSound(Gdx.files.internal("death.mp3"));

	public Enemy(Vector2 position, Texture texture, int health, ArrayList<Pickup> pickups) {
		this.texture = texture;
		this.position = position;
		this.health = health;
		maxHealth = health;
		this.pickups = pickups;
	}

	public void update() {
		hitBox.set(position.x, position.y, texture.getWidth(), texture.getHeight());
		position.x += velocity.x;
		position.y += velocity.y;

		if (velocity.x > maxSpeed) {
			velocity.x -= 0.5f;
		}
		if (health <= 0) {
			enemyDeath.play(0.5f);
			if (this instanceof Boss) {
				bossDeath.play();
				for (int i = 0; i < random.nextInt(4); i++) {

					pickups.add(new Pickup(new Vector2(position.x + random.nextInt(50), position.y + random.nextInt(100)),
							TextureManager.HEART_TEXTURE, 1));
				}
			} else {
				enemyDeath.play();
				if (random.nextInt(10) == 1) {
					pickups.add(new Pickup(position,
							TextureManager.PICKUP_TEXTURE, 0));
				}
				else if(random.nextInt(10) == 1)
					pickups.add(new Pickup(position,
							TextureManager.HEART_TEXTURE, 1));

			}

			isDead = true;
			Player.addToScore(10 * maxHealth);
		}
		if (position.x < -texture.getWidth())

		{
			isDead = true;
		}
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, position.x, position.y);
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void doDamage(int damage) {
		health -= damage;
	}

	public Vector2 getPosition() {
		return position;
	}
	public static int getSpawnPosY() {
		return random.nextInt(MyGame.GAME_HEIGHT - TextureManager.ALIEN_TEXTURE.getHeight()*2) + TextureManager.ALIEN_TEXTURE.getHeight();
	}

}
