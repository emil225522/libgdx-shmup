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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
/*import com.mygdx.game.objects.Bird;
import com.mygdx.game.objects.Bullet;
import com.mygdx.game.objects.Enemy;
import com.mygdx.game.objects.Player;*/
import com.mygdx.game.objects.*;

public class MyGame extends Game {
	public static int WINDOW_WIDTH = 1000;
	public static int WINDOW_HEIGHT = 750;
	public static int GAME_HEIGHT;
	SpriteBatch spriteBatch;
	//Texture UITexture;
	//Texture healthBarTexture;
	Player player;
	Pet kotten;
	long startTime = System.nanoTime();
	Random random = new Random();
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Boss> bosses = new ArrayList<Boss>();
	public ArrayList<Pickup> pickups = new ArrayList<Pickup>();
	public ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	Ui ui;
//	BitmapFont font;
	StageHandler stageHandler;
	Background background1;
	Background background2;

	public GameScreen gameScreen;

	public MyGame(int screenWidth, int screenHeight) {
		WINDOW_WIDTH = screenWidth;
		WINDOW_HEIGHT = screenHeight;
	}

	@Override
	public void create() {
		ui = new Ui(TextureManager.UI_TEXTURE, new BitmapFont(Gdx.files.internal("fon.fnt"), false));
		spriteBatch = new SpriteBatch();
		player = new Player(new Vector2(20, WINDOW_HEIGHT / 2), TextureManager.ALIEN_TEXTURE, bullets);
		kotten = new Pet();
		stageHandler = new StageHandler(enemies, bullets, bosses, pickups);
		background1 = new Background(new Vector2(),TextureManager.BACKGROUND_TEXTURE);
		background2 = new Background(new Vector2(TextureManager.BACKGROUND_TEXTURE.getWidth(),0),TextureManager.BACKGROUND_TEXTURE);
		GAME_HEIGHT = WINDOW_HEIGHT - TextureManager.UI_TEXTURE.getHeight();

	}

	@Override
	public void render() {
		generalUpdate();
		Gdx.gl.glClearColor(0, 0.5f, 1, 0.8f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		background1.draw(spriteBatch);
		background2.draw(spriteBatch);
		player.draw(spriteBatch);
		kotten.draw(spriteBatch);
		for (int i = 0; i < bullets.size(); i++) {

			if (bullets.get(i) instanceof EnemyBullet) {
				spriteBatch.setColor(Color.RED);
				bullets.get(i).draw(spriteBatch);
				spriteBatch.setColor(Color.WHITE);
			} else {
				spriteBatch.setColor(Color.WHITE);
				bullets.get(i).draw(spriteBatch);
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(spriteBatch);
		}
		for (int i = 0; i < bosses.size(); i++) {
			bosses.get(i).draw(spriteBatch);
		}
		for (int i = 0; i < pickups.size(); i++) {
			pickups.get(i).draw(spriteBatch);
		}
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).draw(spriteBatch);
		}
		ui.draw(spriteBatch);
		spriteBatch.end();
	}

	public void generalUpdate() {
		if (Player.getHealth() < 1) {
			reset();
		}
		stageHandler.update();
		background1.update();
		background2.update();
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
		}
		for (int i = 0; i < bosses.size(); i++) {
			bosses.get(i).update();
		}
		for (int i = 0; i < pickups.size(); i++) {
			pickups.get(i).update();
		}
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
		}

		handleCollision();
		removeDead();
		player.update();
		kotten.update();
	}

	private void removeDead() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isDead == true) {
				bullets.remove(i);
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isDead == true) {
				explosions.add(new Explosion(new Vector2(enemies.get(i).getPosition().x,enemies.get(i).getPosition().y),new Vector2()));
				enemies.remove(i);
			}
		}
		for (int i = 0; i < bosses.size(); i++) {
			if (bosses.get(i).isDead == true) {
				bosses.remove(i);
			}
		}
		for (int i = 0; i < pickups.size(); i++) {
			if (pickups.get(i).isDead == true) {
				pickups.remove(i);
			}
		}
		for (int i = 0; i < explosions.size(); i++) {
			if (explosions.get(i).isDead == true) {
				explosions.remove(i);
			}
		}
	}

	private void handleCollision() {
		for (Bullet bullet : bullets) {
			if (bullet.getHitBox().overlaps(Player.getHitBox()) && (bullet instanceof EnemyBullet)) {
				Player.doDamage(bullet.getDamage());
				bullet.isDead = true;
			}
			for (Enemy enemy : enemies) {
				if (bullet.getHitBox().overlaps(enemy.getHitBox()) && !(bullet instanceof EnemyBullet)) {
					enemy.velocity.x = 2;
					enemy.doDamage(bullet.getDamage());
					bullet.isDead = true;
				}
			}
			for (Boss boss : bosses) {
				if (bullet.getHitBox().overlaps(boss.getHitBox()) && !(bullet instanceof EnemyBullet)) {
					boss.velocity.x = 2;
					boss.doDamage(bullet.getDamage());
					bullet.isDead = true;
				}
			}
		}
//		for (Bullet bullet : bullets) {
//			for (Boss boss : bosses) {
//				if (bullet.getHitBox().overlaps(boss.getHitBox()) && !(bullet instanceof EnemyBullet)) {
//					boss.velocity.x = 2;
//					boss.doDamage(bullet.getDamage());
//					bullet.isDead = true;
//				}
//			}
//		}
		for (Enemy enemy : enemies) {
			if (enemy.getHitBox().overlaps(Player.getHitBox())) {
				enemy.isDead = true;
				Player.doDamage(1);
			}
		}
		for (Pickup pickup : pickups) {
			if (pickup.getHitBox().overlaps(Player.getHitBox())) {
				pickup.isDead = true;
				if (pickup.type == 0){
					Player.upgrade();
				}
				else {
					Player.heal();
				}
			}
		}
	}
	public void reset() {
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
	    bosses = new ArrayList<Boss>();
		pickups = new ArrayList<Pickup>();
		startTime = System.nanoTime();
		stageHandler.reset();
		create();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}

}
