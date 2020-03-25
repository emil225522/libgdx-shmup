package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EnemyBullet extends Bullet {
	boolean homing;
	
	public EnemyBullet(int damage, Vector2 position, Texture texture, float offSet, double angle,int speed, boolean homing) {
		super(damage, position, texture, offSet, angle, speed);
		this.homing = homing;
	}
	public EnemyBullet(int damage, Vector2 position, Texture texture, float offSet, Vector2 direction, boolean homing) {
		super(damage, position, texture, offSet, direction);
		this.homing = homing;
	}
	public void update() {
		super.update();
		if(homing && position.x > 400) {
			if (position.y < Player.getCenter().y)
				position.y +=	1;
			if (position.y > Player.getCenter().y)
				 position.y -=	1;
		}
	}
	

}
