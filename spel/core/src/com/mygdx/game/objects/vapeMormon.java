package com.mygdx.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class vapeMormon extends Enemy {
	Vector2 direction;
	double spincounter = 0;
	private int bulletTimer = 0;
	ArrayList<Bullet> bullets;
	Player player;

	public vapeMormon(Vector2 position, Texture texture, int health, ArrayList<Bullet> bullets, Player player,ArrayList<Pickup> pickups) {
		super(position, texture, health,pickups);
		direction = new Vector2(0,0);
		this.bullets = bullets;
		this.player = player;
		maxSpeed = -3;
		velocity.x = maxSpeed;
	}
	
	public void update() {
		super.update();
		position.add(-1, 0);
		
		spincounter+=0.1;
		
		direction = new Vector2((float)Math.cos(spincounter)*3,(float)Math.sin(spincounter)*3);
		
		position.add(direction);
		
		bulletTimer++;
		if (bulletTimer > 100) {
			bulletTimer = 0;
			Vector2 shootDir = new Vector2((player.getCenter().x - this.position.x)/50,(player.getCenter().y-this.position.y)/50);
			//double shootAngle = player.getPosition().angleRad(this.position);//this.position.angleRad(player.getPosition());
			//float offSet = rnd.nextFloat() - 0.5f;
			bullets.add(new EnemyBullet(new Vector2(position.x + texture.getWidth(), position.y),
					new Texture("bullet.png"), 0, shootDir.angleRad(), 7,false));
		}
	}
	//Borde snurra i draw

}
