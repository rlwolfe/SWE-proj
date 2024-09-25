package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * 
 */
public class DirectionalLight extends Light implements LightSource {
	private Vector direction;
	
	/**
	 * constructor
	 * @param c
	 * @param v
	 */
	public DirectionalLight(Color c, Vector v) {
		super(c);
		direction = v;
	}

	@Override
	public Color getIntensity(Point p) {
		return intensity;
	}

	@Override
	public Vector getL(Point p) {
		return direction.normalize();
	}
}
