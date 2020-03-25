package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.Player;

public class Ui {
	Texture texture;
	BitmapFont font;

	public Ui(Texture texture, BitmapFont font) {
		this.texture = texture;
		this.font = font;
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(this.texture, 0, MyGame.WINDOW_HEIGHT - texture.getHeight());
		spriteBatch.draw(TextureManager.HEALTHBAR_TEXTURE, 130,
				MyGame.WINDOW_HEIGHT - TextureManager.HEALTHBAR_TEXTURE.getHeight()
						- (this.texture.getHeight() - TextureManager.HEALTHBAR_TEXTURE.getHeight()) / 2,
				((float) Player.getHealth() / (float) Player.getMaxHealth()) * 200,
				TextureManager.HEALTHBAR_TEXTURE.getHeight());
		
		if(Player.getMaxShield() > 0) {
			spriteBatch.draw(TextureManager.SHEILD_TEXTURE, 130,
					MyGame.WINDOW_HEIGHT - TextureManager.SHEILD_TEXTURE.getHeight()
					- (this.texture.getHeight() - TextureManager.SHEILD_TEXTURE.getHeight()) / 2,
					((float) Player.getShield() / (float) Player.getMaxShield()) * 200,
					TextureManager.SHEILD_TEXTURE.getHeight());			
		}
		
		spriteBatch.draw(TextureManager.GUN_SELECTOR_TEXTURE, 350, 
				MyGame.WINDOW_HEIGHT - TextureManager.GUN_SELECTOR_TEXTURE.getHeight()
				- (this.texture.getHeight() - TextureManager.GUN_SELECTOR_TEXTURE.getHeight()) / 2);
		spriteBatch.draw(TextureManager.GUN_SELECTOR_FRAME_TEXTURE, 350 + (50 * Player.getGun()), 
				MyGame.WINDOW_HEIGHT - TextureManager.GUN_SELECTOR_FRAME_TEXTURE.getHeight()
				- (this.texture.getHeight() - TextureManager.GUN_SELECTOR_FRAME_TEXTURE.getHeight()) / 2);
		
		font.draw(spriteBatch, "Score: " + Player.getScore(), 450, MyGame.WINDOW_HEIGHT - font.getXHeight());
		font.draw(spriteBatch, "Stage: " + StageHandler.getStage(), 850, MyGame.WINDOW_HEIGHT - font.getXHeight());
		font.draw(spriteBatch, "FireRate: " + Player.getFireRate(), 650, MyGame.WINDOW_HEIGHT - font.getXHeight());
		font.draw(spriteBatch, "FireDelay1: " + (30f / (Math.log(((double)Player.getFireRate())*Math.E))), 650, MyGame.WINDOW_HEIGHT - font.getXHeight() - 50);
		font.draw(spriteBatch, "FireDelay2: " + (30f * (1f/Player.getFireRate())), 650, MyGame.WINDOW_HEIGHT - font.getXHeight() - 100);
		font.draw(spriteBatch, "Health: ", 10, MyGame.WINDOW_HEIGHT - font.getXHeight());
	}
}
