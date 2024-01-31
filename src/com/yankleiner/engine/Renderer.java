package com.yankleiner.engine;
import java.awt.image.DataBufferInt;
import com.yankleiner.engine.gfx.Image;

public class Renderer {
	
	private int pW, pH;
	private int[] pixels;
	public Renderer(GameContainer gc) {
		
		pW = gc.getWidth();
		pH = gc.getHeight();
		pixels = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
				
	}
	public void clear() {
		for(int i = 0 ; i < pixels.length; i++) {
			pixels[i] += i+20000;
			
		}
	}
	
	public void setPixel(int x , int y , int value) {
		if((x < 0 || x >= pW || y < 0 || y >= pH) || value == 0xffff00ff) {
			return;
		}
		
		pixels[x + y *pW] = value;
	}
	
	public void drawImage(Image image, int offx, int offy) {
		
		for(int y = 0 ; y < image.getH(); y++) {
			
			for(int x = 0 ; x < image.getW(); x++) {
				setPixel(x + offx, y + offy, image.getPixels()[x + y * image.getW()]);
				
			}
			
		}
	}
}
