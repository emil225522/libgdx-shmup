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
		if (position.y + velocity.y < 0 || position.y + texture.getHeight() + velocity.y > MyGame.GAME_HEIGHT) {
			velocity.y *= -1;
		}
		

	}

	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

}
