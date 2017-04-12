package gamedata;

/**
 * just a data structure to contain front end info of a projectile type, including: 
 * 1) Image path 
 * 2) Speed 
 * 3) Damage
 * 4) Range/Radius 
 * 5) Explosive
 * 6) Restrictive (straight/ or going around/ or follow an enemy)  
 * 
 * @author Anh
 *
 */
public class ProjectileType {


	private String imagePath; 
	private double speed; 
	private double radius; 
	private boolean explosive; 
	private boolean restrictive; 
	
	public ProjectileType(String image, double sp, double r, boolean expl, boolean rest){
		imagePath = image ; 
		speed = sp; 
		radius = r; 
		explosive = expl; 
		restrictive = rest; 
	}
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public boolean isExplosive() {
		return explosive;
	}

	public void setExplosive(boolean explosive) {
		this.explosive = explosive;
	}

	public boolean isRestrictive() {
		return restrictive;
	}

	public void setRestrictive(boolean restrictive) {
		this.restrictive = restrictive;
	}
}
