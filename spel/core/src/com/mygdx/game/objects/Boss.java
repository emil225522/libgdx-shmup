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

	public Boss(Vector2 position, Texture texture, int health, ArrayList<Bullet> bullets, Player player) {
		super(position, texture, health);
		this.bullets = bullets;
		this.player = player;
		velocity.y = 2;
		// TODO Auto-generated constructor stub
	}

	public void update() {
		super.update();
		if (position.x != idealPosition)
			position.x -= 0.5f;
		if (position.y < 0 || position.y > MyGame.WINDOW_HEIGHT - 50) {
			velocity.y *= -1;
		}
		Vector2 shootDir = new Vector2((player.getCenter().x - this.position.x) / 50,
				((player.getCenter().y - this.position.y) + playerVelOffset) / 50);
		bulletTimer++;
		if (bulletTimer > 80 && bulletTimer < 82) {
			if (Math.abs(player.dy) < 4)
				playerVelOffset = player.dy * 50;
			bullets.add(new EnemyBullet(
					new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight() / 2),
					TextureManager.BULLET_TEXTURE, 0, shootDir, true));

		} else if (bulletTimer > 150) {
			bulletTimer = 0;
			for (int i = -1; i < 2; i++) {
				shootDir.scl(0.5f);
				bullets.add(new EnemyBullet(
						new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight() / 2),
						TextureManager.BULLET_TEXTURE, 0, new Vector2(-7,i*2), false));
			}
		}

	}

	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

}
