package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

/**
 * light that points in a direction
 */
public class PointLight extends Light implements LightSource {
	protected Point position;
	private double kC, kL, kQ;
	
	/**
	 * constructor
	 * @param c
	 * @param p
	 */
	public PointLight(Color c, Point p) {
		super(c);
		kC = 1;
		kL = 0;
		kQ = 0;
		position = p;
	}
	
	/**
	 * @param position to set
	 */
	public PointLight setPosition(Point position) {
		this.position = position;
		return this;
	}
	/**
	 * @param kC to set
	 */
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}
	/**
	 * @param kL to set
	 */
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}
	/**
	 * @param kQ to set
	 */
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		double d = position.distance(p);
		double frac = kC + kL * d + kQ * d * d; //given in slides
		
		if ( !Util.isZero(frac) )
			return ( this.intensity.scale(1/frac) );
		return  this.intensity;
	}

	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}
	
}