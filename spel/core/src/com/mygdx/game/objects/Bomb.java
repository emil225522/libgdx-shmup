package com.mygdx.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;
import com.mygdx.game.TextureManager;

public class Bomb extends Enemy{
	float rotation;
	ArrayList<Bullet> bullets;

	public Bomb(Vector2 position, Texture texture, int health, ArrayList<Pickup> pickups) {
		super(position, texture, health, pickups);
	}
	public Bomb(Texture texture,ArrayList<Bullet> bullets, ArrayList<Pickup> pickups) {
		super(new Vector2(MyGame.WINDOW_WIDTH, getSpawnPosY()), texture, 1, pickups);
		maxSpeed = -3;
	 this.bullets = bullets;
		velocity.x = maxSpeed;
		velocity.y-= random.nextFloat();
	}
	public void update() {
		if (health < 1) {
			for (int i = 0; i < 20; i++) {
				bullets.add(new EnemyBullet(1, new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight() / 2),TextureManager.BULLET_TEXTURE, 0, i, 8, true));
			}
		}
		super.update();
		
	}
//	public void draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height,
//			float scaleX, float scaleY, float rotation, boolean clockwise) {
	public void draw(SpriteBatch spriteBatch) {
		TextureRegion region =  new TextureRegion( texture);
		 rotation++;
		spriteBatch.draw(region,position.x,position.y,texture.getWidth()/2,texture.getHeight()/2,texture.getWidth(),texture.getHeight(),1,1,rotation,true);
	}

}
