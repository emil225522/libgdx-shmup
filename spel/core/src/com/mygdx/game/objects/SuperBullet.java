package com.mygdx.game.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SuperBullet extends Bullet {
	

	Random rnd;
	ArrayList<Bullet> bullets;

	public SuperBullet(Vector2 position, Texture texture, float offSet, double angle, int speed, ArrayList<Bullet> bullets) {
		super(position, texture, offSet, angle, speed);
		
		this.bullets = bullets;
		rnd = new Random();
	}
	
	public void update() {
		super.update();
		if(rnd.nextInt(1000) > 990) {
			bullets.add(new Bullet(new Vector2(this.position),this.texture,0,-Math.PI/4,7));
			bullets.add(new Bullet(new Vector2(this.position),this.texture,0,Math.PI/4,7));
			super.isDead=true;
		}
	}

}
