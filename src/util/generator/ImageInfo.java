package util.generator;

import java.awt.image.BufferedImage;

/**
 *  Info for WebImageCollector
 * @author maddiebriere
 *
 */
public class ImageInfo {
	private BufferedImage myImage;
	private String myName;
	
	public ImageInfo(BufferedImage myImage, String myName) {
		super();
		this.myImage = myImage;
		this.myName = myName;
	}
	
	public BufferedImage getMyImage() {
		return myImage;
	}
	public void setMyImage(BufferedImage myImage) {
		this.myImage = myImage;
	}
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}

	
	
}
