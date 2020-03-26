package com.mygdx.game.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;

public class SuperBullet extends Bullet {
	

	Random rnd;
	ArrayList<Bullet> bullets;

	public SuperBullet(int damage, Vector2 position, Texture texture, float offSet, double angle, int speed, ArrayList<Bullet> bullets) {
		super(damage, position, texture, offSet, angle, speed);
		
		this.bullets = bullets;
		rnd = new Random();
	}
	
	public void update() {
		super.update();
		if(rnd.nextInt(1000) > 1000*(MyGame.WINDOW_WIDTH /(position.x*2))) {
			//bullets.add(new Bullet(new Vector2(this.position),this.texture,0,-Math.PI/4,7));
			//bullets.add(new Bullet(new Vector2(this.position),this.texture,0,Math.PI/4,7));
			for(double i = 0; i < Math.PI*2; i += Math.PI/6) {
				bullets.add(new Bullet(1, new Vector2(this.position),this.texture,0,i,7));
			}
			super.isDead=true;
		}
	}

}
