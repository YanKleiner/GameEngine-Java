package com.yankleiner.engine;
import java.awt.image.DataBufferInt;
public class Renderer {
	public static int test = 0;
	private int pW, pH;
	private int[] pixels;
	public Renderer(GameContainer gc) {
		
		pW = gc.getWidth();
		pH = gc.getHeight();
		pixels = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
				
	}
	public void clear() {
		for(int i = 0 ; i < pixels.length; i++) {
			pixels[i] += -i;
			while(test == 1) {
				pixels[i] -= 200000000;
				System.out.println("- 200");
				test = 0 ;
			}
			//pixels[i] += i/2000;
			
		}
	}
}
