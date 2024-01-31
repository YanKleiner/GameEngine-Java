package com.yankleiner.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	private int w, h;  //width //height
	private int[] pixels;
	
	public Image(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		w = image.getWidth();
		h = image.getHeight();
		pixels = image.getRGB(0, 0, w, h, null, 0, w);
		image.flush();

	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setH(int h) {
		this.h = h;
	}
	
}
