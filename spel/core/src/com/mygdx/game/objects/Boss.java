package com.mygdx.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;
import com.mygdx.game.TextureManager;

public abstract class Boss extends Enemy {
	int bulletTimer;
	ArrayList<Bullet> bullets;
	int idealPosition = MyGame.WINDOW_WIDTH - 150;
	float playerVelOffset;
	float maxSpeed = 7;
	int state = 0;
	boolean blinkRed;
	boolean isDamaged;
	Texture texture;
	int damageTimer;
	int damage;

	public Boss(Vector2 position, Texture texture, int health, ArrayList<Bullet> bullets, ArrayList<Pickup> pickups) {
		super(position, texture, health, pickups);
		this.bullets = bullets;
		this.texture = texture;
		damage = 1;
		velocity.y = 2;
	}

	public void update() {
		super.update();
		handleBlink();
		if (position.x > idealPosition)
			position.x -= 2f;
		
		if (velocity.y > maxSpeed) {
			velocity.y = maxSpeed;
		}
		if (velocity.y < -maxSpeed) {
			velocity.y = -maxSpeed;
		}
		if (position.y > MyGame.GAME_HEIGHT - texture.getHeight()  - velocity.x
				|| position.y < 0 - velocity.y) {
			velocity.y *= -0.9f;
		}

	}
	private void handleBlink() {
		if (isDamaged) {
			if (damageTimer > 10) {
				isDamaged = false;
				damageTimer = 0;
			} else {
				damageTimer++;
				}
			}
		}
	public void doDamage(int damage) {
		health -= damage;
		isDamaged = true;
	}
	public int getDamage() {
		return damage;
	}

	public void draw(SpriteBatch spriteBatch) {
		if (isDamaged) {
			spriteBatch.setColor(Color.RED);
			spriteBatch.draw(texture, position.x, position.y);
			spriteBatch.setColor(Color.WHITE);
		} else
			spriteBatch.draw(texture, position.x, position.y);
	}

}
