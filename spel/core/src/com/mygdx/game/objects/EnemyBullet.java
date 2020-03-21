package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EnemyBullet extends Bullet {
	boolean homing;
	
	public EnemyBullet(Vector2 position, Texture texture, float offSet, double angle,int speed,boolean homing) {
		super(position, texture, offSet, angle, speed);
		this.homing = homing;
		// TODO Auto-generated constructor stub
	}
	public EnemyBullet(Vector2 position, Texture texture, float offSet, Vector2 direction,boolean homing) {
		super(position, texture, offSet, direction);
		this.homing = homing;
		// TODO Auto-generated constructor stub
	}
	public void update() {
		super.update();
		if(homing && position.x > 400) {
			if (position.y < Player.position.y)
				position.y +=	1;
			if (position.y > Player.position.y)
				 position.y -=	1;
		}
	}
	

}
