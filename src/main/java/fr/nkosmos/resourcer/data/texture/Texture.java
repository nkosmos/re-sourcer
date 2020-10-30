package fr.nkosmos.resourcer.data.texture;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import lombok.Data;

public @Data class Texture {

	private final String path;
	private final int width, height;
	
	public Texture(String path) {
		this.path = path;
		
		try {
			Image img = readImage();
			this.width = img.getWidth(null);
			this.height = img.getHeight(null);
		} catch(Throwable t) {
			throw new RuntimeException(t);
		}
	}
	
	public InputStream getAsStream() {
		return getClass().getClassLoader().getResourceAsStream(path);
	}
	
	public Image readImage() throws IOException {
		return ImageIO.read(getAsStream());
	}
	
}
