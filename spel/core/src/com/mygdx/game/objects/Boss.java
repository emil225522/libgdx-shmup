package com.mygdx.game.objects;

import java.util.ArrayList;

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
	Texture texture;

	public Boss(Vector2 position, Texture texture, int health, ArrayList<Bullet> bullets, ArrayList<Pickup> pickups) {
		super(position, texture, health, pickups);
		this.bullets = bullets;
		this.texture = texture;
		velocity.y = 2;
	}

	public void update() {
		super.update();
		
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

	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

}
