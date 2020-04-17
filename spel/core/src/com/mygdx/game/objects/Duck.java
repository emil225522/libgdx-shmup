package com.mygdx.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TextureManager;

public class Duck extends Boss {

	public Duck(Vector2 position, Texture texture, int health, ArrayList<Bullet> bullets, ArrayList<Pickup> pickups) {
		super(position, texture, health, bullets, pickups);
	}
	
	public void update() {
		super.update();
		bulletTimer++;
		if (bulletTimer == 100) {
			bulletTimer = 0;
			for (double i = Math.PI; i > 2*(Math.PI/3); i -= Math.PI/16) {
				bullets.add(new EnemyBullet(1, new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight() / 2),TextureManager.BULLET_TEXTURE, 0, i, 5, true));
			}
		}
		if (position.y < Player.position.y - 5) {
			velocity.y+=0.05f;
		}
		else if (position.y > Player.position.y + 5) {
			velocity.y-=0.05f;
		}
	}

}
