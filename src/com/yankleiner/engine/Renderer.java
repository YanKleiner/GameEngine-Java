package com.yankleiner.engine;
import java.awt.image.DataBufferInt;

import com.yankleiner.engine.gfx.Font;
import com.yankleiner.engine.gfx.Image;
import com.yankleiner.engine.gfx.ImageTile;

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
			pixels[i] = 0 ;

		}
	}

	public void setPixel(int x , int y , int value) {
		if((x < 0 || x >= pW || y < 0 || y >= pH) || value == 0xffff00ff) {
			return;
		}

		pixels[x + y *pW] = value;
	}
	
	public void drawText(String text, int offx, int offy, int color, Font font) {
		Image fontImage = font.getFontImage();
		int letterPointer = 0;
		
		text = text.toUpperCase();
		
		for(int i = 0 ; i < text.length(); i++) {
			
			int unicode = text.codePointAt(i) - 32; //make it start at useful unicodes
			
			for(int y = 0 ; y < fontImage.getH(); y++) {  //for every height
				for(int x = 0; x < font.getWidths()[unicode]; x++) {   //setPixels for as long as unicode is long
					
					if(fontImage.getPixels()[x + font.getOffsets()[unicode] + y * fontImage.getW()] == 0xffffffff) {
						setPixel(x + offx + letterPointer, y + offy, color);  //at specific offset
					}
				}
			}
			letterPointer += font.getWidths()[unicode];  // print every letter at each pointer
			
			
		}
	}
	
	
	
	
	public void drawImage(Image image, int offx, int offy) {

		
		int newX = 0;
		int newY = 0;
		int endWidth = image.getW();
		int endHeight = image.getH();
		//dont render code
		if(offx < - endWidth) {return;}
		if(offy < - endHeight) {return;}
		if(offx >= pW) {return;}
		if(offy >= pH) {return;}
		//clipping code
		if(offx < 0 ) {newX = newX - offx;}
		if(offy < 0 ) {newY = newY - offy;}
		if(endWidth + offx > pW) {endWidth =  pW - offx;}
		if(endHeight + offy > pH) {endHeight = pH - offy;}

		//render image pixel by pixel
		for(int y = newY ; y < endHeight; y++) {

			for(int x = newX ; x < endWidth; x++) {
				setPixel(x + offx, y + offy, image.getPixels()[x + y * image.getW()]);

			}

		}
	}
	public void drawImageTile(ImageTile image, int offx, int offy, int tilex, int tiley) {

		int newX = 0;
		int newY = 0;
		int endWidth = image.getTileW();
		int endHeight = image.getTileH();
		//dont render code
		if(offx < - endWidth) {return;}
		if(offy < - endHeight) {return;}
		if(offx >= pW) {return;}
		if(offy >= pH) {return;}
		//clipping code
		if(offx < 0 ) {newX = newX - offx;}
		if(offy < 0 ) {newY = newY - offy;}
		if(endWidth + offx > pW) {endWidth =  pW - offx;}
		if(endHeight + offy > pH) {endHeight = pH - offy;}

		//render image pixel by pixel
		for(int y = newY ; y < endHeight; y++) {

			for(int x = newX ; x < endWidth; x++) {
				setPixel(x + offx, y + offy, image.getPixels()[(x + tilex * image.getTileW()) + (y + tiley * image.getTileH()) * image.getW()]);

			}

		}
	}
	
	
	
	
	
	
	
}








