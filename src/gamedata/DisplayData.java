package gamedata;

/**
 * Display information
 * 
 * @author maddiebriere
 *
 */

public class DisplayData {
	private static final String DEFAULT_IMAGE = "images/enemy_icon.png";
	
	private String splashScreenImage;
	private String thumbnailImage;
	private Integer colorTheme;
	
	public DisplayData(){
		this(DEFAULT_IMAGE, DEFAULT_IMAGE, 0);
	}
	
	public DisplayData(String splashScreenImage, String thumbnailImage, Integer colorTheme) {
		super();
		this.splashScreenImage = splashScreenImage;
		this.thumbnailImage = thumbnailImage;
		this.colorTheme = colorTheme;
	}
	public String getSplashScreenImage() {
		return splashScreenImage;
	}
	public void setSplashScreenImage(String splashScreenImage) {
		this.splashScreenImage = splashScreenImage;
	}
	public String getThumbnailImage() {
		return thumbnailImage;
	}
	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}
	public Integer getColorTheme() {
		return colorTheme;
	}
	public void setColorTheme(Integer colorTheme) {
		this.colorTheme = colorTheme;
	}
	
	
}
