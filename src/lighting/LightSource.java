package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Bella & Rachel
 */
public interface LightSource {
	public Color getIntensity(Point p);
	public Vector getL(Point p);
	double getDistance(Point p);
}
