package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.Bird;
import com.mygdx.game.objects.Bullet;
import com.mygdx.game.objects.Enemy;
import com.mygdx.game.objects.Player;

public class MyGame extends Game {
	SpriteBatch spriteBatch;
	Texture img;
	Player player;
	long startTime = System.nanoTime();
	int spawnTimer = 0;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	public GameScreen gameScreen;

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		img = new Texture("alien.png");
		player = new Player(new Vector2(20, 200), new Texture("alien.png"), bullets);

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
		spriteBatch.end();
	}

	public void generalUpdate() {
		player.update();
		spawnTimer++;
		if(spawnTimer > 100) {
			spawnTimer = 0;
			enemies.add(new Bird(new Vector2(500,200), new Texture("bird.png"), 5));
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
		}
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		img.dispose();
	}

}
