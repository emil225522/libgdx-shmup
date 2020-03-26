package com.mygdx.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;
import com.mygdx.game.TextureManager;

public class Pet {
	
	Texture texture;

	Vector2 position;
	Vector2 idealPosition;
	
	float dx = 0;
	float dy = 0;
	float dyxa = 2;
	
	ArrayList<Bullet> bullets;
	int bulletTimer;
	
	public Pet(ArrayList<Bullet> bullets) {
		this.position = new Vector2(100,100);
		this.texture = TextureManager.PET_TEXTURE;
		this.bullets = bullets;
		bulletTimer = 0;
	}
	
	public void update() {
		
		this.idealPosition = new Vector2(Player.position.x + Player.texture.getWidth(),Player.position.y + Player.texture.getHeight());
		//this.idealPosition.y += Player.texture.getHeight();
		
		if (position.dst(idealPosition) > 10) {
			if(position.y - idealPosition.y > 10) {
				dy -= dyxa * 0.10f;				
			}else if(position.y - idealPosition.y < -10) {
				dy += dyxa * 0.10f;				
			}
			
			if(position.x - idealPosition.x > 10) {
				dx -= dyxa * 0.10f;				
			}else if(position.x - idealPosition.x < -10) {
				dx += dyxa * 0.10f;				
			}
			if (dy > 4) {dy = 4;}
			if (dy < -4) {dy = -4;}
			if (dx > 4) {dx = 4;}
			if (dx < -4) {dx = -4;}
		}
		position.y += dy;
		position.x += dx;
		
		bulletTimer++;
		
		if (bulletTimer > 100) {
			bulletTimer = 0;
			bullets.add(new Bullet(1,new Vector2(position.x + texture.getWidth(),position.y +(texture.getHeight()/2)), TextureManager.BULLET_TEXTURE,0,new Vector2(5,0),0.5f));
		}
		
	}
	
	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture,position.x,position.y,texture.getWidth(),texture.getHeight());
	}
}
