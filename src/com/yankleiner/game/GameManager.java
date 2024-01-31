package com.yankleiner.game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.yankleiner.engine.AbstractGame;
import com.yankleiner.engine.GameContainer;
import com.yankleiner.engine.Renderer;
import com.yankleiner.engine.gfx.Image;

public class GameManager extends AbstractGame {
	private int x, y;
	private Image image ;
	public GameManager() {
		image = new Image("/testimage2.png");
		
	}
	@Override
	public void update(GameContainer gc, float dt) {
		if(gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			System.out.println("A was pressed once");
		}
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		
		if(gc.getInput().isKey(KeyEvent.VK_0)) {
			x = gc.getInput().getMouseX();
			y = gc.getInput().getMouseY();
			renderer.drawImage(image, gc.getInput().getMouseX(), gc.getInput().getMouseY());
		}
		renderer.drawImage(image, x, y);
		
	}
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}
}
