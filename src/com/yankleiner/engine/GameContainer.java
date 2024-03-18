package com.yankleiner.engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameContainer implements Runnable{
	private Thread thread;
	private Window window;
	private Renderer renderer;
	private Input input;
	private AbstractGame game;
	private boolean running;
	public static double UPDATE_CAP = 1.0/144.0;
	
	private int width = 300, height = 200;
	private float scale = 3f;
	private String title = "YanEngine v1.0";
	
	
	public GameContainer(AbstractGame game) {
		this.game = game;
	}
	public void start() {
		
		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);
		thread = new Thread(this);
		thread.run();
		
	}
	public void stop() {
		
	}
	
	@Override
	public void run() {
		running = true;
		
		boolean render = false;
		
		double frameTime = 0;
		int frames = 0;
		int fps = 0;
		
		double firstTime;
		double lastTime = System.nanoTime() / 1000000000.0;;
		
		double passedTime = 0 ;
		double unprocessedTime = 0;
		
		
		while(running) { //repeats all the time 
			render = false;
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= UPDATE_CAP) {  //while it should update
				
				unprocessedTime -= UPDATE_CAP;
				render = true;
				//TODO: Update Game
				
				game.update(this, (float)UPDATE_CAP);
				
				input.update();
				if(frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0 ;
					System.out.println("FPS" + fps);
				}
			}
			
			if(render) {
				//renderer.clear();
				
				//TODO: Render game
				game.render(this, renderer);
				window.update();
				frames++;
			}
			else {
				try {
					thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			dispose();
		}
	}
	public void dispose() {
		
	}
	
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Window getWindow() {
		return window;
	}
	public void setWindow(Window window) {
		this.window = window;
	}
	public Input getInput() {
		return input;
	}
}
