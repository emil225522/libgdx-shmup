package com.mygdx.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;
import com.mygdx.game.TextureManager;

public class Spirit extends Boss {

	public Spirit(Vector2 position, Texture texture, int health, ArrayList<Bullet> bullets, ArrayList<Pickup> pickups) {
		super(position, texture, health, bullets, pickups);
	}

	public void update() {
		super.update();
		if (position.y + velocity.y < 0 || position.y + texture.getHeight() + velocity.y > MyGame.GAME_HEIGHT) {
			velocity.y *= -1;
		}
		switch (state) {
		case 0:
			Vector2 shootDir = new Vector2((Player.getCenter().x - this.position.x) / 50,
					((Player.getCenter().y - this.position.y) + playerVelOffset) / 60);
			bulletTimer++;
			if (bulletTimer == 100) {
				if (Math.abs(Player.getVelocity()) < 4)
					playerVelOffset = Player.getVelocity() * 50;
				bullets.add(new EnemyBullet(1,
						new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight() / 2),
						TextureManager.BULLET_TEXTURE, 0, shootDir, true));

			} else if (bulletTimer > 200) {
				bulletTimer = 0;
				for (int i = -1; i < 2; i++) {
					shootDir.scl(0.7f);
					bullets.add(new EnemyBullet(1,
							new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight() / 2),
							TextureManager.BULLET_TEXTURE, 0, new Vector2(-9, i * 2), false));
				}
			}
			if (health < 20) {
				state = 1;
				velocity.y *= 2;
				idealPosition -= 100;
			}
			break;
		case 1:
			bulletTimer++;
			if (bulletTimer > 50) {
				bulletTimer = 0;
				for (int i = -1; i < 2; i++) {
					bullets.add(new EnemyBullet(1, 
							new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight() / 2),
							TextureManager.BULLET_TEXTURE, 0, new Vector2(-10, i * 2), false));
				}
			}
			break;
		}

	}
	
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

}
