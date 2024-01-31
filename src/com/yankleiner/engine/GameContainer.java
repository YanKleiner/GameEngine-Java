package com.yankleiner.engine;

public class GameContainer implements Runnable{
	private Thread thread;
	private Window window;
	private Renderer renderer;
	private boolean running;
	private final double UPDATE_CAP = 1.0/60.0;
	
	private int width = 1920, height = 1080;
	private float scale = 1f;
	private String title = "YanEngine v1.0";
	
	
	public GameContainer() {
		
	}
	public void start() {
		
		window = new Window(this);
		renderer = new Renderer(this);
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
				if(frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0 ;
					System.out.println("FPS" + fps);
				}
			}
			
			if(render) {
				renderer.clear();
				
				//TODO: Render game
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
	public static void main(String[] args) {
		GameContainer gc = new GameContainer();
		gc.start();
	}
	public Window getWindow() {
		return window;
	}
	public void setWindow(Window window) {
		this.window = window;
	}
}
