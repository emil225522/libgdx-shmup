package com.mygdx.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;

public class Boss extends Enemy {
	int bulletTimer;
	ArrayList<Bullet> bullets;
	Player player;

	public Boss(Vector2 position, Texture texture, int health, ArrayList<Bullet> bullets, Player player) {
		super(position, texture, health);
		this.bullets = bullets;
		this.player = player;
		velocity.y = 2;
		// TODO Auto-generated constructor stub
	}

	public void update() {
		super.update();
		if (position.y < 0 || position.y > MyGame.WINDOW_HEIGHT) {
			velocity.y*=-1;
		}
		bulletTimer++;
		if (bulletTimer > 100) {
			bulletTimer = 0;
			Vector2 shootDir = new Vector2((player.getCenter().x - this.position.x)/50,(player.getCenter().y-this.position.y)/50);
			//double shootAngle = player.getPosition().angleRad(this.position);//this.position.angleRad(player.getPosition());
			//float offSet = rnd.nextFloat() - 0.5f;
			bullets.add(new EnemyBullet(new Vector2(position.x + texture.getWidth(), position.y),
					new Texture("bullet.png"), 0, shootDir.angleRad(), 7));
		}

	}

	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

}
