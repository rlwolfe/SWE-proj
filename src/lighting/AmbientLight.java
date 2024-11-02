package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Bella & Rachel
 * constructors accept color and either a double or double3
 * fields are final and and intensity and default ("none") ambient light
 * public methods consist of: getIntensity
 * no Overridden methods
 */
public class AmbientLight extends Light {
	//final private Color intensity;
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO); 
	
	/**
	 * constructor that takes a color and a double3 and passed it to parent
	 * @param Ia
	 * @param Ka
	 */
	public AmbientLight( Color Ia, Double3 Ka) {
		super(Ia.scale(Ka));
	}
	
	/**
	 * constructor that takes a color and a double and passed it to parent
	 * @param Ia
	 * @param Ka
	 */
	public AmbientLight( Color Ia, double Ka) {
		super(Ia.scale(Ka));
	}
}
