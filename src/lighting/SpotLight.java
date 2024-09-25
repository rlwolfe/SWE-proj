package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Bella & Rachel
 */
public class SpotLight extends PointLight {
	private Vector direction;
	
	/**
	 * constructor
	 * @param c
	 * @param v
	 * @param p
	 */
	public SpotLight(Color c, Point p, Vector v) {
		super(c, p);
		direction = v;
	}
	
	/**
	 * @param position to set
	 */
	public SpotLight setPosition(Point position) {
		this.setPosition(position);
		return this;
	}
	/**
	 * @param kC to set
	 */
	public SpotLight setkC(double kC) {
		this.setkC(kC);
		return this;
	}
	/**
	 * @param kL to set
	 */
	public SpotLight setkL(double kL) {
		this.setkL(kL);
		return this;
	}
	/**
	 * @param kQ to set
	 */
	public SpotLight setkQ(double kQ) {
		this.setkQ(kQ);
		return this;
	}
	
	@Override
	public Color getIntensity(Point p) {
		double mult = Math.max( 0, direction.normalize().dotProduct( super.getL(p) ));
		return super.getIntensity(p).scale(mult);
	}
}
