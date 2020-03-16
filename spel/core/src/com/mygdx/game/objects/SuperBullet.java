package com.mygdx.game.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SuperBullet extends Bullet {
	

	Random rnd;
	ArrayList<Bullet> bullets;

	public SuperBullet(Vector2 position, Texture texture, float offSet, Vector2 dir, ArrayList<Bullet> bullets) {
		super(position, texture, offSet, dir);
		
		this.bullets = bullets;
		rnd = new Random();
	}
	
	public void update() {
		super.update();
		if(rnd.nextInt(1000) > 990) {
			bullets.add(new Bullet(new Vector2(this.position),this.texture,0,new Vector2(7,2)));
			bullets.add(new Bullet(new Vector2(this.position),this.texture,0,new Vector2(7,-2)));
		}
	}

}
