package com.yankleiner.engine.gfx;

import java.awt.Color;

public class Font {
	
	public static final Font STANDARD = new Font("/standard.png");
	
	private Image fontImage;  //image with unicode letters and color markers to mark offsets and width
	private int[] offsets;  //the index on which the unicode letters start
	private int[] widths;   //the widhts of each unicode letter
	
	public Font(String path) {
		
		fontImage = new Image(path);
		
		offsets = new int[59];
		widths = new int[59];
		
		int unicode = 0;
		for(int i = 0 ; i < fontImage.getW();i++) {
			if(fontImage.getPixels()[i] == 0xff0000ff) {
				offsets[unicode] = i; //
			}
			if(fontImage.getPixels()[i] == 0xffffff00) {
				widths[unicode] = i - offsets[unicode];
				unicode++;
				
				
			}
		}
		
	}

	public Image getFontImage() {
		return fontImage;
	}

	public int[] getOffsets() {
		return offsets;
	}

	public void setOffsets(int[] offsets) {
		this.offsets = offsets;
	}

	public int[] getWidths() {
		return widths;
	}

	public void setWidths(int[] widths) {
		this.widths = widths;
	}

	
}
