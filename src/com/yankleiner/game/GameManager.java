package com.yankleiner.game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.yankleiner.engine.AbstractGame;
import com.yankleiner.engine.GameContainer;
import com.yankleiner.engine.Renderer;
import com.yankleiner.engine.gfx.Font;
import com.yankleiner.engine.gfx.Image;
import com.yankleiner.engine.gfx.ImageTile;

public class GameManager extends AbstractGame {
	private int x, y;
	private ImageTile image;
	private float temp;
	//private Image image ;
	int animationIndex = 0;
	public GameManager() {
		image = new ImageTile("/testTexture.png", 16, 16);
		
	}
	@Override
	public void update(GameContainer gc, float dt) { 
		//method that updates every dt -> can be used for logic like animation
		
		
		
		
		temp += dt*20;
		if(temp > 3) temp = 0 ;
		/////////////////////////////////////////////////////////////////////////
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		//Examples for Rendering ImageTile/ Text etc.
		renderer.drawImage(new Image("/testimage2.png"), 140, 30);
		if(gc.getInput().isButton(MouseEvent.BUTTON1)) {
			renderer.drawImageTile(image, gc.getInput().getMouseX() - 8, gc.getInput().getMouseY() - 16, (int)temp, (int)temp );
		}
		if(gc.getInput().isButton(MouseEvent.BUTTON2)) {
			renderer.drawImageTile(image, gc.getInput().getMouseX() - 8, gc.getInput().getMouseY() - 16, (int)temp, (int)temp );
		}
		renderer.drawText("SAMPLE TEXT", gc.getWidth()/ 2 - 10, 20, 0xff00ffff, Font.STANDARD);
		
		///////////////////////////////////////////////////////////////////////////
		
	}
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}
}
