package gamedata;

/**
 * just a data structure to contain front end info of a projectile type, including: 
 * 1) Image path 
 * 2) Damage
 * 3) Explosive
 * 4) Restrictive (straight/ or going around/ or follow an enemy)  
 * 
 * @author Anh
 *
 */
public class ProjectileType {


	private String imagePath; 
	private boolean explosive; 
	private boolean restrictive;
	private double damage; 
	
	public ProjectileType(String image, double damage, boolean expl, boolean rest){
		imagePath = image ; 
		explosive = expl; 
		restrictive = rest; 
	}
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}
}
