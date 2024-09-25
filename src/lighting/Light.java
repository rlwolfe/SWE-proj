package lighting;

import primitives.Color;

/**
 * Bella & Rachel
 * parent light class
 */
public abstract class Light {
	protected Color intensity;

	/**
	 * constructor
	 * @param c
	 */
	protected Light(Color c) {
		intensity = c;
	}

	/**
	 * getter
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}


}
