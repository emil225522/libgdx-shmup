package com.mygdx.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;
import com.mygdx.game.TextureManager;

public class Duck extends Boss {
	int diveTimer;
	boolean dive;

	public Duck(Vector2 position, Texture texture, int health, ArrayList<Bullet> bullets, ArrayList<Pickup> pickups) {
		super(position, texture, health, bullets, pickups);
	}
	
	public void update() {
		bulletTimer++;
		if (state == 0) {
			super.update();
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
		if (random.nextInt(100) == 20) {
			state = 1;
		}
	}
		if (state == 1) {
			super.update();
			if (position.x == idealPosition) {
				dive = false;
			}

			//velocity = new Vector2();
			if (random.nextBoolean()) {

			position.x+= 2f;
			}
			else {
				position.y-= 2f;
			}
			diveTimer++;
			if (diveTimer > 200 && !dive) {
			
				velocity.x = -15;
			}
			if(position.x < 30) {
				dive = true;
				velocity.x = 15;
			}

		}
		if(position.y < 0) {
			velocity.y+=2;
		}
		if(position.y > MyGame.GAME_HEIGHT) {
			velocity.y-=2;
		}
	}

}
