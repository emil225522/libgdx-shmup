package com.mygdx.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;
import com.mygdx.game.TextureManager;

public class Boss extends Enemy {
	int bulletTimer;
	ArrayList<Bullet> bullets;
	Player player;
	int idealPosition = MyGame.WINDOW_WIDTH - 150;
	float playerVelOffset;
	int state = 0;

	public Boss(Vector2 position, Texture texture, int health, ArrayList<Bullet> bullets, Player player,
			ArrayList<Pickup> pickups) {
		super(position, texture, health, pickups);
		this.bullets = bullets;
		this.player = player;
		velocity.y = 2;
		// TODO Auto-generated constructor stub
	}

	public void update() {
		super.update();
		if (position.x > idealPosition)
			position.x -= 2f;
		if (position.y + velocity.y < 0 || position.y + velocity.y > MyGame.WINDOW_HEIGHT - 50) {
			velocity.y *= -1;
		}
		if (state == 0) {
			Vector2 shootDir = new Vector2((player.getCenter().x - this.position.x) / 50,
					((player.getCenter().y - this.position.y) + playerVelOffset) / 60);
			bulletTimer++;
			if (bulletTimer == 100) {
				if (Math.abs(player.dy) < 4)
					playerVelOffset = player.dy * 50;
				bullets.add(new EnemyBullet(
						new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight() / 2),
						TextureManager.BULLET_TEXTURE, 0, shootDir, true));

			} else if (bulletTimer > 200) {
				bulletTimer = 0;
				for (int i = -1; i < 2; i++) {
					shootDir.scl(0.7f);
					bullets.add(new EnemyBullet(
							new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight() / 2),
							TextureManager.BULLET_TEXTURE, 0, new Vector2(-9, i * 2), false));
				}
			}
			if (health < 20) {
				state = 1;
			}
		}
		else if (state == 1) {
			velocity.y*=2;
			idealPosition -= 100;
			state = 2;
		}
		else if (state == 2) {
			bulletTimer++;
			if (bulletTimer > 50) {
				bulletTimer = 0;
				for (int i = -1; i < 2; i++) {
					bullets.add(new EnemyBullet(
							new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight() / 2),
							TextureManager.BULLET_TEXTURE, 0, new Vector2(-10, i * 2), false));
				}
			}
		}
		
	}

	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

}
