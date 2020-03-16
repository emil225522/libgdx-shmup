package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
/*import com.mygdx.game.objects.Bird;
import com.mygdx.game.objects.Bullet;
import com.mygdx.game.objects.Enemy;
import com.mygdx.game.objects.Player;*/
import com.mygdx.game.objects.*;

public class MyGame extends Game {
	int WINDOW_WIDTH = 1000;
	int WINDOW_HEIGHT = 750;
	SpriteBatch spriteBatch;
	Texture img;
	Player player;
	long startTime = System.nanoTime();
	int spawnTimer = 0;
	Random random = new Random();
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	BitmapFont font;

	public GameScreen gameScreen;

	public MyGame(int screenHeight, int screenWidth) {
		 WINDOW_WIDTH = screenHeight;
		 WINDOW_HEIGHT = screenWidth;
	}
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		img = new Texture("alien.png");
		player = new Player(new Vector2(20, WINDOW_HEIGHT/2), new Texture("alien.png"), bullets);
		font = new BitmapFont(Gdx.files.internal("fon.fnt"), false);
	}

	@Override
	public void render() {
		generalUpdate();
		Gdx.gl.glClearColor(0, 0.5f, 1, 0.8f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		player.draw(spriteBatch);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(spriteBatch);
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(spriteBatch);
		}
		font.draw(spriteBatch, "Score: " + player.getScore(), 100, 740);
		spriteBatch.end();
	}

	public void generalUpdate() {
		player.update();
		spawnTimer++;
		if (spawnTimer > 100) {
			spawnTimer = 0;
			enemies.add(new Bird(new Vector2(WINDOW_WIDTH, random.nextInt(WINDOW_HEIGHT - 45 * 4) + 45),
					new Texture("bird.png"), 5));
			if (random.nextInt(5)==1) {
			enemies.add(new vapeMormon(new Vector2(WINDOW_WIDTH, random.nextInt(WINDOW_HEIGHT - 45 * 4) + 45)
					, new Texture("vapeMormon2.png"), 5, bullets, player));
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
		}
		handleBulletCollision();
		removeDead();
	}

	private void removeDead() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isDead == true) {
				bullets.remove(i);
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isDead == true) {
				enemies.remove(i);
			}
		}
	}

	private void handleBulletCollision() {
		for (Bullet bullet : bullets) {
			for (Enemy enemy : enemies) {
				if (bullet.getHitBox().overlaps(enemy.getHitBox()) && !(bullet instanceof EnemyBullet)) {
					enemy.position.x += 5;
					enemy.doDamage(1);
					bullet.isDead = true;
				}
				if (bullet.getHitBox().overlaps(player.getHitBox()) && (bullet instanceof EnemyBullet)) {
					player.doDamage(1);
					bullet.isDead = true;
				}

			}
		}
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		img.dispose();
	}

}
